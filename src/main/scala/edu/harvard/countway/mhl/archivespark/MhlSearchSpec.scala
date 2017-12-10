package edu.harvard.countway.mhl.archivespark

import de.l3s.archivespark.dataspecs.DataSpec
import de.l3s.archivespark.specific.books.IATxtBookAccessor
import de.l3s.archivespark.utils.RddUtil
import edu.harvard.countway.mhl.archivespark.search.{MhlFields, MhlSearchOptions}
import org.apache.http.client.entity.UrlEncodedFormEntity
import org.apache.http.client.methods.HttpPost
import org.apache.http.impl.client.SystemDefaultHttpClient
import org.apache.http.message.BasicNameValuePair
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import org.json4s.JsonAST.JObject
import org.json4s.native.JsonMethods

import scala.collection.JavaConverters._
import scala.io.Source
import scala.util.Try

class MhlSearchSpec private (options: MhlSearchOptions, maxRecords: Int) extends DataSpec[MhlSearchResult, MhlSearchRecord] {
  import MhlSearchSpec._

  override def load(sc: SparkContext, minPartitions: Int): RDD[MhlSearchResult] = {
    val pages = Math.ceil(maxRecords.toDouble / ResultsPerPage).toInt
    RddUtil.parallelize(sc, pages, minPartitions).flatMap{page =>
      val client = new SystemDefaultHttpClient()
      val post = new HttpPost(MhlSearchUrl)
      val q = {
        val conjunctiveQuery = if (options.conjunctive) options.query.replace(" ", " AND ") else options.query
        val q = s"""("${options.query}"^10) OR ($conjunctiveQuery)"""
        options.field match {
          case Some(MhlFields.MhlField(field)) => s"($field:$q)"
          case None => q
        }
      }
      var params = Seq(
        "qt" -> "standard",
        "defType" -> "edismax",
        "wt" -> "json",
        "fl" -> "id,title,date,subject,publisher,author,volume,language,collection,contributor,description,mediatype,score",
        "qf" -> "title^4 subject^3 fulltext^2 author volume date publisher collection contributor language",
        "facet.field" -> "language",
        "facet.field" -> "mediatype",
        "facet.field" -> "collection",
        "q" -> q,
        "rows" -> ResultsPerPage,
        "start" -> (page * ResultsPerPage).max(maxRecords),
        "sort" -> "score desc"
      )
      if (options.languages.nonEmpty) params :+= "fq" -> options.languages.map(l => s"""language:"${l.id}"""").mkString(" OR ")
      if (options.mediatypes.nonEmpty) params :+= "fq" -> options.mediatypes.map(m => s"""mediatype:"${m.id}"""").mkString(" OR ")
      if (options.collections.nonEmpty) params :+= "fq" -> options.collections.map(c => s"""collection:"${c.id}"""").mkString(" OR ")
      val query = params.map{case (k,v) => s"$k=$v"}.mkString("&")
      post.setEntity(new UrlEncodedFormEntity(Seq(new BasicNameValuePair("query", query)).asJava))
      Try {
        val in = client.execute(post).getEntity.getContent
        val response = try {
          Source.fromInputStream(in).mkString
        } finally {
          in.close()
        }
        val json = JsonMethods.parse(response).asInstanceOf[JObject].values
        json.get("response").flatMap { case response: Map[String, Any] =>
          response.get("docs").map { case docs: Seq[Any] =>
            docs.flatMap{case doc: Map[String, Any] => MhlSearchResult.fromMap(doc)}
          }
        }.getOrElse(Seq.empty)
      }.getOrElse(Seq.empty)
    }
  }

  override def parse(result: MhlSearchResult): Option[MhlSearchRecord] = {
    val url = "https://archive.org/details/" + result.id
    Some(new MhlSearchRecord(result, new IATxtBookAccessor(url)))
  }
}

object MhlSearchSpec {
  val MhlSearchUrl = "http://mhl.countway.harvard.edu/proxy/proxy.php"
  val ResultsPerPage = 100
  val DefaultMaxRecords = 1000

  def apply(options: MhlSearchOptions, maxRecords: Int = DefaultMaxRecords): MhlSearchSpec = new MhlSearchSpec(options, maxRecords)
}
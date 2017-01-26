package edu.harvard.countway.mhl.archivespark

import scala.util.Try

case class MhlSearchResult (
  id: String,
  title: String,
  author: Seq[String],
  date: String,
  subject: Seq[String],
  language: Seq[String],
  mediatype: Seq[String],
  collection: Seq[String],
  description: Seq[String],
  score: Double
)

object MhlSearchResult {
  def fromMap(map: Map[String, Any]): Option[MhlSearchResult] = Try {
    MhlSearchResult(
      map.get("id").map(_.asInstanceOf[String]).getOrElse(""),
      map.get("title").map(_.asInstanceOf[String]).getOrElse(""),
      map.get("author").map(_.asInstanceOf[Seq[String]]).getOrElse(Seq.empty),
      map.get("date").map(_.asInstanceOf[String]).getOrElse(""),
      map.get("subject").map(_.asInstanceOf[Seq[String]]).getOrElse(Seq.empty),
      map.get("language").map(_.asInstanceOf[Seq[String]]).getOrElse(Seq.empty),
      map.get("mediatype").map(_.asInstanceOf[Seq[String]]).getOrElse(Seq.empty),
      map.get("collection").map(_.asInstanceOf[Seq[String]]).getOrElse(Seq.empty),
      map.get("description").map(_.asInstanceOf[Seq[String]]).getOrElse(Seq.empty),
      map.get("score").map(_.asInstanceOf[Double]).getOrElse(0.0)
    )
  }.toOption
}
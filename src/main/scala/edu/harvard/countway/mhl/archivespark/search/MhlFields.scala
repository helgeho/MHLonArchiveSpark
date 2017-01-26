package edu.harvard.countway.mhl.archivespark.search

object MhlFields {
  case class MhlField(id: String)

  val Title = MhlField("title")
  val Volume = MhlField("volume")
  val Author = MhlField("author")
  val Contributor = MhlField("contributor")
  val Publisher = MhlField("publisher")
  val Subject = MhlField("subject")
  val Description = MhlField("description")
}
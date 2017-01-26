package edu.harvard.countway.mhl.archivespark.search

object MhlMediaTypes {
  case class MhlMediaType(id: String)
  private def set(ids: String*) = ids.toSet.map(MhlMediaType)
  
  val Audio = set("audio")
  val Collection = set("collection")
  val Movies = set("movies")
  val Other = set("other")
  val Texts = set("texts")

  def All = Audio ++ Collection ++ Movies ++ Other ++ Texts
}
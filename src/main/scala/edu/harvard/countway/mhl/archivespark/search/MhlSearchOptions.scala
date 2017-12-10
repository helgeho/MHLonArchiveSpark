package edu.harvard.countway.mhl.archivespark.search

case class MhlSearchOptions(
  query: String,
  conjunctive: Boolean = true,
  field: Option[MhlFields.MhlField] = None,
  languages: Set[MhlLanguages.MhlLanguage] = MhlLanguages.All,
  mediatypes: Set[MhlMediaTypes.MhlMediaType] = MhlMediaTypes.All,
  collections: Set[MhlCollections.MhlCollection] = MhlCollections.All
)
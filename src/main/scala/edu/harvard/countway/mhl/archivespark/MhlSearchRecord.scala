package edu.harvard.countway.mhl.archivespark

import de.l3s.archivespark.dataspecs.DataEnrichRoot
import de.l3s.archivespark.dataspecs.access.DataAccessor
import de.l3s.archivespark.enrich.RootEnrichFunc
import de.l3s.archivespark.enrich.dataloads.TextLoad
import de.l3s.archivespark.enrich.functions.Text

class MhlSearchRecord(result: MhlSearchResult, data: DataAccessor[String]) extends DataEnrichRoot[MhlSearchResult, String](result) with TextLoad {
  override def access[R >: Null](action: String => R): R = data.access(action)

  override def defaultEnrichFunction(field: String): Option[RootEnrichFunc[_]] = field match {
    case TextLoad.Field => Some(Text)
    case _ => None
  }
}

object MhlSearchRecord {
  implicit def recordToMeta(record: MhlSearchRecord): MhlSearchResult = record.get
}
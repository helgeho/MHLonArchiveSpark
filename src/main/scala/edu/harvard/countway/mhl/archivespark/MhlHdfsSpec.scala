package edu.harvard.countway.mhl.archivespark

import de.l3s.archivespark.specific.books.{IATxtBooksDjvuHdfsSpec, IATxtBooksHdfsSpec}

object MhlHdfsSpec {
  def apply(metaPath: String): IATxtBooksHdfsSpec = IATxtBooksHdfsSpec(metaPath)
  def apply(metaPath: String, djvuPath: String): IATxtBooksDjvuHdfsSpec = IATxtBooksDjvuHdfsSpec(metaPath, djvuPath)
}

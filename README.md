# [*Medical Heritage Library*](http://www.medicalheritage.org) on [ArchiveSpark](https://github.com/helgeho/ArchiveSpark)

This project contains the required components for [ArchiveSpark](https://github.com/helgeho/ArchiveSpark) to work with [*Medical Heritage Library*](http://www.medicalheritage.org) (MHL) collections.
 
We currently support metadata in the form of XML files with contents in txt files, both stored locally or contents being fetched remotely from the [Internet Archive](http://archive.org).
In addition to that, we support completely remote queries by utilizing [MHL's advanced full-text search](http://mhl.countway.harvard.edu/search/) to retrieve the required metadata.
 
The following ArchiveSpark *DataSpecs* are included:
* ***MhlHdfsSpec* (with local data)**: Loads data from local files (from HDFS). It takes two parameters: `MhlHdfsSpec(metaPath, txtPath)`
* ***MhlHdfsSpec* (with remote data)**: Loads metadata from local files (from HDFS) and fetches contents remotely: `MhlHdfsSpec(metaPath)`
* ***MhlSearchSpec***: Queries the MHL search system and fetches contents remotely. A query can be specified by passing in [*MhlSearchOptions*](src/main/scala/edu/harvard/countway/mhl/archivespark/search/MhlSearchOptions.scala): `MhlSearchSpec(options)`.

[*MhlSearchOptions*](src/main/scala/edu/harvard/countway/mhl/archivespark/search/MhlSearchOptions.scala) allow to specify a *query*, whether it is a *conjunctive* or disjunctive query, which *fields* should be queried, as well as the desired *languages*, *mediatypes* and *collections*.
The available options are provided by specialized objects under [*edu.harvard.countway.mhl.archivespark.search*](src/main/scala/edu/harvard/countway/mhl/archivespark/search).

An example is provided as [Jupyter](http://jupyter.org/) notebook under: [*MhlPolioSymptomsSearch*](examples/MhlPolioSymptomsSearch.ipynb).

## Usage

To use this library, we recommend an interactive environment, such as [*Jupyter*](http://jupyter.org/) in combination with [*Toree*](https://toree.apache.org/*) to run [*Spark*](http://spark.apache.org/) instructions on a cluster.

Please make sure that you have both [ArchiveSpark](https://github.com/helgeho/ArchiveSpark) as well as this *MHLonArchiveSpark* library in your classpath. 

To build a JAR file of this project, we recommend to use [*SBT*](http://www.scala-sbt.org/). As the project depends on the [*Internet Archive Books on ArchiveSpark*](https://github.com/helgeho/IABooksOnArchiveSpark) project, you will first need to publish that one locally, before you can build this:
```
git clone https://github.com/helgeho/IABooksOnArchiveSpark.git
cd IABooksOnArchiveSpark
sbt publishLocal
cd ..
git clone https://github.com/helgeho/MHLonArchiveSpark.git
cd MHLonArchiveSpark
sbt assembly
```

Now the resulting JAR file should be located under `MHLonArchiveSpark/target/mhl-archivespark-assembly-1.0.0.jar`.
package edu.harvard.countway.mhl.archivespark.search

object MhlLanguages {
  case class MhlLanguage(id: String)
  private def set(ids: String*) = ids.toSet.map(MhlLanguage)

  val Arabic = set("ara", "arabic")
  val Catalan = set("cat")
  val Church_Slavic = set("chu")
  val Czech = set("cze")
  val Danish = set("dan")
  val Dutch = set("dut")
  val English = set("eng", "english")
  val Finnish = set("fin")
  val French = set("fre", "french")
  val German = set("ger")
  val Greek_ancient = set("grc")
  val Greek_modern = set("gre")
  val Handwritten = set("handwritten")
  val Hebrew_modern = set("heb")
  val Hindi = set("hin")
  val Hungarian = set("hun")
  val Irish = set("iri")
  val Italian = set("ita")
  val Japanese = set("jpn")
  val Latin = set("lat")
  val Multiple_Languages = set("mul")
  val Norwegian = set("nor")
  val Ottoman_Turkish = set("ota")
  val Persian = set("per")
  val Polish = set("pol")
  val Portuguese = set("por")
  val Russian = set("rus")
  val Sanskrit = set("san")
  val Spanish = set("spa")
  val Swedish = set("swe")
  val Undetermined = set("und")
  val Urdu = set("urd")
  val Vietnamese = set("vietnamese")
  val Yiddish = set("yid")
  val Other_arabic = set("arabic")
  val Other_chi = set("chi")
  val Other_egy = set("egy")
  val Other_fse = set("fse")
  val Other_gae = set("gae")
  val Other_ges = set("ges")
  val Other_goh = set("goh")
  val Other_ice = set("ice")
  val Other_latin = set("latin")
  val Other_nob = set("nob")
  val Other_persian = set("persian")
  val Other_rum = set("rum")
  val Other_srp = set("srp")
  val Other_wel = set("wel")

  def All = Arabic ++ Catalan ++ Church_Slavic ++ Czech ++ Danish ++ Dutch ++ English ++ Finnish ++ French ++ German ++ Greek_ancient ++ Greek_modern ++ Handwritten ++ Hebrew_modern ++ Hindi ++ Hungarian ++ Irish ++ Italian ++ Japanese ++ Latin ++ Multiple_Languages ++ Norwegian ++ Ottoman_Turkish ++ Persian ++ Polish ++ Portuguese ++ Russian ++ Sanskrit ++ Spanish ++ Swedish ++ Undetermined ++ Urdu ++ Vietnamese ++ Yiddish ++ Other_chi ++ Other_egy ++ Other_fse ++ Other_gae ++ Other_ges ++ Other_goh ++ Other_ice ++ Other_latin ++ Other_nob ++ Other_persian ++ Other_rum ++ Other_srp ++ Other_wel
}

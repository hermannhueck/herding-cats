val catsVersion = "1.0.1"
val catsCore = "org.typelevel" %% "cats-core" % catsVersion
val catsFree = "org.typelevel" %% "cats-free" % catsVersion
val catsMtl = "org.typelevel" %% "cats-mtl-core" % "0.2.1"

val simulacrum = "com.github.mpilquist" %% "simulacrum" % "0.11.0"
val macroParadise = compilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full)
val kindProjector = compilerPlugin("org.spire-math" %% "kind-projector" % "0.9.4")
val resetAllAttrs = "org.scalamacros" %% "resetallattrs" % "1.0.0"

val specs2Version = "3.8.9" // use the version used by discipline
val specs2Core  = "org.specs2" %% "specs2-core" % specs2Version
val specs2Scalacheck = "org.specs2" %% "specs2-scalacheck" % specs2Version
val scalacheck = "org.scalacheck" %% "scalacheck" % "1.12.4"
val discipline = "org.typelevel" %% "discipline" % "0.9.0"
val catsLaws = "org.typelevel" %% "cats-laws" % catsVersion

lazy val packageSite = taskKey[Unit]("package site")
lazy val doPackageSite = taskKey[File]("package site")
lazy val packageSitePath = settingKey[File]("path for the package")

lazy val root = (project in file(".")).
  // enablePlugins(PamfletPlugin).
  settings(
    organization := "com.eed3si9n",
    name := "herding-cats",
    scalaVersion := "2.12.4",
    libraryDependencies ++= Seq(
      catsCore,
      catsFree,
      catsMtl,
      simulacrum,
      specs2Core % Test,
      specs2Scalacheck % Test,
      scalacheck % Test,
      discipline % Test,
      catsLaws, // % Test,
      macroParadise,
      kindProjector,
      resetAllAttrs
    ),
    scalacOptions ++= Seq(
      "-deprecation",
      "-encoding", "UTF-8",
      "-Ypartial-unification",
      "-feature",
      "-language:_"
    ),
    resolvers ++= Seq(
      Resolver.sonatypeRepo("releases"),
      Resolver.sonatypeRepo("snapshots")
    )
//  ).settings(
//    packageSitePath := target.value / "herding-cats.tar.gz",
//    doPackageSite := {
//      val out = packageSitePath.value
//      val siteDir = (target in (Pamflet, pfWrite)).value
//      val items = ((siteDir ** "*").get map { _.relativeTo(siteDir) }).flatten
//      Process(s"""tar zcf ${ packageSitePath.value.getAbsolutePath } ${ items.mkString(" ") }""", Some(siteDir)).!
//      out
//    },
//    packageSite := Def.sequential(/*clean,*/ pfWrite, doPackageSite).value
  )

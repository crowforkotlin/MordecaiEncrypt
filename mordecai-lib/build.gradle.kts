plugins {
  alias(libs.plugins.kotlinJvm)
  alias(libs.plugins.kotlinSerialization)
}

dependencies {
  compileOnly(libs.ksp.api)
  implementation(libs.kotlinpoet)
//  implementation(projects.providerApi)
  implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.1")
}
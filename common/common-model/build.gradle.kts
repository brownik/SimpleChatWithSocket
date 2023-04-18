plugins {
    id("brownik.android.library")
    id("kotlin-parcelize")
    id("kotlinx-serialization")
}

android {
    namespace = "com.brownik.sockettest.common_model"
}

dependencies {

    implementation(projects.common.commonResource)

    implementation(libs.coroutine.core)
    implementation(libs.coroutine.android)
    implementation(libs.google.gson)
    implementation(libs.okhttp)
    implementation(libs.kotlinx.serialization.json)
}
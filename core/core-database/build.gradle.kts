plugins {
    id("brownik.android.library")
    id("brownik.android.hilt")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.brownik.sockettest.core_database"

    kapt { correctErrorTypes = true }
}

dependencies {
    implementation(projects.common.commonModel)
    implementation(projects.common.commonUtil)

    implementation(libs.google.firebase.database)
}
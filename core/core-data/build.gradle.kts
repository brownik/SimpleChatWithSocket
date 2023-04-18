plugins {
    id("brownik.android.library")
    id("brownik.android.hilt")
}

dependencies {
    implementation(projects.common.commonModel)
    implementation(projects.core.coreNetwork)
    implementation(projects.common.commonUtil)

    implementation(libs.coroutine.core)
    implementation(libs.coroutine.android)

    implementation(libs.google.gson) // Gson
    implementation(libs.okhttp)

    implementation(libs.junit)
}
plugins {
    id("brownik.android.library")
}

android {
    namespace = "com.brownik.sockettest.common_resource"
}

dependencies {
    implementation(libs.androidx.core)
    implementation(libs.androidx.appcompat)
    implementation(libs.android.material)
}
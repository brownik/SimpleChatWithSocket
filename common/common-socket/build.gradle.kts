plugins {
    id("brownik.android.library")
    id("brownik.android.hilt")
}

android {
    namespace = "com.brownik.sockettest.common_socket"
}

dependencies {
    implementation(projects.common.commonUtil)

    implementation(libs.socketio)
}
plugins {
    id("brownik.android.library")
    id("brownik.android.hilt")
    id("kotlinx-serialization")
}

android {
    namespace = "com.brownik.sockettest.core_network"

    kapt { correctErrorTypes = true }
}

dependencies {
}

plugins {
    id("brownik.android.library")
    id("brownik.android.hilt")
}

android {
    namespace = "com.brownik.sockettest.common_base"

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

dependencies {
    implementation(projects.core.coreData)
    implementation(projects.common.commonUtil)
    implementation(projects.common.commonModel)
    implementation(projects.common.commonResource)

    implementation(libs.coroutine.core)
    implementation(libs.coroutine.android)

    implementation(libs.androidx.core)
    implementation(libs.androidx.appcompat)
    implementation(libs.android.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.fragment)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.lifecycle.extension)
    implementation(libs.androidx.lifecycle.common)
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.androidx.lifecycle.viewmodel.savedstate)
    implementation(libs.androidx.lifecycle.livedata)
    implementation(libs.androidx.lifecycle.runtime)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso)
    implementation(libs.google.gson)
}
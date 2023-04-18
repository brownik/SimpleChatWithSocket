import com.brownik.sockettest.build_logic.VersionConstants
import java.text.SimpleDateFormat
import java.util.*

plugins {
    id("brownik.android.application")
    id("brownik.android.hilt")
    id("brownik.android.feature")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.brownik.sockettest"

    val extensionAware = this as ExtensionAware

    fun getFileName(): String {
        val formattedDate = SimpleDateFormat("yyMMdd", Locale.getDefault()).format(Date())
        val versionName = VersionConstants.VERSION_NAME.replace(".", "_")
        return "manager_v${versionName}_${formattedDate}"
    }

    defaultConfig {
        multiDexEnabled = true
        applicationId = VersionConstants.APPLICATION_ID
        versionCode = VersionConstants.VERSION_CODE
        versionName = VersionConstants.VERSION_NAME
    }

    // kotlin 주석 처리 도구 인 kaptlin이 주석 처리 중에 발생한 오류를 보고 할 때 생성 된 Java 스텁 대신 원래 Kotlin 코드의 위치에 대한 링크를 제공.
    kapt {
        mapDiagnosticLocations = true
        correctErrorTypes = true
    }

    lintOptions {
        isCheckReleaseBuilds = false
        isAbortOnError = false
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
            applicationVariants.all {
                outputs.map { it as com.android.build.gradle.internal.api.BaseVariantOutputImpl }
                    .forEach { output ->
                        output.outputFileName = "${getFileName()}.apk"
                    }
            }
        }
    }
}

dependencies {

    implementation(libs.androidx.core)
    implementation(libs.androidx.multidex)
    implementation(libs.androidx.appcompat)
    implementation(libs.android.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.fragment)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.viewpager2)
    implementation(libs.androidx.constraintlayout)

    implementation(libs.socketio)

    implementation(libs.androidx.lifecycle.extension)
    implementation(libs.androidx.lifecycle.common)
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.androidx.lifecycle.viewmodel.savedstate)
    implementation(libs.androidx.lifecycle.livedata)
    implementation(libs.androidx.lifecycle.runtime)
    implementation(libs.androidx.legacy.support)

    implementation(libs.google.gson) // Gson
}
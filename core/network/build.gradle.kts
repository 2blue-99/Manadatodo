import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    alias(libs.plugins.mandatodo.android.library)
    alias(libs.plugins.mandatodo.android.hilt)
//    alias(libs.plugins.mandatodo.android.library.compose)
//    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}

android {
    buildFeatures {
        buildConfig = true
    }
    namespace = "com.mandatodo.core.network"
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}




dependencies {
//    implementation(libs.android.material)
//    implementation(libs.supabase.compose.auth)
//    implementation(libs.supabase.gotrue)
//    implementation(libs.supabase.postgrest)
//    implementation(libs.ktor.client.cio)

//    implementation(libs.retrofit.core)
//    implementation(libs.kotlin.serialization)
//    implementation(libs.okhttp.logging)
}
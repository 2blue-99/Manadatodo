import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    alias(libs.plugins.mandatodo.android.library)
    alias(libs.plugins.mandatodo.android.hilt)
    alias(libs.plugins.mandatodo.android.library.compose)
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
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
//    defaultConfig{
//        buildConfigField("String", "Url",getUrl("Url"))
//        buildConfigField("String", "Key",getKey("Key"))
//        buildConfigField("String", "Id",getId("Id"))
//    }
}

//fun getUrl(propertyKey: String): String = gradleLocalProperties(rootDir).getProperty(propertyKey)
//fun getKey(propertyKey: String): String = gradleLocalProperties(rootDir).getProperty(propertyKey)
//fun getId(propertyKey: String): String = gradleLocalProperties(rootDir).getProperty(propertyKey)


dependencies {
    implementation(libs.android.material)
    implementation(libs.supabase.compose.auth)
    implementation(libs.supabase.gotrue)
    implementation(libs.supabase.postgrest)
    implementation("io.ktor:ktor-client-cio:2.3.7")

//    implementation(libs.retrofit.core)
//    implementation(libs.kotlin.serialization)
//    implementation(libs.okhttp.logging)
}
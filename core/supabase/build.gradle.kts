plugins {
    alias(libs.plugins.mandatodo.android.library)
    alias(libs.plugins.mandatodo.android.hilt)
    alias(libs.plugins.mandatodo.android.library.compose)
    alias(libs.plugins.mandatodo.android.room)
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}

android {
    buildFeatures {
        buildConfig = true
    }
    namespace = "com.manadatodo.core.supabase"
}

dependencies {
    implementation(projects.core.model)

    implementation(libs.android.material)
    implementation(libs.supabase.compose.auth)
    implementation(libs.supabase.gotrue)
    implementation(libs.supabase.postgrest)
    implementation(libs.ktor.client.cio)
}
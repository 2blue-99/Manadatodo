plugins {
    alias(libs.plugins.mandatodo.android.library)
    alias(libs.plugins.mandatodo.android.hilt)
}

android {
    namespace = "com.mandatodo.core.domain"
}

dependencies {
    implementation(projects.core.data)
    implementation(projects.core.datastore)
    implementation(libs.supabase.compose.auth)
}
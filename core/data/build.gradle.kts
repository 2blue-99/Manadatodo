plugins {
    alias(libs.plugins.mandatodo.android.library)
    alias(libs.plugins.mandatodo.android.hilt)
}

android {
    namespace = "com.mandatodo.core.data"
}

dependencies {
    implementation(projects.core.network)
    implementation(projects.core.datastore)


    implementation(libs.supabase.compose.auth)
}
plugins {
    alias(libs.plugins.mandatodo.android.library)
    alias(libs.plugins.mandatodo.android.hilt)
    alias(libs.plugins.mandatodo.android.room)
}

android {
    namespace = "com.mandatodo.core.data"
}

dependencies {
//    implementation(projects.core.network)
    implementation(projects.core.datastore)
    implementation(projects.core.supabase)
    implementation(projects.core.database)
    implementation(projects.core.model)

    implementation(libs.androidx.tracing.ktx)
    implementation(libs.androidx.startup.runtime)
    implementation(libs.androidx.work.ktx)
    implementation(libs.hilt.ext.work)

    implementation(libs.supabase.compose.auth)
}
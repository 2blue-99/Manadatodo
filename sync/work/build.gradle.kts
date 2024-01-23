plugins {
    alias(libs.plugins.mandatodo.android.library)
    alias(libs.plugins.mandatodo.android.hilt)
}

android {
    namespace = "com.mandatodo.sync.work"
}

dependencies {
    implementation(libs.androidx.work.ktx)
    implementation(libs.hilt.ext.work)

    implementation(projects.core.data)

    implementation(libs.androidx.tracing.ktx)

    implementation(libs.androidx.startup.runtime)
//    implementation(projects.core.datastore)
//    implementation(projects.core.database)
//    implementation(projects.core.model)
//    implementation(libs.supabase.compose.auth)
}
plugins {
    alias(libs.plugins.mandatodo.android.library)
    alias(libs.plugins.mandatodo.android.hilt)
}

android {
    namespace = "com.mandatodo.core.datastore"
}

dependencies {
    implementation(libs.datastore.preferences.core)
    implementation("androidx.datastore:datastore-preferences:1.0.0")
}
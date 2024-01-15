plugins {
    alias(libs.plugins.mandatodo.android.library)
    alias(libs.plugins.mandatodo.android.hilt)
//    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.mandatodo.core.datastore"
}

dependencies {
    implementation(libs.datastore.preferences)
}
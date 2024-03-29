plugins {
    alias(libs.plugins.mandatodo.android.feature)
    alias(libs.plugins.mandatodo.android.library.compose)
    alias(libs.plugins.mandatodo.android.hilt)
}

android {
    namespace = "com.mandatodo.feature.login"
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.android.material)
    testImplementation(libs.junit4)
    implementation(libs.androidx.activity.compose)
    implementation(libs.supabase.compose.auth)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.test.espresso.core)
}
plugins {
    alias(libs.plugins.mandatodo.android.feature)
    alias(libs.plugins.mandatodo.android.library.compose)
}

android {
    namespace = "com.mandatodo.feature.history"
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.android.material)
    testImplementation(libs.junit4)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.test.espresso.core)
    implementation(libs.androidx.activity.compose)
}
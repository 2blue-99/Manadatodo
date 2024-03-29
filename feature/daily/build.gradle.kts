plugins {
    alias(libs.plugins.mandatodo.android.feature)
    alias(libs.plugins.mandatodo.android.library.compose)
    alias(libs.plugins.mandatodo.android.hilt)
}

android {
    namespace = "com.mandatodo.feature.daily"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.core.database)
    implementation(projects.core.designsystem)
//    implementation(projects.sync.work)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.android.material)
    testImplementation(libs.junit4)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.test.espresso.core)
    implementation(libs.androidx.activity.compose)
}
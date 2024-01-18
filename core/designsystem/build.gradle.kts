plugins {
    alias(libs.plugins.mandatodo.android.library)
    alias(libs.plugins.mandatodo.android.library.compose)
}

android {
    namespace = "com.mandatodo.core.designsystem"
}

dependencies {
    implementation(projects.core.model)

    implementation(libs.androidx.core)
    implementation (libs.androidx.appcompat)

    api(libs.androidx.compose.foundation)
    api(libs.androidx.compose.foundation.layout)
    api(libs.androidx.compose.material.iconsExtended)
    api(libs.androidx.compose.material3)
    api(libs.androidx.compose.runtime)
    api(libs.androidx.compose.ui.tooling.preview)
    api(libs.androidx.compose.ui.util)
    api(libs.androidx.compose.ui.util)

    implementation(libs.androidx.core.ktx)
    implementation(libs.coil.kt.compose)

    debugApi(libs.androidx.compose.ui.tooling)
}
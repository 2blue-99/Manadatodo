import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17

}
tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}
dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
}
gradlePlugin{
    plugins{
        register("androidApplication"){
            id = "mandatodo.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidLibrary") {
            id = "mandatodo.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("jvmLibrary") {
            id = "mandatodo.jvm.library"
            implementationClass = "JvmLibraryConventionPlugin"
        }
        register("androidHilt") {
            id = "mandatodo.android.hilt"
            implementationClass = "AndroidHiltConventionPlugin"
        }
        register("androidRoom") {
            id = "mandatodo.android.room"
            implementationClass = "AndroidRoomConventionPlugin"
        }
        register("androidFeature") {
            id = "mandatodo.android.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }
        // 앱에서만 써먹기
        register("androidApplicationCompose") {
            id = "mandatodo.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        // 앱을 포함한 이외의 곳에서 써먹기
        register("androidLibraryCompose") {
            id = "mandatodo.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
    }
}
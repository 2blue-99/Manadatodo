package com.blue.mandatodo

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureAndroidCompose(
    commonExtension: CommonExtension<*,*,*,*,*>
){
    commonExtension.apply {
        buildFeatures{
            compose = true
        }
        composeOptions{
            kotlinCompilerExtensionVersion = libs.findVersion("androidxComposeCompiler").get().toString()
        }
        dependencies {
            val bom = libs.findLibrary("androidx-compose-bom").get()
            add("implementation", platform(bom))
            add("androidTestImplementation", platform(bom))

//            add("debugImplementation", libs.findLibrary("androidx.compose.ui.testManifest").get())

            // 버전 카탈로그에서 가져오는 라이브러리임, 즉 버전 카탈로그에 존재해야 동작함
            add("implementation", libs.findLibrary("androidx.compose.material3").get())
            add("implementation", libs.findLibrary("androidx.compose.ui.util").get())
            add("implementation", libs.findLibrary("androidx.compose.ui.tooling.preview").get())


        }
    }

}
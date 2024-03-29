import com.blue.mandatodo.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("mandatodo.android.library")
                apply("mandatodo.android.hilt")
            }

            dependencies {
                add("implementation", project(":core:data"))
                add("implementation", project(":core:datastore"))
                add("implementation", project(":core:designsystem"))
                add("implementation", project(":core:domain"))
                add("implementation", project(":core:model"))
                add("implementation", project(":core:network"))

                add("implementation", libs.findLibrary("androidx.hilt.navigation.compose").get())
                add("implementation", libs.findLibrary("androidx.lifecycle.runtimeCompose").get())
                add("implementation", libs.findLibrary("androidx.lifecycle.viewModelCompose").get())

                add("implementation", libs.findLibrary("coil.kt").get())
                add("implementation", libs.findLibrary("coil.kt.compose").get())

                add("implementation", libs.findLibrary("androidx.lifecycle.livedata.ktx").get())
                add("implementation", libs.findLibrary("androidx.lifecycle.runtime.ktx").get())
                add("implementation", libs.findLibrary("androidx.lifecycle.viewmodel.ktx").get())

                add("implementation", libs.findLibrary("kotlinx.coroutines.android").get())
            }
        }
    }
}

package com.kurt.myapplication.plugins.extensions

import com.kurt.myapplication.dependencies.MultiplatformDependencies
import org.gradle.api.Project
import org.gradle.api.tasks.Sync
import org.gradle.kotlin.dsl.*
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.mpp.Framework
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinNativeLink

fun Project.setupKotlinMultiplatform(frameworkName: String) {
    val kotlinMultiplatformExtension = project.extensions.getByType<KotlinMultiplatformExtension>()
    kotlinMultiplatformExtension.apply {

        /* Use for 1.3.50 */
//        val iOSTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget =
//            if (System.getenv("SDK_NAME")?.startsWith("iphoneos") == true)
//                ::iosArm64
//            else
//                ::iosX64
//
//        iOSTarget("ios") {
//            binaries {
//                framework(frameworkName) {
//                    baseName = frameworkName
//                    if (iOSTarget == presets.getByName("iosX64")) {
//                        embedBitcode("disable")
//                    }
//                }
//            }
//        }

        /* Use for 1.3.60 */
        ios {
            binaries {
                framework(frameworkName) {
                    baseName = frameworkName
                }
            }
        }
        android()

        val commonMain by sourceSets.getting {
            dependencies {
                MultiplatformDependencies.coreCommonDependencies.forEach { implementation(it) }
            }
        }

        val iosMain by sourceSets.getting {
            dependencies {
                MultiplatformDependencies.coreNativeDependencies.forEach { implementation(it) }
            }
        }
    }

    tasks.filterIsInstance<KotlinNativeLink>()
        .filter { it.binary is Framework }
        .forEach {
            val framework = it.binary
            if (framework is Framework) {
                val linkTask = framework.linkTask
                val syncTaskName = linkTask.name.replaceFirst("link", "sync")
                val syncFramework = tasks.create(syncTaskName, Sync::class) {
                    group = "cocoapods"
                    from(framework.outputDirectory)
                    into(file("build/cocoapods/framework"))
                }
                syncFramework.dependsOn(linkTask)
            }
        }
}
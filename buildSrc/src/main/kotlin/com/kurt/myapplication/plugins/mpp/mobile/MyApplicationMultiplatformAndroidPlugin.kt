package com.kurt.myapplication.plugins.mpp.mobile

import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import com.kurt.myapplication.dependencies.MultiplatformDependencies
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

/**
 * Copyright 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 11/13/2019
 */

open class MyApplicationMultiplatformAndroidPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        val libraryExtension = project.extensions.getByType<LibraryExtension>()
        libraryExtension.configureAndroidDirectory()
        libraryExtension.configureAndroidLibrary()
        project.configureAndroidDependencies()
    }

    private fun BaseExtension.configureCommonAndroid() {
        compileSdkVersion(Properties.COMPILE_SDK_VERSION)
        defaultConfig {
            minSdkVersion(Properties.MIN_SDK_VERSION)
            targetSdkVersion(Properties.TARGET_SDK_VERSION)
            testInstrumentationRunner = "com.kurt.myapplication.test.utils.StarkApplicationTestRunner"
        }

        flavorDimensions("environment")
    }

    private fun LibraryExtension.configureAndroidLibrary() {
        configureCommonAndroid()

        buildTypes {
            getByName("release") {
                isMinifyEnabled = true
                consumerProguardFiles("consumer-rules.pro")
            }
        }

        productFlavors {
            create("dev")
            create("production")
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }
    }

    private fun LibraryExtension.configureAndroidDirectory() {
        sourceSets {
            getByName("main") {
                manifest.srcFile("src/androidMain/AndroidManifest.xml")
                java.srcDirs("src/androidMain/kotlin")
                res.srcDirs("src/androidMain/res")
            }
        }
    }

    private fun Project.configureAndroidDependencies() = dependencies {
        MultiplatformDependencies.coreAndroidDependencies.forEach {
            add("implementation", it)
        }
    }
}
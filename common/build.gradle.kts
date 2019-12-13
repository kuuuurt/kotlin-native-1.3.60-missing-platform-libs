import com.kurt.myapplication.plugins.extensions.setupKotlinMultiplatform

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.multiplatform")
    id("org.jetbrains.kotlin.native.cocoapods")
    id("com.kurt.myapplication.multiplatform.android")
}

setupKotlinMultiplatform("Common")

version = "1.0.0"

kotlin {
    cocoapods {
        summary = "Shared Common module for Android and iOS"
        homepage = "Link to a Kotlin/Native module homepage"
    }
}
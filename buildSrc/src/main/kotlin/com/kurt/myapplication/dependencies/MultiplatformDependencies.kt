package com.kurt.myapplication.dependencies

/**
 * Copyright 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 11/13/2019
 */

object MultiplatformDependencies {
    object Common {
        const val KOTLIN = "org.jetbrains.kotlin:kotlin-stdlib-common"
    }

    object Native {
        const val KOTLIN = "org.jetbrains.kotlin:kotlin-stdlib"
    }

    object Android {
        const val KOTLIN = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${GlobalVersions.KOTLIN}"
    }

    val coreCommonDependencies = listOf(Common.KOTLIN)
    val coreNativeDependencies = listOf(Native.KOTLIN)
    val coreAndroidDependencies = listOf(Android.KOTLIN)
}
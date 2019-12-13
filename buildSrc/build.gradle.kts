plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    google()
    jcenter()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.61")
    implementation("com.android.tools.build:gradle:3.5.0")

    implementation(gradleApi())
    implementation(localGroovy())
}
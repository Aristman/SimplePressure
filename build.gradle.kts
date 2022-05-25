plugins {
    id("com.android.application") version Versions.gradle apply false
    id("com.android.library") version Versions.gradle apply false
    id("org.jetbrains.kotlin.android") version Versions.kotlin apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}")
    }
}

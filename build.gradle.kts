// Top-level build.gradle.kts
/*
buildscript {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://jitpack.io") // ✅ JitPack repo
    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.2.0")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.22")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://jitpack.io") // ✅ Ensure all modules see JitPack
    }
}
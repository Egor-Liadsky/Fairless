pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven { url = uri("https://oss.sonatype.org/content/repositories/snapshots") } // only needed for SNAPSHOT
    }
    plugins {
        id("com.android.library").version("7.4.0").apply(false)
        id("com.codingfeline.buildkonfig").version("0.13.3").apply(false)
        kotlin("multiplatform").version("1.8.0").apply(false)
        kotlin("plugin.serialization").version("1.8.0").apply(false)
        id("com.squareup.sqldelight").version("1.5.5").apply(false)
        id("com.google.gms.google-services").version("4.3.15").apply(false)
        id("com.google.firebase.crashlytics").version("2.9.5").apply(false)

        id("com.android.application").version("7.4.0").apply(false)
        kotlin("android").version("1.7.20").apply(false)
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://oss.sonatype.org/content/repositories/snapshots") } // only needed for SNAPSHOT

    }
}

rootProject.name = "Fairless"
include(":androidApp")
include(":shared")

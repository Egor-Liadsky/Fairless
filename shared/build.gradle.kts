import com.codingfeline.buildkonfig.compiler.FieldSpec.Type

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    kotlin("plugin.serialization")
    id("com.squareup.sqldelight")
    id("com.codingfeline.buildkonfig")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
}

val androidPackageName = "com.mobile.fairless"

kotlin {
    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                // DI
                implementation("io.insert-koin:koin-core:3.3.2")

                // Kotlinx
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")

                // Network
                implementation("io.ktor:ktor-client-core:2.2.2")
                implementation("io.ktor:ktor-client-logging:2.2.2")
                implementation("io.ktor:ktor-client-okhttp:2.2.2")
                implementation("io.ktor:ktor-server-content-negotiation:2.2.2")
                implementation("io.ktor:ktor-serialization-kotlinx-json:2.2.2")


                // Database
                implementation("com.squareup.sqldelight:runtime:1.5.4")
                implementation("com.squareup.sqldelight:coroutines-extensions:1.5.4")

                // Settings
                implementation("com.russhwolf:multiplatform-settings:1.0.0")
            }
        }

        val androidMain by getting {
            dependencies {
                // AppMetrica
                implementation("com.yandex.android:mobmetricalib:5.2.0")

                // Firebase
                implementation(platform("com.google.firebase:firebase-bom:31.5.0"))
                implementation("com.google.firebase:firebase-messaging-ktx")
                implementation("com.google.firebase:firebase-analytics-ktx")
                implementation("com.google.firebase:firebase-crashlytics-ndk")

                // Network
                implementation("io.ktor:ktor-client-android:2.2.2")

                // Database
                implementation("com.squareup.sqldelight:android-driver:1.5.5")

                // Adfox
                implementation("com.yandex.android:mobileads:5.6.0")

                // Serializer
                implementation("com.google.code.gson:gson:2.8.8")
            }
        }

        val iosMain by creating {
            dependsOn(commonMain)
            dependencies {
                // Network
                implementation("io.ktor:ktor-client-ios:2.2.2")

                // Database
                implementation("com.squareup.sqldelight:native-driver:1.5.5")
            }
        }
    }
}

buildkonfig {
    packageName = androidPackageName

    defaultConfigs() {
        buildConfigField(Type.STRING, "BASE_URL", "https://api.fairless.ru")
    }
    targetConfigs {
        create("android") {
        }
        create("ios") {
        }
    }
}

android {
    namespace = androidPackageName
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
}

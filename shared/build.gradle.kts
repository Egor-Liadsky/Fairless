import com.codingfeline.buildkonfig.compiler.FieldSpec.Type

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    kotlin("plugin.serialization")
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
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
        }
    }
    
    sourceSets {
        val commonMain by getting {
            dependencies {
                // DI
                implementation("io.insert-koin:koin-core:3.4.1")

                // Kotlinx
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")

                // Network
                implementation("io.ktor:ktor-client-core:2.2.2")
                implementation("io.ktor:ktor-client-logging:2.2.2")
                implementation("io.ktor:ktor-server-content-negotiation:2.2.2")
                implementation("io.ktor:ktor-serialization-kotlinx-json:2.2.2")

                // Settings
                implementation("com.russhwolf:multiplatform-settings:1.0.0")

                // DateTime
                implementation("com.soywiz.korlibs.klock:klock:3.4.0")
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
                implementation("io.ktor:ktor-client-darwin:2.2.2")
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }

        val iosX64Test by getting {
            dependsOn(commonTest)
        }
    }
}

buildkonfig {
    packageName = androidPackageName

    defaultConfigs() {
        buildConfigField(Type.STRING, "BASE_URL", "https://api.fairless.ru")
        buildConfigField(Type.STRING, "APPMETRICA_KEY", "9e699ade-2774-4e21-92af-7b1838611f1a")
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

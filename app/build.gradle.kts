plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.blueapps.signproviderexampleapp"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "com.blueapps.signproviderexampleapp"
        minSdk = 24
        targetSdk = 37
        versionCode = 111
        versionName = "16.08.2026@1.1.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(project(":signprovider"))

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
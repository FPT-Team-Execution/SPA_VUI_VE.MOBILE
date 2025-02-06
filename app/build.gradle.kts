plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)

}


android {
    namespace = "com.spavv.m"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.spavv.m"
        minSdk = 24
        this.targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.12.0") // Hạ từ 1.15.0
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2") // Hạ từ 2.7.0
    implementation("androidx.activity:activity-compose:1.8.0") // Hạ từ 1.10.0
    implementation(platform("androidx.compose:compose-bom:2023.08.00")) // Hạ BOM version
    implementation("androidx.compose.ui:ui:1.5.3") // Hạ từ 1.6.x
    implementation("androidx.compose.ui:ui-graphics:1.5.3")
    implementation("androidx.compose.ui:ui-tooling-preview:1.5.3")
    implementation("androidx.compose.material3:material3:1.1.2") // Hạ từ 1.2.0
    implementation("androidx.navigation:navigation-compose:2.7.2") // Hạ từ 2.8.x
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.5.3")
    debugImplementation("androidx.compose.ui:ui-tooling:1.5.3")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.5.3")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2") // Hạ từ 2.7.0
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
}



plugins {
    id("com.android.application")
    id("kotlin-android")
}

android {
    namespace = "com.example"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.bug_7900"
        minSdk = 33
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.2"
    }
}

dependencies {
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.3.2")
    implementation("androidx.compose.compiler:compiler:1.3.2")
    implementation("androidx.compose.runtime:runtime-livedata:1.3.2")
    implementation("androidx.compose.material3:material3:1.0.1")
    implementation("androidx.compose.ui:ui-test-junit4:1.3.2")
    implementation("androidx.compose.ui:ui-tooling:1.3.2")
    // Temporary workaround, https://github.com/robolectric/robolectric/issues/6593#issuecomment-974858115
    testImplementation("androidx.test.espresso:espresso-core:3.5.0")
    testImplementation("org.robolectric:robolectric:4.9.2")
}

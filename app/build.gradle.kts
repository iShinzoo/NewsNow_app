plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.example.newsnow"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.newsnow"
        minSdk = 24
        targetSdk = 34
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

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2024.02.01"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2024.02.01"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.7.0-alpha03")


    // Preferences Database
    implementation("androidx.datastore:datastore-preferences-core:1.0.0")
    implementation("androidx.datastore:datastore-preferences-rxjava2:1.0.0")
    implementation("androidx.datastore:datastore-preferences-rxjava3:1.0.0")
    implementation("androidx.datastore:datastore-core:1.0.0")
    implementation("androidx.core:core-splashscreen:1.0.1")


    // dagger hilt
    implementation("com.google.dagger:hilt-android:2.51")
    kapt("com.google.dagger:hilt-android-compiler:2.51")
    //hilt ViewModel
    implementation ("androidx.hilt:hilt-navigation-compose:1.2.0")
    kapt ("androidx.hilt:hilt-compiler:1.2.0")

    // system ui controller
    implementation ("com.google.accompanist:accompanist-systemuicontroller:0.35.0-alpha")

    // Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

    // Paging 3 Library
    implementation("androidx.paging:paging-runtime-ktx:3.3.0-alpha03")
    // alternatively - without Android dependencies for tests
    testImplementation("androidx.paging:paging-common-ktx:3.3.0-alpha03")
    // optional - RxJava2 support
    implementation("androidx.paging:paging-rxjava2-ktx:3.3.0-alpha03")
    // optional - RxJava3 support
    implementation("androidx.paging:paging-rxjava3:3.3.0-alpha03")
    // optional - Guava ListenableFuture support
    implementation("androidx.paging:paging-guava:3.3.0-alpha03")
    // optional - Jetpack Compose integration
    implementation("androidx.paging:paging-compose:3.3.0-alpha03")

    //coil library for image
    implementation("io.coil-kt:coil-compose:2.6.0")

    //Compose Navigation
    implementation ("androidx.navigation:navigation-compose:2.8.0-alpha03")

    //Compose Foundation
    implementation ("androidx.compose.foundation:foundation:1.7.0-alpha03")

}
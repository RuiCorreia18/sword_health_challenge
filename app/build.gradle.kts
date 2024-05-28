plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.kotlin.ksp)
}

android {
    namespace = "com.example.swordhealthchallenge"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.swordhealthchallenge"
        minSdk = 30
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        buildConfigField("String", "BASE_URL", "\"https://api.thecatapi.com/v1/\"")
        buildConfigField("String", "API_KEY", "\"live_sZn9ieEBrBScfIom0dBVyHhrCIg1Bpfe1EIdH83yCbZ5vTQLp6J3qkwUhIUP47r1\"")

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    //Retrofit
    implementation(libs.retrofit2)
    implementation(libs.retrofit2.converter.gson)
    implementation(libs.rxjava3.retrofit.adapter)

    //RxJava
    implementation(libs.rxkotlin)
    implementation(libs.rxandroid)

    // Dagger
    ksp(libs.dagger.compiler)
    ksp(libs.dagger.android.processor)
    implementation(libs.dagger)
    implementation(libs.dagger.android)
    implementation(libs.dagger.android.support)

    //Glide
    ksp(libs.glide.compiler)
    implementation(libs.glide)

    testImplementation(libs.junit)
    testImplementation("io.mockk:mockk:1.13.3")
    testImplementation("androidx.arch.core:core-testing:2.1.0")
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
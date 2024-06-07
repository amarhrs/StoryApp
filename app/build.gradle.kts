plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.ksp)
    alias(libs.plugins.parcelize)
    alias(libs.plugins.googleAndroidLibrariesMapsplatformSecretsGradlePlugin)
}

android {
    namespace = "com.amar.storyapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.amar.storyapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "BASE_URL", "\"https://story-api.dicoding.dev/v1/\"")
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
        freeCompilerArgs = listOf("-Xopt-in=kotlin.RequiresOptIn")
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
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Lottie
    implementation(libs.lottie)

    // Glide
    implementation(libs.glide)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.converterGson)

    // OkHttp3
    implementation(libs.okhttpLoggingInterceptor)

    // Lifecycle
    implementation(libs.lifecycleRuntimeKtx)

    // DataStore
    implementation(libs.datastorePreferences)

    // Coroutines
    implementation(libs.lifecycleViewModelKtx)
    implementation(libs.lifecycleLivedataKtx)
    implementation(libs.coroutinesCore)
    implementation(libs.coroutinesAndroid)

    // SwipeRefreshLayout
    implementation(libs.swipeRefreshLayout)

    // Room
    ksp(libs.roomCompiler)
    implementation(libs.roomKtx)
    implementation(libs.roomRuntime)

    // Maps
    implementation("com.google.android.gms:play-services-location:18.0.0")
    implementation(libs.play.services.maps)

    // Paging 3
    implementation("androidx.paging:paging-runtime-ktx:3.1.0")
    implementation("androidx.room:room-paging:2.4.0-rc01")

    // Testing
    testImplementation(libs.androidx.core.testing) // InstantTaskExecutorRule
    testImplementation(libs.kotlinx.coroutines.test) //TestDispatcher
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.inline)
    androidTestImplementation(libs.androidxTestExtJunit)
    androidTestImplementation(libs.mockwebserver)
    androidTestImplementation(libs.okhttpTls)
    implementation(libs.espressoIdlingResource)




}
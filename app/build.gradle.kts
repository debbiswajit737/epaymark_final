plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")

    id ("kotlin-kapt")
    id ("com.google.dagger.hilt.android")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
}



android {
    namespace = "com.epaymark.epay"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.epaymark.epay"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

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
        dataBinding= true
        viewBinding = true

    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    /*implementation ("com.google.dagger:hilt-android:2.44.1")
    kapt ("com.google.dagger:hilt-compiler:2.44.1")*/

    //Hilt
    implementation("com.google.dagger:hilt-android:2.44.2")
    kapt ("com.google.dagger:hilt-compiler:2.44.2")

    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.google.code.gson:gson:2.10.1")

    //SMS Verification APIs
    implementation ("com.google.android.gms:play-services-auth:20.7.0")
    implementation ("com.google.android.gms:play-services-auth-api-phone:18.0.1")


    val paging_version = "3.2.1"
    implementation ("androidx.paging:paging-runtime-ktx:$paging_version")

   /* implementation ("com.google.firebase:firebase-bom:30.0.0")
    implementation ("com.google.firebase:firebase-crashlytics")
    implementation ("com.google.firebase:firebase-messaging:23.1.0")*/

    //ssd and ssp unit for dimensions
    implementation ("com.intuit.sdp:sdp-android:1.1.0")
    implementation ("com.intuit.ssp:ssp-android:1.1.0")


    //kotlin coroutine
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
    // Preferences DataStore (SharedPreferences like APIs)
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    //viewModel and liveData
    val lifecycle_version = "2.2.0"
    implementation ("androidx.lifecycle:lifecycle-extensions:$lifecycle_version")


    // LiveData
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    // Lifecycles only (without ViewModel or LiveData)
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")


    implementation ("androidx.legacy:legacy-support-v4:1.0.0")
    val nav_version = "2.5.2"
    // Kotlin navigation
    implementation ("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation ("androidx.navigation:navigation-ui-ktx:$nav_version")
    androidTestImplementation ("androidx.navigation:navigation-testing:$nav_version")

    //Retrofit
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-scalars:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.7")
    implementation ("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.7")

    implementation ("com.google.code.gson:gson:2.10.1")

    implementation ("androidx.legacy:legacy-support-v4:1.0.0")

    implementation ("de.hdodenhof:circleimageview:3.1.0")

    implementation(platform("com.google.firebase:firebase-bom:32.4.0"))


    implementation("com.google.firebase:firebase-crashlytics")
    implementation ("com.google.firebase:firebase-crashlytics-ktx")
    //implementation("com.google.android.gms:play-services-analytics")

    /*implementation("com.google.android.gms:play-services-analytics:32.4.0")
    implementation("com.google.firebase:firebase-crashlytics:32.4.0")
    implementation("com.google.firebase:firebase-analytics:32.4.0")*/


    implementation ("com.airbnb.android:lottie:5.2.0")

    //Otp PIN view
    implementation ("io.github.chaosleung:pinview:1.4.4")

    implementation ("com.karumi:dexter:6.2.3")

    implementation ("androidx.window:window:1.0.0")
}

kapt {
    correctErrorTypes= true
}
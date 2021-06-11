object Kotlin {
    private val core_ktx_version = "1.2.0"

    val stdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.3.61"
    val coreKtx = "androidx.core:core-ktx:$core_ktx_version"
}

object Android {
    private val app_compat_version = "1.1.0"

    val appCompat = "androidx.appcompat:appcompat:$app_compat_version"
}

object UI {

    private val material_version = "1.1.0"

    val materialComponents = "com.google.android.material:material:$material_version"
}

object Layout {

    private val constraint_layout_version = "1.1.3"
    private val fragment_container_view_version = "1.2.3"
    private val recycler_view_version = "1.1.0"
    private val swipe_refresh_layout_version = "1.1.0-rc01"

    val constraintLayout = "androidx.constraintlayout:constraintlayout:$constraint_layout_version"
    val fragmentContainer = "androidx.fragment:fragment-ktx:$fragment_container_view_version"
    val recyclerView = "androidx.recyclerview:recyclerview:$recycler_view_version"
    val swipeRefreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:$swipe_refresh_layout_version"
}

object DI {
    private val koin_version = "2.2.3"

    val koin = "io.insert-koin:koin-core:$koin_version"
    val koinViewModel = "io.insert-koin:koin-android-viewmodel:$koin_version"
}

object Glide {
    private val glide_version = "4.11.0"

    val glide = "com.github.bumptech.glide:glide:$glide_version"
    val glideProcessor = "com.github.bumptech.glide:compiler:$glide_version"
}

object RxJava {
    val core = "io.reactivex.rxjava2:rxjava:2.2.17"
    val android = "io.reactivex.rxjava2:rxandroid:2.1.1"
}

object Retrofit {
    private val retrofit_version = "2.3.0"

    val core = "com.squareup.retrofit2:retrofit:$retrofit_version"
    val rxAdapter = "com.squareup.retrofit2:adapter-rxjava2:$retrofit_version"
    val gsonConverter = "com.squareup.retrofit2:converter-gson:$retrofit_version"
    val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:3.10.0"
}

object Log {
    private val timber_version = "4.7.1"

    val timber = "com.jakewharton.timber:timber:$timber_version"
}

object Firebase {
    private const val firebase_analytics_version = "17.4.2"
    private const val firebase_crashlytics_version = "17.0.0"

    const val analytics = "com.google.firebase:firebase-analytics:$firebase_analytics_version"
    const val crashlytics = "com.google.firebase:firebase-crashlytics:$firebase_crashlytics_version"
}

object JUnit {
    private val junit_version = "4.13"
    private val test_ext_junit_version = "1.1.1"

    val core = "junit:junit:$junit_version"
    val ext = "androidx.test.ext:junit:$test_ext_junit_version"
}

object Mockito {
    private val mockito_version = "2.23.4"

    val core = " org.mockito:mockito-core:$mockito_version"
}

object Espresso {
    private val espresso_core_version = "3.2.0"

    val core = "androidx.test.espresso:espresso-core:$espresso_core_version"
}

import org.gradle.api.JavaVersion

object AppConfig {
    const val applicationId = "ru.marslab.simplepressure"
    const val minSdk = 26
    const val completeSdk = 32
    const val targetSdk = 32
    const val jvmTarget = "11"
    val javaVersion = JavaVersion.VERSION_11
}

object Releases {
    const val versionCode = 1
    const val versionName = "1.0.0"
    const val sharedVersion = 1.0
}

object Module {
    const val app = ":app"
    const val core = ":core"
}

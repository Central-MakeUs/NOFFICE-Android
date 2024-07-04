plugins {
    alias(libs.plugins.noffice.android.library)
    alias(libs.plugins.noffice.android.hilt)
    alias(libs.plugins.noffice.android.application.test)
}

android {
    namespace = "com.easyhz.noffice.data.notice"
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
}
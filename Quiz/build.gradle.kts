// 프로젝트 루트의 build.gradle 파일
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath "com.android.tools.build:gradle:8.0.0"
        classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.0"
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

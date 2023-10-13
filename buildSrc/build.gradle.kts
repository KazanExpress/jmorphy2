plugins {
    `kotlin-dsl`
    idea
}

repositories {
    mavenLocal()
    repositories {
        maven("https://plugins.gradle.org/m2/")
    }
}

idea {
    module {
        isDownloadJavadoc = false
        isDownloadSources = false
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.21")
}

kotlin {
    jvmToolchain(17)
}

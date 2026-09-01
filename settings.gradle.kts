pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven("[https://api.xposed.info/](https://api.xposed.info/)")
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "XposedModuleTemplate"
include(":app")
includeBuild("libxposed-api")
include(":libxposed-compat")

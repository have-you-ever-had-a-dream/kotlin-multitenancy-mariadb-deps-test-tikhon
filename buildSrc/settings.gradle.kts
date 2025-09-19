dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            from(files("../gradle/libs.versions.toml"))
        }
    }
}

pluginManagement {
    repositories {
        maven {
            url = uri("https://nexus.tools.personio-internal.de/repository/maven/")
        }
    }
}
rootProject.name = "buildSrc"

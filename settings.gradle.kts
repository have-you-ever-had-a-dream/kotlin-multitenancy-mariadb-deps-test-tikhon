pluginManagement {
    repositories {
        maven("https://nexus.tools.personio-internal.de/repository/maven/")
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "kotlin-multitenancy-mariadb-library"

include(":mariadb", ":mariadb-spring", ":e2e-mvc")

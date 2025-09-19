plugins {
    id("maven-publish")
    id("kotlin-conventions")
}

group = "com.personio.multitenancy"
version = project.properties["versionOverride"]
    ?.toString()
    ?.removePrefix("v") // v1.0 becomes 1.0
    ?: "local"

val sourcesJar by tasks.creating(Jar::class) {
    archiveClassifier.set("sources")
    from(sourceSets.main.get().allSource)
}

val javadocJar by tasks.creating(Jar::class) {
    dependsOn("dokkaJavadoc")
    archiveClassifier.set("javadoc")
    from(File(project.layout.buildDirectory.get().asFile, "javadoc"))
}

extensions.configure<PublishingExtension>("publishing") {
    fun isRelease() = project.hasProperty("release")

    publications {
        register("maven", MavenPublication::class) {
            from(components["kotlin"])
            artifact(sourcesJar)
            artifact(javadocJar)
            artifactId = project.name
            version = project.version.toString() + if (isRelease()) "" else "-SNAPSHOT"
        }
        repositories {
            maven {
                name = "nexus-releases"
                val baseUrl = "https://nexus.tools.personio-internal.de/repository/"
                val repositoryName = if (isRelease()) "maven-releases/" else "maven-snapshots/"
                url = uri(baseUrl + repositoryName)
                credentials {
                    username = System.getenv("NEXUS_USER").orEmpty()
                    password = System.getenv("NEXUS_PASSWORD").orEmpty()
                }
            }
        }
    }
}

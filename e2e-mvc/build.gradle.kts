plugins {
    id("kotlin-conventions")
}

dependencies {
    implementation(projects.mariadbSpring)

    implementation(platform(libs.personio.framework.bom))
    implementation("com.personio.framework:implicit-multitenancy")
    implementation("com.personio.framework:database-test-support")

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-jooq") {
        exclude(group = "org.jooq", module = "jooq")
    }
    implementation("org.testcontainers:testcontainers")
    implementation("org.testcontainers:mariadb")
    implementation("org.flywaydb:flyway-mysql")
}

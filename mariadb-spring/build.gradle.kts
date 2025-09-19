plugins {
    id("kotlin-conventions")
    id("publishing-conventions")
}

dependencies {
    api(project(":mariadb"))

    implementation(platform(libs.personio.framework.bom))
    implementation("com.personio.framework:implicit-multitenancy")
    implementation("com.personio.framework:commons-identifiers")

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("jakarta.annotation:jakarta.annotation-api")
    implementation("org.springframework:spring-context")
    kapt("org.springframework.boot:spring-boot-configuration-processor")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.boot:spring-boot-starter-jooq") {
        exclude(group = "org.jooq", module = "jooq")
    }
}

testing {
    suites {
        val acceptanceTest by registering(JvmTestSuite::class) {
            configurations
                .getByName("acceptanceTestImplementation")
                .extendsFrom(configurations.testImplementation.get())
            configurations
                .getByName("acceptanceTestRuntimeOnly")
                .extendsFrom(configurations.testRuntimeOnly.get())
            dependencies {
                implementation(projects.e2eMvc)
                implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
            }
        }
    }
}

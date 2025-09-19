plugins {
    id("kotlin-conventions")
    id("publishing-conventions")
}

dependencies {
    implementation(platform(libs.personio.framework.bom))

    api("org.jooq.pro:jooq")
    api("org.mariadb.jdbc:mariadb-java-client")

    implementation("ch.qos.logback:logback-classic")
    implementation("com.personio.framework:implicit-multitenancy")
    implementation("com.personio.framework:commons-identifiers")

    testImplementation("org.flywaydb:flyway-mysql")
    testImplementation("com.personio.framework:database-test-support")
    testImplementation("io.mockk:mockk")
    testImplementation("org.assertj:assertj-core")
}

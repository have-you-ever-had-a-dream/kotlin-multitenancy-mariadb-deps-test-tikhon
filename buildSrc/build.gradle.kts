plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(platform(libs.personio.framework.bom))

    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin")
    implementation("org.springframework.boot:spring-boot-gradle-plugin")
    implementation("org.sonarsource.scanner.gradle:sonarqube-gradle-plugin")
    implementation("org.jlleitschuh.gradle:ktlint-gradle")
    implementation("com.personio.personio-plugin-manager:plugin")
    implementation(libs.dokka)
    implementation(libs.benManesVersions)
    implementation(libs.testEvents)
}

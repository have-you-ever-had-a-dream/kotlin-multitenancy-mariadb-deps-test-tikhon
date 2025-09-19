import com.adarshr.gradle.testlogger.theme.ThemeType
import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import com.github.benmanes.gradle.versions.updates.gradle.GradleReleaseChannel
import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jlleitschuh.gradle.ktlint.reporter.ReporterType
import org.springframework.boot.gradle.plugin.SpringBootPlugin

plugins {
    jacoco
    kotlin("jvm")
    kotlin("kapt")
    id("org.jlleitschuh.gradle.ktlint")
    id("org.jetbrains.dokka")
    id("com.github.ben-manes.versions")
    id("com.adarshr.test-logger")
    id("org.sonarqube")
    id("com.personio.pluginmanager")
}

personioPluginManager {
    jvmVersion.set(JavaVersion.VERSION_17)
}

ktlint {
    version.set("1.0.0")
    reporters {
        reporter(ReporterType.JSON)
    }
}

tasks.withType<DependencyUpdatesTask> {
    gradleReleaseChannel = GradleReleaseChannel.CURRENT.id
    rejectVersionIf {
        !Dependencies.isStable(candidate.version) && Dependencies.isStable(currentVersion)
    }
}

testlogger {
    theme = ThemeType.MOCHA
    logLevel = LogLevel.QUIET
}

dependencies {
    implementation(kotlin("stdlib"))
    kapt(platform(SpringBootPlugin.BOM_COORDINATES))
    implementation(platform(SpringBootPlugin.BOM_COORDINATES))

    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks {
    withType<KotlinCompile> {
        compilerOptions {
            allWarningsAsErrors = true
            freeCompilerArgs.add("-Xsuppress-version-warnings")
        }
    }

    withType<Test> {
        testLogging {
            showExceptions = false
            showCauses = true
            showStackTraces = true
            exceptionFormat = TestExceptionFormat.FULL
        }
    }
}

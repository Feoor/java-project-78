val checkstyleVersion = "13.0.0"
val lombokVersion = "1.18.42"
val junitBomVersion = "5.10.0"

plugins {
    application
    jacoco
    id("checkstyle")
    id("com.github.ben-manes.versions") version "0.53.0"
    id("org.sonarqube") version "7.1.0.6387"
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

application {
    mainClass.set("hexlet.code.App")
}

checkstyle {
    toolVersion = checkstyleVersion
    configFile = file("config/checkstyle/checkstyle.xml")
    maxWarnings = 0
}

sonar {
    properties {
        property("sonar.projectKey", "Feoor_java-project-78")
        property("sonar.organization", "feoor")
    }
}

repositories {
    mavenCentral()
}

dependencies {
    // https://mvnrepository.com/artifact/org.projectlombok/lombok
    compileOnly("org.projectlombok:lombok:$lombokVersion")
    annotationProcessor("org.projectlombok:lombok:$lombokVersion")

    testImplementation(platform("org.junit:junit-bom:$junitBomVersion"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
}
tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required.set(true)
        csv.required.set(false)
        html.outputLocation.set(layout.buildDirectory.dir("reports/jacoco/jacocoHtml"))
    }
}

tasks.withType(Checkstyle::class) {
    reports {
        xml.required.set(true)
        html.required.set(true)
    }
}
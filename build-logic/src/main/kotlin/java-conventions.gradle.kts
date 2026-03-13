plugins {
    java
}

group = "com.inspire"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

configurations.compileOnly {
    extendsFrom(configurations.annotationProcessor.get())
}

repositories {
    mavenCentral()
}

val LOMBOK_VERSION = "1.18.42"
val SLF4J_VERSION = "2.0.17"
val JUNIT_BOM_VERSION = "5.11.4"
val ASSERTJ_VERSION = "3.26.3"

dependencies {
    // lombok
    compileOnly("org.projectlombok:lombok:${LOMBOK_VERSION}")
    annotationProcessor("org.projectlombok:lombok:${LOMBOK_VERSION}")
    testCompileOnly("org.projectlombok:lombok:${LOMBOK_VERSION}")
    testAnnotationProcessor("org.projectlombok:lombok:${LOMBOK_VERSION}")

    // logging
    implementation("org.slf4j:slf4j-api:${SLF4J_VERSION}")

    // junit
    testImplementation(platform("org.junit:junit-bom:${JUNIT_BOM_VERSION}"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation("org.assertj:assertj-core:${ASSERTJ_VERSION}")
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
}
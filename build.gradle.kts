import org.jooq.codegen.GenerationTool
import org.jooq.meta.jaxb.*
import org.jooq.meta.jaxb.Configuration
import org.jooq.meta.jaxb.Target

buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("org.jooq:jooq-codegen:3.18.5")
        classpath("com.mysql:mysql-connector-j:8.0.33")
    }
}


plugins {
    id("org.springframework.boot") version "3.2.2"
    id("io.spring.dependency-management") version "1.1.5"
    kotlin("jvm") version "1.9.24"
    kotlin("plugin.spring") version "1.9.24"
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}


subprojects {
    apply(plugin = "kotlin")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(plugin = "kotlin-kapt")

    group = "com.kakao.one"
    version = "0.0.1-SNAPSHOT"

    extra["springCloudVersion"] = "2023.0.0"

    repositories {
        mavenCentral()
    }

    dependencies {
        implementation("org.springframework.boot:spring-boot-starter-actuator")
        implementation("org.springframework.boot:spring-boot-starter-aop")
        implementation("org.springframework.boot:spring-boot-starter-webflux")
        implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
        implementation("io.r2dbc:r2dbc-pool")
        implementation("io.r2dbc:r2dbc-proxy")
        implementation("org.jooq:jooq-kotlin-coroutines:3.18.5")
        implementation("org.mariadb:r2dbc-mariadb:1.1.4")
        implementation("org.springframework.experimental:r2dbc-micrometer-spring-boot:1.0.2")
        runtimeOnly("org.mariadb:r2dbc-mariadb:1.1.4")
        annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
        testImplementation("org.springframework.boot:spring-boot-starter-test") {
            exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
        }
        testImplementation("io.projectreactor:reactor-test")
        testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.1")
        testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.8.1-Beta")
        testImplementation("io.mockk:mockk:1.12.5")
        testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.1")
    }

    dependencyManagement {
        imports {
            mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
        }
    }

    tasks {
        compileKotlin {
            kotlinOptions {
                freeCompilerArgs = listOf("-Xjsr305=strict")
                jvmTarget = "21"
            }
        }
        test {
            useJUnitPlatform()
            failFast = true
            testLogging {
                events("PASSED", "FAILED", "SKIPPED")
            }
            minHeapSize = "2G"
            maxHeapSize = "2G"
        }
    }
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

project(":core") {
    tasks {
        bootJar {
            enabled = false
        }
        jar {
            enabled = true
        }
    }
}

project(":api") {
    dependencies {
        implementation(project(":core"))
    }
}

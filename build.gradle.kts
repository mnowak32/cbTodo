import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.dokka.gradle.DokkaTask

group = "pl.cdbr.tools"
version = "1.0-SNAPSHOT"
description = "cb Todo List for Projects"

plugins {
    application
    kotlin("jvm") version "1.3.61"
    id("org.jetbrains.dokka") version "0.10.0"
}

application {
    mainClassName = "pl.cdbr.tools.todo.TodoApp"
}

repositories {
    mavenLocal()
    mavenCentral()
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("no.tornado:tornadofx:1.7.20")
}

tasks {
    withType<KotlinCompile>().configureEach {
        kotlinOptions.jvmTarget = "1.8"
    }

    withType<DokkaTask>().configureEach {
        outputFormat = "html"
        outputDirectory = "$buildDir/dokka"
    }

    val dokkaJavadoc by registering(DokkaTask::class) {
        outputFormat = "javadoc"
        outputDirectory = "$buildDir/javadoc"
    }

    register("javadocJar", Jar::class) {
        dependsOn(dokkaJavadoc)
        archiveClassifier.set("javadoc")
        from("$buildDir/javadoc")
    }
}
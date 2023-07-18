import com.github.davidmc24.gradle.plugin.avro.GenerateAvroJavaTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
	extra["version.open.api"] = "2.0.0"
	extra["version.avro"] = "1.10.0"
	extra["version.avro.serializer"] = "6.2.0"
}

plugins {
	id("org.springframework.boot") version "3.1.0"
	id("io.spring.dependency-management") version "1.1.0"
	id("com.github.davidmc24.gradle.plugin.avro-base") version "1.2.0"
	kotlin("jvm") version "1.8.22"
	kotlin("plugin.spring") version "1.8.22"
}

group = "sb"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
	maven(url = "https://packages.confluent.io/maven/")
}

dependencies {
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.springframework.kafka:spring-kafka")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:${property("version.open.api")}")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.apache.avro:avro:${property("version.avro")}")
	implementation("org.apache.avro:avro-compiler:${property("version.avro")}")
	implementation("io.confluent:kafka-avro-serializer:${property("version.avro.serializer")}")
	implementation("io.confluent:kafka-serde-tools-package:7.4.0")


	//implementation("org.springframework.boot:spring-boot-starter-log4j2")
	//implementation("com.google.code.gson:gson:2.8.9")
	//implementation("org.apache.avro:avro-tools:${property("version.avro")}")
	//implementation("org.slf4j:slf4j-api:2.0.7")

	developmentOnly("org.springframework.boot:spring-boot-devtools")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.kafka:spring-kafka-test")
}

configurations {
	all {
		//exclude(group = "org.springframework.boot", module = "spring-boot-starter-logging")
	}
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

val generateAvro = tasks.register("generateAvro", GenerateAvroJavaTask::class) {
	source("src/main/avro")
	setOutputDir(file("src/main/kotlin/"))
}

sourceSets {
	main {
		java {
			srcDir(generateAvro)
		}
	}
}
import com.github.davidmc24.gradle.plugin.avro.GenerateAvroJavaTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
	extra["version.avro"] = "1.10.0"
	extra["version.avro.serializer"] = "6.2.0"
	extra["version.open.feign"] = "4.0.3"
}

plugins {
	id("org.springframework.boot") version "3.1.1"
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
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.springframework.kafka:spring-kafka")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.cloud:spring-cloud-starter-openfeign:${property("version.open.feign")}")
	implementation("org.apache.avro:avro:${property("version.avro")}")
	implementation("org.apache.avro:avro-compiler:${property("version.avro")}")
	implementation("io.confluent:kafka-avro-serializer:${property("version.avro.serializer")}")
	implementation("io.confluent:kafka-serde-tools-package:7.4.0")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.kafka:spring-kafka-test")
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


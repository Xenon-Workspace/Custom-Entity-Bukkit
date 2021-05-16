plugins {
    kotlin("jvm") version "1.4.10"
    id("com.github.johnrengelman.shadow") version "5.2.0"
    `maven-publish`
}

group = "com.github.xenon"
version = "1.0"

repositories {
    mavenCentral()
    maven("https://papermc.io/repo/repository/maven-public")
    maven("https://jitpack.io/")
    maven("https://repo.dmulloy2.net/repository/public/")
}

dependencies {
    compileOnly(kotlin("stdlib"))
    compileOnly("com.comphenix.protocol:ProtocolLib:4.6.0")
    compileOnly("com.destroystokyo.paper:paper-api:1.16.5-R0.1-SNAPSHOT")
    implementation("com.github.monun:kommand:0.7.+")
}
tasks {
    create<Jar>("sourcesJar") {
        archiveClassifier.set("sources")
        for (subproject in subprojects) {
            from(subproject.sourceSets["main"].allSource)
        }
    }
    create<Copy>("copyToServer") {
        from(shadowJar)
        var dest = File(rootDir, ".server/plugins")
        // if plugin.jar exists in plugins change dest to plugins/update
        if (File(dest, "Tap.jar").exists()) dest = File(dest, "update")
        into(dest)
    }
}
publishing {
    publications {
        create<MavenPublication>("CustomEntity") {
            project.shadow.component(this)
            artifact(tasks["sourcesJar"])
        }
    }
}
plugins {
    id("java-library")
    id("org.allaymc.gradle.plugin") version "0.2.1"
}

group = "org.allaymc.coloredslimeblock"
description = "Slime blocks you know but colorful!"
version = "0.1.0"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

allay {
    api = "0.23.0"
    apiOnly = false

    plugin {
        entrance = ".ColoredSlimeBlock"
        authors += "daoge_cmd"
        website = "https://github.com/AllayMC/ColoredSlimeBlock"
    }
}

dependencies {
    compileOnly(group = "org.projectlombok", name = "lombok", version = "1.18.34")
    annotationProcessor(group = "org.projectlombok", name = "lombok", version = "1.18.34")
}

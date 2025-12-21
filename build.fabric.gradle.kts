plugins {
	id("mod-platform")
	id("fabric-loom")
}

platform {
	loader = "fabric"
	dependencies {
		required("minecraft") {
			versionRange = prop("deps.minecraft").replace("pre", "beta.")
		}
		required("fabric-api") {
			slug("fabric-api")
			versionRange = ">=${prop("deps.fabric-api")}"
		}
		required("fabricloader") {
			versionRange = ">=${libs.fabric.loader.get().version}"
		}
	}
}

loom {
	runs.named("client") {
		client()
		ideConfigGenerated(true)
		runDir = "run/"
		environment = "client"
		programArgs("--username=Dev")
		configName = "Fabric Client"
	}
	runs.named("server") {
		server()
		ideConfigGenerated(true)
		runDir = "run/"
		environment = "server"
		configName = "Fabric Server"
	}
}

stonecutter {
	filters.exclude("**/*.accesswidener", "**/*.cfg")
	val dir = eval(current.version, ">1.21.10")
	replacements.string {
		direction = dir
		replace("ValidatedIdentifier", "ValidatedIdentifier")
	}
	replacements.string {
		direction = dir
		replace("ResourceLocation", "Identifier")
	}
}

repositories {
	maven("https://maven.parchmentmc.org") { name = "ParchmentMC" }
	maven("https://maven.wispforest.io/releases") { name = "Wisp Forest" }
}

dependencies {
	minecraft("com.mojang:minecraft:${prop("deps.minecraft")}")
	@Suppress("UnstableApiUsage")
	mappings(
		loom.layered {
			officialMojangMappings()
			if (hasProperty("deps.parchment")) parchment("org.parchmentmc.data:parchment-${prop("deps.parchment")}@zip")
		})
	modImplementation(libs.fabric.loader)
	modImplementation("net.fabricmc.fabric-api:fabric-api:${prop("deps.fabric-api")}")
	modCompileOnly("io.wispforest:accessories-fabric:${prop("deps.accessories")}")
}

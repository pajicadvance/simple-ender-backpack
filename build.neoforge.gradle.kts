plugins {
	id("mod-platform")
	id("net.neoforged.moddev")
}

platform {
	loader = "neoforge"
	dependencies {
		required("minecraft") {
			forgeVersionRange = "[${prop("deps.minecraft")}]"
		}
		required("neoforge") {
			forgeVersionRange = "[1,)"
		}
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

neoForge {
	version = property("deps.neoforge") as String

	if (hasProperty("deps.parchment")) parchment {
		val (mc, ver) = (property("deps.parchment") as String).split(':')
		mappingsVersion = ver
		minecraftVersion = mc
	}

	runs {
		register("client") {
			client()
			gameDirectory = file("run/")
			ideName = "NeoForge Client (${stonecutter.active?.version})"
			programArgument("--username=Dev")
		}
		register("server") {
			server()
			gameDirectory = file("run/")
			ideName = "NeoForge Server (${stonecutter.active?.version})"
		}
	}

	mods {
		register(property("mod.id") as String) {
			sourceSet(sourceSets["main"])
		}
	}
}

repositories {
	maven("https://maven.parchmentmc.org") { name = "ParchmentMC" }
	maven("https://maven.wispforest.io/releases") { name = "Wisp Forest" }
	maven("https://maven.su5ed.dev/releases") { name = "su5ed" }
}

dependencies {
	compileOnly("io.wispforest:accessories-neoforge:${prop("deps.accessories")}")
}

tasks.named("createMinecraftArtifacts") {
	dependsOn(tasks.named("stonecutterGenerate"))
}

import com.unito.configurePublishing

buildscript {
    repositories {
        jcenter()
        maven("https://dl.bintray.com/kotlin/kotlin-eap")
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.10")
    }
}

allprojects {
    group = "com.unito"
    version = property("unito.version")!!

    repositories {
        jcenter()
        mavenCentral()
        google()
    }

    project.afterEvaluate {
        configurePublishing()
    }
}

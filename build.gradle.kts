import com.unito.configurePublishing

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

plugins {
    application
    kotlin("jvm") version "1.5.21"
}

repositories {
    jcenter()
}

dependencies {
    testImplementation("org.testng:testng:7.3.0")
    implementation("org.mockito:mockito-core:3.7.7")
    implementation("com.google.guava:guava:29.0-jre")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.5.21")
}

application {
    mainClass.set("app.App")
}

tasks.test {
    useTestNG()
}

package app

import quickus.core.QuickusAutomation

fun main() {
    val app = KApp()
    app.start()
}

class KApp : QuickusAutomation() {

    fun start() {
        QuickusAutomation.run(KApp::class.java, null)
    }
}

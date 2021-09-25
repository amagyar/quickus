package quickus.core

import quickus.core.QuickusContext

fun interface QuickusStep {
    fun run(ctx: QuickusContext?)
}
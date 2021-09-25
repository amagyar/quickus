package quickus.core

import quickus.types.StepType

class Feature {
    private val steps = mutableListOf<ExecutableStep>()
    private val ctx = QuickusContext()

    companion object {
        @JvmStatic
        fun feature(): Feature {
            return Feature()
        }
    }

    @Deprecated("")
    fun report(description: String?) {
        println()
        println("----------")
        println(description)
        println("----------")
        println()
    }

    fun getSteps(): Collection<ExecutableStep> {
        return steps
    }

    fun given(step: QuickusStep, vararg params: Any): Feature {
        steps.add(ExecutableStep(StepType.GIVEN, step, ctx, params))
        return this
    }

    fun `when`(step: QuickusStep, vararg params: Any): Feature {
        steps.add(ExecutableStep(StepType.WHEN, step, ctx, params))
        return this
    }

    fun then(step: QuickusStep, vararg params: Any): Feature {
        steps.add(ExecutableStep(StepType.THEN, step, ctx, params))
        return this
    }
}
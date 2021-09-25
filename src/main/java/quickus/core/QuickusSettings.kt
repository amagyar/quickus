package quickus.core

import java.util.LinkedList
import quickus.core.QuickusSettings
import java.util.List

class QuickusSettings {
    private val stepCandidates = mutableListOf<Any>()
    private val featureCandidates = mutableListOf<Any>()

    fun getStepCandidates(): Collection<Any> {
        return stepCandidates
    }

    fun getFeatureCandidates(): Collection<Any> {
        return featureCandidates
    }

    fun withSteps(vararg steps: Any): QuickusSettings {
        stepCandidates.addAll(steps)
        return this
    }

    fun withFeatures(vararg features: Any): QuickusSettings {
        featureCandidates.addAll(features)
        return this
    }
}

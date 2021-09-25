package quickus.core

import quickus.annotations.FeatureDescription
import quickus.annotations.Step
import java.lang.reflect.Field

open class QuickusAutomation {
    open fun settings(): QuickusSettings {
        return QuickusSettings() // TODO default settings
    }

    companion object {
        @JvmStatic
        fun <T> run(clazz: Class<T>, args: Array<String?>?) {
            println("Starting application")
            try {
                // first find the steps candidates provided by settings
                val automationCandidate = clazz.getDeclaredConstructor().newInstance()
                if (automationCandidate is QuickusAutomation) {
                    println("Extension application")
                    val qa = QuickusAutomation::class.java.cast(automationCandidate)
                    val settings = qa.settings()

                    val sentenceField: MutableMap<String, Field> = HashMap()
                    val stepSentence: MutableMap<QuickusStep, String> = HashMap()
                    val stepCandidates = settings.getStepCandidates()
                    for (rawStep in stepCandidates) {
                        for (field in rawStep.javaClass.declaredFields) {
                            if (field.isAnnotationPresent(Step::class.java)) {
                                val sentence: String = field.getAnnotation(Step::class.java).value
                                field.isAccessible = true
                                val step = QuickusStep::class.java.cast(field[rawStep])
                                stepSentence[step] = sentence
                                sentenceField[sentence] = field
                            }
                        }
                    }
                    val featureCandidates = settings.getFeatureCandidates()
                    for (rawFeature in featureCandidates) {
                        for (method in rawFeature.javaClass.declaredMethods) {
                            method.isAccessible = true
                            if (method.isAnnotationPresent(FeatureDescription::class.java)) {
                                val annotation = method.getAnnotation(
                                    FeatureDescription::class.java
                                )
                                val feature = Feature::class.java.cast(method.invoke(rawFeature))
                                feature.report(annotation.value)
                                val steps = feature.getSteps()
                                steps.stream().forEach { executableStep: ExecutableStep ->
                                    val sentence = stepSentence[executableStep.step()]
                                    executableStep.ctx.clearStepContext()
                                    executableStep.execute(sentence!!)
                                }
                            }
                        }
                    }
                }

                // check annotation
                // TODO
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
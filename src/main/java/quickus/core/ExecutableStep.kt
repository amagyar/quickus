package quickus.core

import quickus.types.StepType
import java.util.*
import java.util.regex.Pattern

class ExecutableStep(
    private val stepType: StepType,
    private val step: QuickusStep,
    val ctx: QuickusContext,
    private val params: Array<out Any>
) {
    companion object {
        private var paramNameRegex = "\\$\\{%s\\}"
        private var param = "\\$\\{(.*?)\\}"
        private var paramPattern = Pattern.compile(param)
    }

    private val paramNames: MutableCollection<String> = LinkedList()
    fun step(): QuickusStep {
        return step
    }

    private fun setParamsToContext(sentence: String) {
        val matcher = paramPattern.matcher(sentence)
        var i = 0
        while (matcher.find()) {
            val paramName = matcher.group(1)
            paramNames.add(paramName)
            if (i + 1 <= params.size) {
                ctx.setParameter(paramName, params[i++])
            } else {
                throw IllegalArgumentException("You've forgotten to pass the value for parameter [$paramName]")
            }
        }
    }

    private fun report(sentence: String) {
        val parsedSentence = parseSentence(sentence)
        // TODO pass to reports
        println("$stepType $parsedSentence")
    }

    private fun parseSentence(sentence: String): String {
        var evaluatedSentence = sentence
        for (paramName in paramNames) {
            val regex = String.format(paramNameRegex, paramName)
            evaluatedSentence = evaluatedSentence.replace(regex.toRegex(), ctx.getParameter(paramName, Any::class.java).toString())
        }
        return evaluatedSentence
    }

    fun execute(sentence: String) {
        setParamsToContext(sentence)
        report(sentence)
        step.run(ctx)
    }
}
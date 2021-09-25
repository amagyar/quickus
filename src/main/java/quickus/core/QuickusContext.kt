package quickus.core

import java.util.HashMap

class QuickusContext {
    protected var parsedParams: MutableMap<String, Any> = HashMap()
    fun clearStepContext() {
        parsedParams.clear()
    }

    fun setParameter(key: String, value: Any) {
        if (parsedParams.containsKey(key)) {
            println("WARN: The step already contains a param named [$key]")
        }
        parsedParams[key] = value
    }

    fun <T> getParameter(parameter: String, clazz: Class<T>): T {
        return clazz.cast(parsedParams[parameter])
    }
}
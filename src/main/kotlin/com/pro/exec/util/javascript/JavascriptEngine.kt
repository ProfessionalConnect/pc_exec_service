package com.pro.exec.util.javascript

import com.pro.exec.util.script.EngineType
import javax.script.Invocable
import javax.script.ScriptEngine
import javax.script.ScriptEngineManager
import javax.script.ScriptException
import kotlin.jvm.Throws

/**
 * Created by Minky on 2022-02-17
 */
class JavascriptEngine {
    private val javascriptEngine: ScriptEngine
    private val invocable: Invocable

    constructor() {
        val scriptEngineManager = ScriptEngineManager()
        javascriptEngine = scriptEngineManager.getEngineByName(EngineType.JAVASCRIPT.shortName)
        invocable = javascriptEngine as Invocable
    }

    fun<T> runJS(code: String, testArgument: T): String {
        return try {
            javascriptEngine.eval(code)
            invokeJavascriptOneArgument(testArgument).toString()
        } catch (e: ScriptException) {
            ""
        }
    }

    @Throws(ScriptException::class, NoSuchMethodException::class)
    fun <T> invokeJavascriptOneArgument(a: T): Any {
        return invocable.invokeFunction("solution", a)
    }

    @Throws(ScriptException::class, NoSuchMethodException::class)
    fun <T> invokeJavascriptTwoArgument(a: T, b: T): Any {
        return invocable.invokeFunction("solution", a, b)
    }

    @Throws(ScriptException::class, NoSuchMethodException::class)
    fun <T> invokeJavascriptThreeArgument(a: T, b: T, c: T): Any {
        return invocable.invokeFunction("solution", a, b, c)
    }

    @Throws(ScriptException::class, NoSuchMethodException::class)
    fun <T> invokeJavascriptFourArgument(a: T, b: T, c:T, d:T): Any {
        return invocable.invokeFunction("solution", a, b, c, d)
    }
}
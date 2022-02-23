package com.pro.exec.util.script

import javax.script.ScriptEngine
import javax.script.ScriptEngineManager


/**
 * Created by Minky on 2022-02-06
 */


class ScriptLanguageExecutor {
    private val javaEngine: ScriptEngine
    private val javascriptEngine: ScriptEngine
    private val pythonEngine: ScriptEngine

    constructor() {
        val scriptEngineManager = ScriptEngineManager()
        javascriptEngine = scriptEngineManager.getEngineByName(EngineType.JAVASCRIPT.shortName)
        pythonEngine = scriptEngineManager.getEngineByName(EngineType.PYTHON.shortName)
        javaEngine = scriptEngineManager.getEngineByName(EngineType.JAVA.shortName)
    }
}
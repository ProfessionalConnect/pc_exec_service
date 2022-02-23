package com.pro.exec.util.parser

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

/**
 * Created by Minky on 2022-02-19
 */

class TestArgumentParserTest {
    private lateinit var testArgumentParser: TestArgumentParser

    @BeforeEach
    fun setUp() {
        testArgumentParser = TestArgumentParser()
    }

    @ParameterizedTest(name = "숫자_파싱_검증 [{index}] => `{argumentsWithNames}`")
    @ValueSource(strings = [
        "1",
        "1, 2",
        "1, 2, 3",
        "1, 2, 3, 4",
        "1,2,3,4",
    ])
    fun 숫자_파싱_검증(testArgument: String) {
        println("Stage: { ${testArgument} }")
        val parseList = testArgumentParser.firstParsing(testArgument)
        parseList.forEach { println(it) }
        println("---END---")
    }

    @ParameterizedTest(name = "문자열_파싱_검증 [{index}] => `{argumentsWithNames}`")
    @ValueSource(strings = [
        "\"test\"",
        "\"test\", \"test\"",
        "\"test\", \"test\", \"test\"",
        "\"test\", \"test\", \"test\", \"test\"",
        "\"test\",\"test\",\"test\",\"test\"",
    ])
    fun 문자열_파싱_검증(testArgument: String) {
        println("Stage: { ${testArgument} }")
        val parseList = testArgumentParser.firstParsing(testArgument)
        parseList.forEach { println(it) }
        println("---END---")
    }

    @ParameterizedTest(name = "배열_파싱_검증 [{index}] => `{argumentsWithNames}`")
    @ValueSource(strings = [
        "[1]",
        "[1, 2]",
        "[1, 2, 3]",
        "[1, 2, 3, 4]",
        "[\"test\"]",
        "[\"test\", \"test\"]",
        "[\"test\", \"test\", \"test\"]",
        "[\"test\", \"test\", \"test\", \"test\"]",
        "[1, \"test\", \"test\", \"test\"]",
        "[\"test\", 1, \"test\", \"test\"]",
        "[\"test\", \"test\", 1, \"test\"]",
        "[\"test\", \"test\", \"test\", 1]",
        "[1,2,3,4]",
        "[\"test\",\"test\",\"test\",\"test\"]",
    ])
    fun 배열_파싱_검증(testArgument: String) {
        println("Stage: { ${testArgument} }")
        val parseList = testArgumentParser.firstParsing(testArgument)
        parseList.forEach { println(it) }
        println("---END---")
    }

    @ParameterizedTest(name = "복합_파싱_검증 [{index}] => `{argumentsWithNames}`")
    @ValueSource(strings = [
        "\"test\", 1, \"test\"",
        "1, \"test\", 2",
        "[\"test\", \"test\"], 2",
        "[\"test\", \"test\"], 2, \"test\"",
        "1, [\"test\", \"test\"], 2",
        "1, 1, [\"test\", \"test\"]",
        "[\"test\", \"test\"], [\"test\", \"test\"], [\"test\", \"test\"]",
        "[\"test\", \"test\"], 1, [\"test\", \"test\"]",
        "1, [\"test\", \"test\"], [\"test\", \"test\"]",
    ])
    fun 복합_파싱_검증(testArgument: String) {
        println("Stage: { ${testArgument} }")
        val parseList = testArgumentParser.firstParsing(testArgument)
        parseList.forEach { println(it) }
        println("---END---")
    }
}
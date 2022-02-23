package com.pro.exec.util.parser

/**
 * Created by Minky on 2022-02-17
 */

class TestArgumentParser {
    fun firstParsing(testArgument: String): MutableList<String> {
        var bracketCounter = 0
        var doubleQuotesFlag = false
        
        val firstParseList = mutableListOf<String>()
        var argument = ""

        for (str in testArgument) {
            // Phase.1 Check Bracket
            if (isFrontBracket(str) && bracketCounter == 0) {
                bracketCounter = 1
                continue
            }

            if (isBackBracket(str) && bracketCounter == 1) {
                bracketCounter = 0
                firstParseList.add(argument)
                argument = ""
                continue
            }

            if (isFrontBracket(str)) {
                bracketCounter += 1
            }

            if (isBackBracket(str)) {
                bracketCounter -= 1
            }

            if (bracketCounter > 0) {
                argument += str
                continue
            }

            // Phase.2 Check Double Quotes
            if (isDoubleQuotes(str) && !doubleQuotesFlag) {
                doubleQuotesFlag = !doubleQuotesFlag
                continue
            }

            if (isDoubleQuotes(str)) {
                doubleQuotesFlag = !doubleQuotesFlag
            }
            
            if (!doubleQuotesFlag && !isEmptyString(argument)) {
                firstParseList.add(argument)
                argument = ""
                continue
            }

            if (doubleQuotesFlag) {
                argument += str
                continue
            }
            // Phase.3 Check Comma
            if (isComma(str) && !isEmptyString(argument)) {
                firstParseList.add(argument)
                argument = ""
                continue
            }

            if (isComma(str)) {
                continue
            }

            // Phase.4 Check Empty Space
            if (isEmptySpace(str) && !isEmptyString(argument)) {
                firstParseList.add(argument)
                argument = ""
                continue
            }
            
            if (isEmptySpace(str)) {
                continue
            }
            
            argument += str
        }

        if (!isEmptyString(argument)) {
            firstParseList.add(argument)
        }

        return firstParseList
    }

    private fun isEmptyString(string: String): Boolean = string == ""
    
    private fun isDoubleQuotes(str: Char): Boolean = str == '\"'

    private fun isEmptySpace(str: Char): Boolean = str == ' '

    private fun isComma(str: Char): Boolean = str == ','

    private fun isFrontBracket(str: Char): Boolean = str == '['

    private fun isBackBracket(str: Char): Boolean = str == ']'

    inner class TypeCaseOne<T>(val a: T)
    inner class TypeCaseTwo<T>(val a: T, val b: T)
    inner class TypeCaseThree<T>(val a: T, val b: T, val c: T)
    inner class TypeCaseFour<T>(val a: T, val b: T, val c: T, val d: T)
}
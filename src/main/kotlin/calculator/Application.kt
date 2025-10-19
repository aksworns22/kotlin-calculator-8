package calculator

import camp.nextstep.edu.missionutils.Console

fun main() {
    println("덧셈할 문자열을 입력해 주세요.")
    val input = Console.readLine()
    val structuredInput = StructuredInput(input)
    val positiveNumbers = structuredInput.extractPositiveNumbers()
    println("결과 : ${positiveNumbers.sum()}")
}

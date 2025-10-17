package calculator

import camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest
import camp.nextstep.edu.missionutils.test.NsTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ApplicationTest : NsTest() {
    @Test
    fun `빈 문자열이 들어오는 경우`() {
        val answer = PositiveNumbers(listOf())
        assertSimpleTest {
            assertThat(StructuredInput("").extractPositiveNumbers()).isEqualTo(answer)
            assertThat(StructuredInput("//@\\n").extractPositiveNumbers()).isEqualTo(answer)
        }
    }

    @Test
    fun `단일 숫자가 입력으로 들어오는 경우`() {
        val answer = PositiveNumbers(listOf(1))
        assertSimpleTest {
            assertThat(StructuredInput("1").extractPositiveNumbers()).isEqualTo(answer)
            assertThat(StructuredInput("//;\\n1").extractPositiveNumbers()).isEqualTo(answer)
        }
    }

    @Test
    fun `여러 숫자가 입력되는 경우`() {
        val answer = PositiveNumbers(listOf(1, 2, 3, 4))
        assertSimpleTest {
            assertThat(StructuredInput("1:2:3:4").extractPositiveNumbers()).isEqualTo(answer)
            assertThat(StructuredInput("//-\\n1,2-3:4").extractPositiveNumbers()).isEqualTo(answer)
        }
    }

    @Test
    fun `구분자만 입력으로 들어오는 경우`() {
        assertSimpleTest {
            assertThrows<IllegalArgumentException> { StructuredInput(",") }
            assertThrows<IllegalArgumentException> { StructuredInput("//@\\n,") }
        }
    }

    @Test
    fun `두 글자 이상의 문자열을 커스텀 구분자로 사용하는 경우`() {
        assertSimpleTest {
            assertThrows<IllegalArgumentException> { Delimiter("//ab\\n") }
        }
    }

    @Test
    fun `음수를 입력받는 경우`() {
        assertSimpleTest {
            assertThrows<IllegalArgumentException> { PositiveNumbers(listOf(1, -2, 3)) }
        }
    }

    @Test
    fun `숫자 합산 테스트`() {
        val positiveNumbers = PositiveNumbers(listOf(1, 2))
        assertSimpleTest {
            assertThat(positiveNumbers.sum()).isEqualTo(3)
        }
    }

    @Test
    fun`구분자를 연속해서 사용하는 경우 테스트`() {
        assertSimpleTest {
            assertThrows<IllegalArgumentException> { StructuredInput("12,,34") }
            assertThrows<IllegalArgumentException> { StructuredInput("//-\\n2--3") }
        }
    }

    @Test
    fun `정의되지 않은 문자를 사용하는 경우 테스트`() {
        assertSimpleTest {
            assertThrows<IllegalArgumentException> { StructuredInput("1 2") }
            assertThrows<IllegalArgumentException> { StructuredInput("//-\\n1-2 -3") }
        }
    }
//    @Test
//    fun `커스텀 구분자 사용`() {
//        assertSimpleTest {
//            run("//;\\n1")
//            assertThat(output()).contains("결과 : 1")
//        }
//    }
//
//    @Test
//    fun `예외 테스트`() {
//        assertSimpleTest {
//            assertThrows<IllegalArgumentException> { runException("-1,2,3") }
//        }
//    }

    override fun runMain() {
        main()
    }
}

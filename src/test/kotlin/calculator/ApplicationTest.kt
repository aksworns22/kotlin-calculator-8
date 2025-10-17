package calculator

import camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest
import camp.nextstep.edu.missionutils.test.NsTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ApplicationTest : NsTest() {
    @Test
    fun `빈 문자열이 들어오는 경우`() {
        val answer = listOf<Int>()
        assertSimpleTest {
            assertThat(extractNumbers("")).isEqualTo(answer)
            assertThat(extractNumbers("//@\\n")).isEqualTo(answer)
        }
    }

    @Test
    fun `단일 숫자가 입력으로 들어오는 경우`() {
        val answer = listOf(1)
        assertSimpleTest {
            assertThat(extractNumbers("1")).isEqualTo(answer)
            assertThat(extractNumbers("//;\\n1")).isEqualTo(answer)
        }
    }

    @Test
    fun `여러 숫자가 입력되는 경우`() {
        assertSimpleTest {
            assertThat(extractNumbers("1,2")).isEqualTo(listOf(1, 2))
            assertThat(extractNumbers("1:2:3")).isEqualTo(listOf(1, 2, 3))
            assertThat(extractNumbers("//#\\n1,2#3:4")).isEqualTo(listOf(1, 2, 3, 4))
        }
    }

    @Test
    fun `구분자만 입력으로 들어오는 경우`() {
        assertSimpleTest {
            assertThrows<IllegalArgumentException> { extractNumbers(",") }
            assertThrows<IllegalArgumentException> { extractNumbers("//@\\n,") }
        }
    }

    @Test
    fun `두 글자 이상의 문자열을 커스텀 구분자로 사용하는 경우`() {
        assertSimpleTest {
            assertThrows<IllegalArgumentException> { getCustomDelimiter("//ab\\n") }
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
        val extractedNumbers = listOf(1, 2)
        assertSimpleTest {
            assertThat(addNumbers(extractedNumbers)).isEqualTo(3)
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

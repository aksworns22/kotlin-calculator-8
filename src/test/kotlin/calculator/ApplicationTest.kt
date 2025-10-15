package calculator

import camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest
import camp.nextstep.edu.missionutils.test.NsTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ApplicationTest : NsTest() {
    @Test
    fun `빈 문자열이 들어오는 경우`() {
        assertSimpleTest {
            assertThat(calculator("")).isEqualTo(0)
        }
    }

    @Test
    fun `단일 숫자가 입력으로 들어오는 경우`() {
        assertSimpleTest {
            assertThat(calculator("1")).isEqualTo(1)
        }
    }

    @Test
    fun `두 숫자가 쉼표를 구분자로 입력되는 경우`() {
        assertSimpleTest {
            assertThat(calculator("1,2")).isEqualTo(3)
        }
    }

    @Test
    fun `콜론을 구분자로 여러 숫자가 입력되는 경우`() {
        assertSimpleTest {
            assertThat(calculator("1:2:3")).isEqualTo(6)
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

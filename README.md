# kotlin-calculator-precourse

## 구현할 기능 목록

- [x] [빈 문자열이 들어오는 경우](#빈-문자열이-들어오는-경우)
- [x] [커스텀 구분자만 정의하는 경우](#빈-문자열이-들어오는-경우)
- [x] [단일 숫자가 입력으로 들어오는 경우](#단일-숫자가-입력으로-들어오는-경우)
- [x] [구분자만 입력으로 들어오는 경우](#구분자만-입력으로-들어오는-경우)
- [x] [두 숫자가 쉼표를 구분자로 입력되는 경우](#여러-숫자가-입력되는-경우)
- [x] [여러 숫자가 콜론을 구분자로 입력되는 경우](#여러-숫자가-입력되는-경우)
- [x] [여러 숫자에 대한 합을 구하는 기능](#숫자-합산-테스트)
- [x] [커스텀 구분자를 사용하는 경우](#여러-숫자가-입력되는-경우)
- [x] [커스텀 구분자와 기본 구분자를 사용하는 경우](#여러-숫자가-입력되는-경우)
- [x] [2글자 이상의 문자열을 커스텀 구분자로 사용하는 경우](#두-글자-이상의-문자열을-커스텀-구분자로-사용하는-경우)
- [x] [음수를 입력받는 경우](#음수를-입력받는-경우)
- [x] [`-`를 구분자로 사용하는 경우](#여러-숫자가-입력되는-경우)
- [x] [정의되지 않은 문자를 사용하는 경우](#정의되지-않은-문자를-사용하는-경우)
- [x] [결과가 최대 데이터 범위를 넘어가는 경우](#결과가-최대-데이터-범위를-넘어가는-경우)
- [x] [구분자를 연속해서 사용하는 경우](#구분자를-연속해서-사용하는-경우)
- [x] [구분자로 입력을 시작하는 경우](#구분자로-입력이-시작하는-경우)
- [x] [구분자로 입력이 끝나는 경우](#구분자로-입력이-끝나는-경우)
- [x] [숫자를 커스텀 구분자로 사용하는 경우]()
- [x] [연속된 숫자를 입력받는 경우](#연속된-숫자를-입력받는-경우)
- [x] [커스텀 구분자 지정이 처음에 시작되지 않는 경우](#커스텀-구분자-지정이-처음에-시작되지-않는-경우)

## 결정한 요구사항

### 1. 양수에 소수점을 포함하는 숫가 포함될 수 있을까?

소수점을 포함하는 숫는 예외로 처리했습니다.
그 이유는 문제에서 수(123, 12.25 등)가 아닌 숫자(0~9)라고 했기에 소수점(문자)이 포함된 "수"는 입력될 수 없다고 판단했습니다.

### 2. 구분자에 숫자가 사용될 수 있을까?

구분자에 숫자 사용을 예외로 처리했습니다.
그 이유는 "문자"를 구분자로 사용한다고 했기에 "숫자"는 제외했습니다.

### 3. 구분자에 2글자 이상의 문자열이 사용될 수 있을까?

문자라고 명시되어 있기 때문에 2글자 이상의 문자열은 불가능하다고 판단했습니다.

## 테스트 코드

### 빈 문자열이 들어오는 경우

해당 테스트로 커스텀 구분자만 정의하는 경우도 테스트했습니다.

```kotlin
    @Test
    fun `빈 문자열이 들어오는 경우`() {
        val answer = PositiveNumbers(listOf())
        assertSimpleTest {
            assertThat(StructuredInput("").extractPositiveNumbers()).isEqualTo(answer)
            assertThat(StructuredInput("//@\\n").extractPositiveNumbers()).isEqualTo(answer)
        }
    }
```

`StructuredInput` 객체를 통해 문법적 오류가 없는 지 검사합니다.
이후 `extractPositiveNumbers()` 메서드를 통해 빈 `PositiveNumbers(listOf())`를 리턴하는 지 확인합니다.

### 단일 숫자가 입력으로 들어오는 경우

```kotlin
    @Test
    fun `단일 숫자가 입력으로 들어오는 경우`() {
        val answer = PositiveNumbers(listOf(1))
        assertSimpleTest {
            assertThat(StructuredInput("1").extractPositiveNumbers()).isEqualTo(answer)
            assertThat(StructuredInput("//;\\n1").extractPositiveNumbers()).isEqualTo(answer)
        }
    }
```

`StructuredInput` 객체를 통해 문법적 오류가 없는 지 검사합니다.
이후 `extractPositiveNumbers()` 메서드를 통해 `PositiveNumbers(listOf(1))`를 리턴하는 지 확인합니다.

### 구분자만 입력으로 들어오는 경우

```kotlin
    @Test
    fun `구분자만 입력으로 들어오는 경우`() {
        assertSimpleTest {
            assertThrows<IllegalArgumentException> { StructuredInput(",") }
            assertThrows<IllegalArgumentException> { StructuredInput("//@\\n,") }
        }
    }
```

구분자만 입력이 들어오는 경우는 올바르지 않은 문법입니다.
따라서 `StructuredInput` 객체 생성 시 `IllegalArgumentException` 예외를 던집니다.

### 여러 숫자가 입력되는 경우

여러 숫자가 입력되는 경우는 기본 구분자와 커스텀 구분자를 사용하는 경우를 테스트합니다.
특히 커스텀 구분자의 경우는 `-`로 테스트하도록 해서 음수 처리보다 `-` 문자를 구분자로써 우선시하는지 확인했습니다.

```kotlin
    @Test
    fun `여러 숫자가 입력되는 경우`() {
        val answer = PositiveNumbers(listOf(1, 2, 3, 4))
        assertSimpleTest {
            assertThat(StructuredInput("1:2:3:4").extractPositiveNumbers()).isEqualTo(answer)
            assertThat(StructuredInput("//-\\n1,2-3:4").extractPositiveNumbers()).isEqualTo(answer)
        }
    }
```

두 예시 모두 올바른 문법입니다.(2번째의 경우 `-` 기호는 음수가 아닌 커스텀 구분자로 사용되었기 때문입니다.)
따라서 생성된 `StructuredInput` 객체의 `extractPositiveNumbers()`를 활용해 예상한 `PositiveNumbers`와 같은 지 확인합니다.

### 숫자 합산 테스트

```kotlin
    @Test
    fun `숫자 합산 테스트`() {
        val positiveNumbers = PositiveNumbers(listOf(1, 2))
        assertSimpleTest {
            assertThat(positiveNumbers.sum()).isEqualTo(3)
        }
    }
```

`PositiveNumbers`의 `sum()`메서드의 결과가 예상값(1+2=3)과 동일한 지 확인합니다.

### 두 글자 이상의 문자열을 커스텀 구분자로 사용하는 경우

```kotlin
    @Test
    fun `두 글자 이상의 문자열을 커스텀 구분자로 사용하는 경우`() {
        assertSimpleTest {
            assertThrows<IllegalArgumentException> { Delimiters("//ab\\n") }
        }
    }
```

커스텀 구분자는 "문자"만 가능하기에 `ab`를 구분자로 사용하는 것은 올바르지 않은 문법입니다.
따라서 `Delimiters` 객체를 생성 시 `IllegalArgumentException` 예외를 던집니다.

### 음수를 입력받는 경우

```kotlin
    @Test
    fun `음수를 입력받는 경우`() {
        assertSimpleTest {
            assertThrows<IllegalArgumentException> { PositiveNumbers(listOf(1, -2, 3)) }
        }
    }
```

구현하는 문자열 덧셈 계산기는 음수가 허용되지 않습니다.
따라서 `PositiveNumbers` 객체 생성에 음수가 포함되면 `IllegalArgumentException` 예외를 던집니다.

### 정의되지 않은 문자를 사용하는 경우

```kotlin
    @Test
    fun `정의되지 않은 문자를 사용하는 경우`() {
        assertSimpleTest {
            assertThrows<IllegalArgumentException> { StructuredInput("1 2") }
            assertThrows<IllegalArgumentException> { StructuredInput("//-\\n1-2 -3") }
        }
    }
```

위 두 예시는 허용되지 않은 문자인 `공백`을 사용하고 있습니다.
따라서 `StructuredInput`의 생성에서 `IllegalArgumentException` 예외를 던집니다.

### 결과가 최대 데이터 범위를 넘어가는 경우

```kotlin
    @Test
    fun `결과가 최대 데이터 범위를 넘어가는 경우`() {
        val maxValue: Long = Int.MAX_VALUE.toLong()
        assertSimpleTest {
            assertThrows<IllegalArgumentException> {
                StructuredInput((maxValue + 1).toString()).extractPositiveNumbers()
            }
            assertThrows<IllegalArgumentException> { PositiveNumbers(listOf(maxValue.toInt(), 1)).sum() }
        }
    }
```

`PositiveNumbers`는 내부적으로 `Int` 범위의 데이터를 가집니다.
따라서 해당 범위를 넘어가는 연산(`sum()`)을 하게 된다면 `IllegalArgumentException` 예외를 던집니다.

`PositiveNumbers`의 한 요소가 범위를 초과하는 경우도 있습니다.
이 경우에는 `extractPositiveNumbers()` 메서드의 내부에서 `toInt()` 변환 과정 중
`NumberFormatException`(`IllegalArgumentException`의 하위 클래스)이 발생합니다.

### 구분자를 연속해서 사용하는 경우

```kotlin
    @Test
    fun`구분자를 연속해서 사용하는 경우`() {
        assertSimpleTest {
            assertThrows<IllegalArgumentException> { StructuredInput("12,,34") }
            assertThrows<IllegalArgumentException> { StructuredInput("//-\\n2--3") }
        }
    }
```

구분자를 연속해서 사용하는 것은 문법적으로 올바르지 않습니다.
따라서 `StructuredInput` 객체 생성 시 `IllegalArgumentException` 예외를 던집니다.

### 구분자로 입력이 시작하는 경우

```kotlin
    @Test
    fun `구분자로 입력이 시작하는 경우`() {
        assertSimpleTest {
            assertThrows<IllegalArgumentException> { StructuredInput(",123") }
            assertThrows<IllegalArgumentException> { StructuredInput("//-\\n-1,2") }
        }
    }
```

구분자로 입력을 시작하는 것은 문법적으로 올바르지 않습니다.
따라서 `StructuredInput` 객체 생성 시 `IllegalArgumentException` 예외를 던집니다.

### 구분자로 입력이 끝나는 경우

```kotlin
    @Test
    fun `구분자로 입력이 끝나는 경우`() {
        assertSimpleTest {
            assertThrows<IllegalArgumentException> { StructuredInput("1,") }
            assertThrows<IllegalArgumentException> { StructuredInput("//:\\n1:") }
        }
    }
```

구분자로 입력이 끝나는 경우은 문법적으로 올바르지 않습니다.
따라서 `StructuredInput` 객체 생성 시 `IllegalArgumentException` 예외를 던집니다.

### 연속된 숫자를 입력받는 경우

```kotlin
    @Test
    fun `연속된 숫자를 입력받는 경우`() {
        val answer = PositiveNumbers(listOf(12, 34, 56, 78))
        assertSimpleTest {
            assertThat(StructuredInput("12,34,56,78").extractPositiveNumbers()).isEqualTo(answer)
        }
    }
```

연속된 숫자(`123`, `99` 등)를 입력받을 수 있습니다.
해당 경우는 올바른 문법이며 `StructuredInput` 객체의 `extractPositiveNumbers()`를 통해 예상 결과와 동일한 지 확인합니다.

### 숫자를 커스텀 구분자로 사용하는 경우

```kotlin
    @Test
    fun `숫자를 커스텀 구분자로 사용하는 경우`() {
        assertSimpleTest {
            assertThrows<IllegalArgumentException> { Delimiters("//2\\n123") }
        }
    }
```

문자가 아닌 숫자를 커스텀 구분자로 사용하는 것은 문법적으로 올바르지 않습니다.
따라서 `Delimiters` 객체 생성 시 `IllegalArgumentException` 예외를 던집니다.

### 커스텀 구분자 지정이 처음에 시작되지 않는 경우

```kotlin
    @Test
    fun `커스텀 구분자 지정이 처음에 시작되지 않는 경우`() {
        assertSimpleTest {
            assertThrows<IllegalArgumentException> { StructuredInput("1//;\n") }
        }
    }
```

커스텀 구분자 지정이 처음에 시작되지 않는 경우는 잘못된 사용입니다.
따라서 `StructuredInput` 객체 생성 시 `IllegalArgumentException` 예외를 던집니다.
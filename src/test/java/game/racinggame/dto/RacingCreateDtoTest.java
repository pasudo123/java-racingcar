package game.racinggame.dto;

import game.dto.RacingCreateDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("레이싱 생성을 위한 데이터 전달 객체에")
class RacingCreateDtoTest {

    @ParameterizedTest
    @MethodSource("provideRacingInputData")
    @DisplayName("시도 횟수를 획득한다.")
    void getTryCountTest(final String carNames, final int tryCount, final List<String> expectedCarNames, final int expectTryCount) {

        // given & when
        final RacingCreateDto dto = RacingCreateDto.createByConsole(carNames, tryCount);

        // then
        assertThat(dto.getTryCount()).isEqualTo(expectTryCount);
    }

    @ParameterizedTest
    @MethodSource("provideRacingInputData")
    @DisplayName("자동차 이름을 순서에 따라 획득한다")
    void getCarNameByIndexTest(final String carNames, final int tryCount, final List<String> expectedCarNames, final int expectTryCount) {

        // given & when
        final RacingCreateDto dto = RacingCreateDto.createByConsole(carNames, tryCount);

        // then
        for(int i = 0; i < expectedCarNames.size(); i++) {
            assertThat(dto.getCarNameByIndex(i)).isEqualTo(expectedCarNames.get(i));
        }
    }

    @ParameterizedTest
    @MethodSource("provideRacingInputData")
    @DisplayName("자동차의 개수를 획득한다.")
    void getCarSizeTest(final String carNames, final int tryCount, final List<String> expectedCarNames, final int expectTryCount) {

        // given & when
        final RacingCreateDto dto = RacingCreateDto.createByConsole(carNames, tryCount);

        // then
        assertThat(dto.getCarSize()).isEqualTo(expectedCarNames.size());
    }

    static Stream<Arguments> provideRacingInputData() {
        return Stream.of(
                Arguments.of("붕붕, 씽씽", 1, Arrays.asList("붕붕", "씽씽"), 1),
                Arguments.of("붕붕, 씽씽, 하하", 2, Arrays.asList("붕붕", "씽씽", "하하"), 2)
        );
    }
}

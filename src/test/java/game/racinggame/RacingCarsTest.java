package game.racinggame;

import game.dto.RacingCreateDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("레이싱 카들은")
class RacingCarsTest {

    @ParameterizedTest
    @MethodSource("provideRacingCreateDto")
    @DisplayName("레이싱 전에 세팅된다.")
    void setUpRacingTest(final RacingCreateDto dto) {

        // given
        final RacingCars racingCars = new RacingCars();
        racingCars.setUpRacing(dto);

        // when
        final int carNumber = racingCars.getRacingCarsCount();

        // then
        assertThat(carNumber).isEqualTo(dto.getCarSize());
    }

    @ParameterizedTest
    @MethodSource("providePoorRacingCreateDto")
    @DisplayName("잘못된 전달 값으로 인해서 생성되지 못한다.")
    void illegalArgumentExceptionTest(final RacingCreateDto dto, final String errorMessage) {

        // given & when
        final RacingCars racingCars = new RacingCars();

        final IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> racingCars.setUpRacing(dto));

        // then
        assertEquals(errorMessage, exception.getMessage());
    }

    @ParameterizedTest
    @MethodSource("provideNullRacingCreateDto")
    @DisplayName("널 값을 받기 때문에 생성되지 못한다.")
    void nullPointerExceptionTest(final RacingCreateDto dto, final String errorMessage) {

        // given & when
        final RacingCars racingCars = new RacingCars();

        final NullPointerException exception =
                assertThrows(NullPointerException.class, () -> racingCars.setUpRacing(dto));

        // then
        assertEquals(errorMessage, exception.getMessage());
    }


    @ParameterizedTest
    @MethodSource("provideRacingCreateDto")
    @DisplayName("앞으로 나아가며 흔적을 남긴다.")
    void go(final RacingCreateDto dto) {

        // given
        RacingCars racingCars = new RacingCars();
        racingCars.setUpRacing(dto);

        // when
        racingCars.go();
    }

    private static Stream<RacingCreateDto> provideRacingCreateDto() {

        return Stream.of(
                RacingCreateDto.createByConsole("붕붕, 씽씽", 2)
        );
    }

    private static Stream<Arguments> providePoorRacingCreateDto() {

        return Stream.of(
            Arguments.of(RacingCreateDto.createByConsole("붕붕, 씽씽", 0), "레이싱 카에게 주어진 시도횟수가 0 입니다."),
            Arguments.of(RacingCreateDto.createByConsole(",", 5), "레이싱 카의 이름은 존재하지 않습니다.")
        );
    }

    private static Stream<Arguments> provideNullRacingCreateDto() {

        return Stream.of(
                Arguments.of(null, "레이싱 카를 만들 수 없습니다.")
        );
    }
}

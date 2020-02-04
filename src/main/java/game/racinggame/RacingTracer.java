package game.racinggame;

import java.util.*;
import java.util.stream.IntStream;

public class RacingTracer {

    private static final String NEW_LINE = "\n";
    private final int tryCount;
    private final List<RacingCar> racingCars;

    RacingTracer(final List<RacingCar> racingCars, final int tryCount) {

        validateRacingCars(racingCars);
        validateTryCount(tryCount);

        this.racingCars = racingCars;
        this.tryCount = tryCount;
    }

    public RacingTracerResultDevice createRacingTracerResultDevice() {
        return new RacingTracerResultDevice(this);
    }

    private void validateRacingCars(final List<RacingCar> racingCars) {
        if (racingCars == null || racingCars.size() == 0) {
            throw new IllegalArgumentException("레이싱 카는 널 또는 사이즈가 0 이기 때문에 추적할 수 없습니다.");
        }
    }

    private void validateTryCount(final int tryCount) {
        if (tryCount == 0) {
            throw new IllegalArgumentException("레이싱 카의 전진횟수는 0 이기 때문에 추척할 수 없습니다.");
        }
    }

    public String getTraces() {

        final StringBuilder traces = new StringBuilder();

        IntStream.rangeClosed(1, tryCount)
                .forEach(round -> {
                    racingCars.forEach(racingCar -> {
                        traces.append(racingCar.getMyTracesByRound(round));
                        traces.append(NEW_LINE); });
                    traces.append(NEW_LINE);
                });

        return traces.toString();
    }

    public List<String> getWinners() {

        final List<String> winners = new ArrayList<>();

        final int maxTraces = racingCars.stream()
                .mapToInt(RacingCar::getMyLastTracesLength)
                .max()
                .orElse(0);

        racingCars.stream()
                .filter(racingCar -> racingCar.getMyLastTracesLength() >= maxTraces)
                .forEach(racingCar -> winners.add(racingCar.getName()));

        return winners;
    }
}

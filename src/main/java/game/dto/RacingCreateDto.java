package game.dto;

import game.racinggame.util.RacingUtils;

import java.util.Arrays;
import java.util.List;

public class RacingCreateDto {

    private int tryCount;
    private List<String> carNames;

    private RacingCreateDto(){}

    private RacingCreateDto(final String[] carNames, final int tryCount){
        this.tryCount = tryCount;
        this.carNames = Arrays.asList(carNames);
    }

    public static RacingCreateDto createByWeb(final String carNames, final int tryCount) {
        return new RacingCreateDto(RacingUtils.splitLineByWhiteSpace(carNames), tryCount);
    }

    public static RacingCreateDto createByConsole(final String carNames, final int tryCount) {
        return new RacingCreateDto(RacingUtils.splitLineByComma(carNames), tryCount);
    }

    public int getTryCount() {
        return tryCount;
    }

    public String getCarNameByIndex(final int index) {
        return carNames.get(index);
    }

    public int getCarSize() {
        return carNames.size();
    }
}

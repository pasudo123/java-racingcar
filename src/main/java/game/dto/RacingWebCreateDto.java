package game.dto;

import java.util.List;

public class RacingWebCreateDto {

    private int tryCount;
    private List<String> carNames;

    public RacingWebCreateDto(final List<String> carNames, final int tryCount){
        this.carNames = carNames;
        this.tryCount = tryCount;
    }

    public int getTryCount() {
        return tryCount;
    }

    public List<String> getCarNames() {
        return carNames;
    }
}

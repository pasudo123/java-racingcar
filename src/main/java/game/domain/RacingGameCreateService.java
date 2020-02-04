package game.domain;

import java.util.List;

public class RacingGameCreateService {

    private RacingGameCreateService(){}

    public static RacingGameCreateService create() {
        return new RacingGameCreateService();
    }

    public List<String> getRacingNames(final String carNames) {

        return RacingWebUtils.splitLineBySpace(carNames);
    }
}

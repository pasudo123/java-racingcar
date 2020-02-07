package game.service;

import game.dto.RacingCreateDto;
import game.racinggame.RacingCars;
import game.racinggame.RacingTracer;

public class RacingGameStartService {

    private RacingGameStartService(){}

    public static RacingGameStartService create() {
        return new RacingGameStartService();
    }

    public RacingTracer startGame(final RacingCreateDto createDto){

        final RacingCars racingCars = new RacingCars();
        racingCars.setUpRacing(createDto);

        racingCars.go();

        return racingCars.createRacingTracer();
    }
}

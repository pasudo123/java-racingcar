package game.domain;

import game.dto.RacingCreateDto;
import game.dto.RacingWebCreateDto;
import game.racinggame.RacingCars;
import game.racinggame.RacingTracer;

public class RacingGameStartService {

    private RacingGameStartService(){}

    public static RacingGameStartService create() {
        return new RacingGameStartService();
    }

    public RacingTracer startGame(final RacingWebCreateDto dto){

        final RacingCars racingCars = new RacingCars();
        racingCars.setUpRacing(RacingCreateDto.create(dto));

        racingCars.go();

        return racingCars.createRacingTracer();
    }
}

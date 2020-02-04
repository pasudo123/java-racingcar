package game.racinggame;

import game.dto.RacingCreateDto;
import game.racinggame.strategy.RandomMovementStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public final class RacingCars {

    private List<RacingCar> racingCars = new ArrayList<>();

    public RacingCars() {}

    public void setUpRacing(final RacingCreateDto dto){

        this.validateCreateDto(dto);

        IntStream.range(0, dto.getCarSize())
                .forEach(index -> racingCars.add(
                        new RacingCar(
                                dto.getTryCount(),
                                dto.getCarNameByIndex(index),
                                new RandomMovementStrategy())));
    }

    private void validateCreateDto(final RacingCreateDto dto) {

        /**
         * dto 객체에 대해서 유효성 검사를 실시하는 메소드는 하나의 행위라고 판단하였기 때문에
         * 각각 예외처리하는 메소드로 추출하지 않고 단일 메소드 내에 처리하였다.
         *  **/

        if (dto == null) {
            throw new NullPointerException("레이싱 카를 만들 수 없습니다.");
        }

        if(dto.getTryCount() == 0) {
            throw new IllegalArgumentException("레이싱 카에게 주어진 시도횟수가 0 입니다.");
        }

        if(dto.getCarSize() == 0) {
            throw new IllegalArgumentException("레이싱 카의 이름은 존재하지 않습니다.");
        }
    }

    private int getTryCount() {
        return racingCars.get(0).getTryCount();
    }

    int getRacingCarsCount(){
        return racingCars.size();
    }

    public void go(){
        racingCars.forEach(RacingCar::move);
    }

    public RacingTracer createRacingTracer(){
        return new RacingTracer(this.racingCars, this.getTryCount());
    }
}

package game.racinggame.console;

import game.dto.RacingCreateDto;

import java.util.Scanner;

public class RacingInputDevice  {

    private final Scanner scanner = new Scanner(System.in);

    public RacingCreateDto doInput() {

        System.out.println("경주할 자동차 이름을 입력하세요. (이름은 쉼표(,) 를 기준으로 구분)");
        final String carNames = scanner.nextLine();

        System.out.println("시도할 횟수는 몇 회 인가요?");
        final int tryCount = Integer.parseInt(scanner.nextLine());

        return new RacingCreateDto(carNames, tryCount);
    }
}

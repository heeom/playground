package owen.racingcar.domain;

import owen.racingcar.RandomMovingStrategy;
import owen.racingcar.domain.car.Car;
import owen.racingcar.domain.car.Cars;
import owen.racingcar.utils.RacingUtils;

public class RandomRacing {

    private final Cars cars;
    private int gameTry;

    private static int MAX_BOUND = 10;

    public RandomRacing(Cars cars, int gameTry) {
        this.cars = cars;
        this.gameTry = gameTry;
    }

    public void startRandomRacing() {
        /**
         * 주어진 시도 횟수만큼 racing 시작
         */
        for (int i = 0; i <gameTry; i++) {
            startRacing();
        }
    }

    private void startRacing() {
        for (Car car : cars.getCarList()) {
            car.move(new RandomMovingStrategy());
            convert(car);
        }
        RacingUtils.printEndLine();
    }

    private void convert(Car car) {
        StringBuilder beauty = new StringBuilder();
        for (int i = 0; i < car.getPosition(); i++) {
            beauty.append("-");
        }
        RacingUtils.printConvert(car.getName(), beauty);
    }
}

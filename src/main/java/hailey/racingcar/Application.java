package hailey.racingcar;

import hailey.racingcar.domain.Car;
import hailey.racingcar.domain.Score;
import hailey.racingcar.util.ValidationUtils;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;


public class Application {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("경주할 자동차 이름을 입력하세요");
        System.out.println("이름은 쉼표(,)를 기준으로 구분해주세요");
        String inputCarNames = scanner.nextLine();
        Map<Car, Score> cars = readyGame(inputCarNames);

        System.out.println("시도할 회수는 몇회인가요?");
        int repeatNumber = scanner.nextInt();
        for (int i = 0; i < repeatNumber; i++) {
            cars.forEach((k, v) -> {
                Random random = new Random();
                int advanceNumber = random.nextInt(100) % 9;
                if (advanceNumber >= 4) {
                    v.setScore(v.getScore() + advanceNumber);
                    v.getProgress().append("-".repeat(advanceNumber));
                }
                System.out.println(k.getName() + " : " + v.getProgress().toString());
            });
            System.out.println();
        }

        Map<Car, Score> winners = new LinkedHashMap<>();
        final int[] max = {0};
        cars.forEach((k, v) -> {
            if (v.getScore() == max[0]) {
                winners.put(k, v);
            } else if (v.getScore() > max[0]) {
                winners.clear();
                winners.put(k, v);
                max[0] = v.getScore();
            }
        });

        Iterator<Car> winner = winners.keySet().iterator();
        int count = 0;
        while(winner.hasNext()) {
            String win = winner.next().getName();
            System.out.print(win);
            count++;
            if (winners.size() != count) {
                System.out.print(", ");
            }
        }
        System.out.println(" 가 최종 우승했습니다.");
    }

    private static Map<Car, Score> readyGame(String inputCarNames) {
        Map<Car, Score> cars = new LinkedHashMap<>();
        String[] carNames = inputCarNames.replace(" ", "").split(",");
        for (int i = 0; i < carNames.length; i++) {
            if (ValidationUtils.validate(i, carNames[i], carNames)) {
                cars.put(new Car(carNames[i]), new Score());
            }
        }
        return cars;
    }
}

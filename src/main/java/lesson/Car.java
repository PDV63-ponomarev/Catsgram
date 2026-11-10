package lesson;

import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class Car {
    private int power;
    private int weight;
    private int height;
    private int lenght;

    @Builder.Default
    private String color = "black";
    @Builder.Default
    private int numberOfWheels = 4;
    @Builder.Default
    private int numbersOfDoors = 5;

    public static void main(String[] args) {
        Car firstCar = Car.builder() //вызов класса-строителя
                .color("white")
                .power(200)
                .height(4280)
                .lenght(1540)
                .build();
    }

}

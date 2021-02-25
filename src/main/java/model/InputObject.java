package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InputObject {

    private int durationInSeconds;
    private int numOfIntersections;
    private int numOfStreets;
    private int numOfCars;
    private int bonusPointsPerFinishedCar;
    private List<Street> streets;
    private List<Car> cars;
}

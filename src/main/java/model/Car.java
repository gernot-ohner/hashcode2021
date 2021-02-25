package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {
    private int numOfStreets;
    private List<Street> streetsToTravel;

    public int getRemainingDuration(Street street) {
        final var i = streetsToTravel.indexOf(street);
        if (i == -1) {
            return 0;
        } else {
            return streetsToTravel.subList(i, streetsToTravel.size())
                    .stream().mapToInt(Street::getLength).sum();
        }
    }
}

import model.*;
import org.apache.commons.collections4.CollectionUtils;

import java.util.TreeMap;
import java.util.stream.Collectors;

public class Solution {
    public OutputObject compute(InputObject inputObject) {

        final var steetCounts = getSteetCounts(inputObject);

        final var outputObject = new OutputObject();

        for (final Intersection intsec: inputObject.getIntersections()) {
            final var schedule = new Schedule();
            schedule.setIntersection(intsec);

            for (final Street street: intsec.getIncomingStreets()) {
                final var sd = new StreetLightSwitchDirective(street, 1);
                schedule.addSwitchDirective(sd);
            }

            outputObject.addSchedule(schedule);
        }

        return outputObject;
    }

    private TreeMap<Integer, String> getSteetCounts(InputObject inputObject) {
        final var streetNames = inputObject.getCars().stream().flatMap(car -> car.getStreetsToTravel().stream()).collect(Collectors.toList());

        final var steetCounts = new TreeMap<>(CollectionUtils.getCardinalityMap(streetNames));

        var inverted = new TreeMap<Integer, String>();
        steetCounts.descendingMap().forEach((s, integer) -> inverted.put(integer, s));

        System.out.println(inverted.descendingMap());
        return inverted;
    }
}

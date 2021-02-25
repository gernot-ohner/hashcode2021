package solution;

import model.*;
import org.apache.commons.collections4.CollectionUtils;

import java.util.TreeMap;
import java.util.stream.Collectors;

public class SolutionV2 implements ISolution {
    @Override
    public OutputObject compute(InputObject inputObject) {

        final int periodPerStreet = 1;

        final var outputObject = new OutputObject();
        final var carCounts = getStreetCounts(inputObject);
        System.out.println(carCounts);

        for (final Intersection intersection: inputObject.getIntersections()) {
            final var schedule = new Schedule();
            schedule.setIntersection(intersection);
            final var sum = intersection.getIncomingStreets().stream().mapToInt(street ->
                    carCounts.getOrDefault(street.getName(), 0)).sum();

            final var numOfIncomingStreets = intersection.getIncomingStreets().size();

            for (final Street street: intersection.getIncomingStreets()) {
                final var d = carCounts.getOrDefault(street.getName(), 0);

                float duration = ((float) d) / Math.max(sum, 1) * (numOfIncomingStreets * periodPerStreet);
                if (duration != 0 && (d * 100) > sum) {
                    final var sd = new StreetLightSwitchDirective(street, (int) Math.ceil(duration));
                    schedule.addSwitchDirective(sd);
                }
            }

            if (schedule.getScheduleEntries() != null && !schedule.getScheduleEntries().isEmpty()) {
                outputObject.addSchedule(schedule);
            }
        }

        return outputObject;
    }


    private TreeMap<String, Integer> getStreetCounts(InputObject inputObject) {
        final var streetNames = inputObject.getCars().stream().flatMap(car -> car.getStreetsToTravel().stream().map(Street::getName)).collect(Collectors.toList());

        return new TreeMap<>(CollectionUtils.getCardinalityMap(streetNames));
    }
}

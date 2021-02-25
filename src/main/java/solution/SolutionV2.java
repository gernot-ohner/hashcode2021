package solution;

import model.*;
import org.apache.commons.collections4.CollectionUtils;

import java.util.TreeMap;
import java.util.stream.Collectors;

public class SolutionV2 implements ISolution {
    @Override
    public OutputObject compute(InputObject inputObject) {

        final int periodPerStreet = 2;

        final var outputObject = new OutputObject();
        final var streetCounts = getStreetCounts(inputObject);
        System.out.println(streetCounts);

        for (final Intersection intsec: inputObject.getIntersections()) {
            final var schedule = new Schedule();
            schedule.setIntersection(intsec);
            final var sum = intsec.getIncomingStreets().stream().mapToInt(street ->
                    streetCounts.getOrDefault(street.getName(), 0)).sum();
            final var numOfIncomingStreets = intsec.getIncomingStreets().size();

            for (final Street street: intsec.getIncomingStreets()) {
                final var d = streetCounts.getOrDefault(street.getName(), 0);
                float duration = ((float) d) / Math.max(sum, 1) * (numOfIncomingStreets * periodPerStreet);
                if (duration != 0) {
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
        final var streetNames = inputObject.getCars().stream().flatMap(car -> car.getStreetsToTravel().stream()).collect(Collectors.toList());

        return new TreeMap<>(CollectionUtils.getCardinalityMap(streetNames));
    }
}

package solution;

import model.*;
import org.apache.commons.collections4.CollectionUtils;

import java.util.TreeMap;
import java.util.stream.Collectors;

public class Solution implements ISolution {
    public OutputObject compute(InputObject inputObject) {

        final var steetCounts = getSteetCounts(inputObject);

        final var outputObject = new OutputObject();

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

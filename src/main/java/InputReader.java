import model.Car;
import model.InputObject;
import model.Street;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputReader {

    public InputObject parse(String filename) throws IOException, URISyntaxException {
        final var strings = readFile(filename);

        final var metadata = Arrays.stream(strings.get(0).split(" "))
                .map(Integer::valueOf).collect(Collectors.toList());
        // TODO extract the metadata into individual variables
        final var durationInSeconds = metadata.get(0);
        final var numOfIntersections = metadata.get(1);
        final var numOfStreets = metadata.get(2);
        final var numOfCars = metadata.get(3);
        final var bonusPointsPerFinishedCar = metadata.get(4);

        strings.remove(0);

        final var streets = strings.stream().limit(numOfStreets)
                .map(s -> {
                    final var strings1 = Arrays.asList(s.split(" "));
                    return new Street(
                            Integer.parseInt(strings1.get(0)),
                            Integer.parseInt(strings1.get(1)),
                            strings1.get(2),
                            Integer.parseInt(strings1.get(3))
                    );
                }).collect(Collectors.toList());

        if (numOfStreets > 0) {
            strings.subList(0, numOfStreets).clear();
        }

        final var cars = strings.stream().map(s -> {
            final var s1 = Arrays.asList(s.split(" "));
            final var numOfStreetsForCar = Integer.parseInt(s1.get(0));
            return new Car(
                    numOfStreetsForCar,
                    s1.subList(1, s1.size())
            );
        }).collect(Collectors.toList());



        final var inputObject = new InputObject();
        inputObject.setDurationInSeconds(durationInSeconds);
        inputObject.setNumOfCars(numOfCars);
        inputObject.setNumOfIntersections(numOfIntersections);
        inputObject.setNumOfStreets(numOfStreets);
        inputObject.setBonusPointsPerFinishedCar(bonusPointsPerFinishedCar);
        inputObject.setStreets(streets);
        inputObject.setCars(cars);
        return inputObject;
    }

    public List<String> readFile(String filename) throws IOException, URISyntaxException {
        final var uri = getClass()
                .getClassLoader()
                .getResource(filename)
                .toURI();
        final var path = Paths.get(uri);
        final var lines = Files.lines(path);

        return lines.collect(Collectors.toList());
    }

}

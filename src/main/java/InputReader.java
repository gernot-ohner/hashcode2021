import model.InputObject;

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

        strings.remove(0);

//        AtomicInteger index = new AtomicInteger(0);
//        final var things = getIntegers(strings.get(0)).stream()
//                .map(thing -> new Thing(index.getAndIncrement(), thing))
//                .sorted(Comparator.comparing(Thing::getScore).reversed())
//                .collect(Collectors.toList());
        strings.remove(0);

        for (int i = 0; i < strings.size() - 1; i = i + 2) {
            final var s = strings.get(i).split(" ");
        }

        return new InputObject();
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

    private List<Integer> getIntegers(String s) {
        return Arrays.stream(s.split(" "))
                .map(Integer::valueOf)
                .collect(Collectors.toList());
    }
}

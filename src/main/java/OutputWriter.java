import model.OutputObject;
import model.Schedule;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

public class OutputWriter {

    public void writeOutput(String filename, OutputObject outputObject) throws IOException {

        final var now = LocalTime.now().format(DateTimeFormatter.ofPattern("hh_mm"));
        final var taskFolder = new File(String.valueOf(filename.charAt(0)));
        taskFolder.mkdir();
        final var pathname = filename.charAt(0) + "/" + now + ".txt";
        final var file = new File(pathname);
        file.createNewFile();
        final var fw = new FileWriter(file);

        fw.write(outputObject.getSchedules().size() + "\n");

        for (final Schedule schedule: outputObject.getSchedules()) {
            fw.write(schedule.getIntersection().getId() + "\n");
            fw.write(schedule.getScheduleEntries().size() + "\n");
            final var bookString = schedule.getScheduleEntries().stream()
                        .map(se -> String.format("%s %d\n", se.getStreet().getName(), se.getGreenDuration()))
                        .collect(Collectors.joining(""));
            fw.write(bookString);
        }

        fw.close();
    }
}

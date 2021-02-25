package solution;

import model.*;

public class SolutionV1 implements ISolution {
    @Override
    public OutputObject compute(InputObject inputObject) {

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
}

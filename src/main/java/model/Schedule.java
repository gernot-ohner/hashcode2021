package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {
    private Intersection intersection;
    private List<StreetLightSwitchDirective> scheduleEntries;

    public void addSwitchDirective(StreetLightSwitchDirective d) {
        if (scheduleEntries == null) {
            scheduleEntries = new ArrayList<>();
        }
        scheduleEntries.add(d);
    }


}

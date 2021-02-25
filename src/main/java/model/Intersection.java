package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Intersection {
    private List<Street> incomingStreets;
    private List<Street> outgoingStreets;
}

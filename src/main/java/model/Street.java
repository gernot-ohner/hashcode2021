package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Street {

    private int startIntersectionId;
    private int endIntersectionId;
    private String name;
    private int length;

}

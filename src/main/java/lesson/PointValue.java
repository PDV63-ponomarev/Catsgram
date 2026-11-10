package lesson;

import lombok.Value;
import lombok.Builder;

@Value
@Builder(toBuilder = true)
public class PointValue {
    private int x;
    private int y;
}

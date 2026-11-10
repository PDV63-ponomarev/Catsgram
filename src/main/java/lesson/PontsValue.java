package lesson;

public class PontsValue {
    public static void main(String[] args) {
        final PointValue point1 = PointValue.builder().x(0).y(0).build();
        System.out.println(point1);
        final PointValue point2 = point1.toBuilder().y(3).build();
        System.out.println(point2);
    }
}

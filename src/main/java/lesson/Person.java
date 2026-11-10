package lesson;

import lombok.Data;
import lombok.NonNull;

//аннотация @Data обьеденяет в себя геттеры, сеттеры, toString, equals и hashCode
@Data
public class Person {
    @NonNull // указывает что поля не принимаю значение null
    private final String name;
    private final int age;
    private String profession;
    private String email;
}

package chap03.functionalInterfaceExample;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class ConsumerExample<T> {

    public static <T> void forEach(List<T> list, Consumer<T> c) {
        for (T t : list) {
            c.accept(t);
        }
    }

    public static void main(String[] args) {
        forEach(
                Arrays.asList(1, 2, 3, 4, 5),
                (Integer i) -> System.out.println(i)
        );
    }
}

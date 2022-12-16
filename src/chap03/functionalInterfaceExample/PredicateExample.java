package chap03.functionalInterfaceExample;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class PredicateExample<T> {

    public static <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> results = new ArrayList<>();
        for (T t : list) {
            if (p.test(t)) {
                results.add(t);
            }
        }
        return results;
    }

    public static void main(String[] args) {
        List<String> listOfStrings = new ArrayList<>();
        listOfStrings.add("adsd"); listOfStrings.add("wqe");

        Predicate<String> nonEmptyStringPredicate = (String s) -> !s.isEmpty();
        List<String> nonEmpty = filter(listOfStrings, nonEmptyStringPredicate);

        System.out.println("nonEmpty = " + nonEmpty);
    }
}



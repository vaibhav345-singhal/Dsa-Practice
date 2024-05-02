package Streams;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Questions {

        public static void main(String[] args) {

                List<Integer> list = Arrays.asList(1, 2, 4, 5, 6, 7, 8, 9, 10);

                sumOfAllNumbers(list);
                averageOfAllNumbers(list);
                squareFilterAverage(list);
                evenOdd(list);

                List<Integer> numbers = Arrays.asList(2, 223, 224, 204, 213, 456, 23, 56, 42, 55, 654);
                startingWith2Prefix(numbers);

                List<Integer> nums = Arrays.asList(1, 1, 1, 2, 23, 4, 4, 23, 4, 2, 5, 6, 7);
                printDuplicate(nums);

                minMax(list);

                groupByFun();
        }

        public static void groupByFun() {

                List<Employee> list = List.of(new Employee(1, "ramesh", 25), new Employee(2, "ramesh", 30),
                                new Employee(3, "ram", 30),
                                new Employee(4, "sakshi", 25), new Employee(5, "ram", 30));

                // Map<Integer, List<Employee>> empMap = list.stream()
                // .collect(Collectors.groupingBy(emp -> emp.getAge()));

                // Map<Integer, Set<Employee>> empMap = list.stream()
                // .collect(Collectors.groupingBy(emp -> emp.getAge(), Collectors.toSet()));

                Map<Integer, Set<Employee>> empMap = list.stream()
                                .collect(Collectors.groupingBy(emp -> emp.getAge(), TreeMap::new, Collectors.toSet()));

                System.out.println(empMap);
        }

        public static void minMax(List<Integer> list) {

                Optional<Integer> max = list.stream()
                                .max((a, b) -> a - b);

                // or
                Integer maxData = list.stream()
                                .max(Comparator.comparing(a -> a)).get();

                System.out.println(maxData);

                System.out.println(max.get());

                Optional<Integer> min = list.stream()
                                .min((a, b) -> a - b);
                System.out.println(min.get());

        }

        public static void printDuplicate(List<Integer> list) {

                // this is correct but some issue is there with collections
                Set<Integer> dup = list.stream()
                                .filter(a -> Collections.frequency(list, a) > 1)
                                .collect(Collectors.toSet());

                System.out.println(dup);

                Set<Integer> set = new HashSet<>();
                Set<Integer> dup2 = list.stream()
                                .filter(a -> !set.add(a))
                                .collect(Collectors.toSet());

                System.out.println(dup2);

        }

        public static void startingWith2Prefix(List<Integer> list) {
                List<Integer> ans = list.stream()
                                .map(a -> String.valueOf(a))
                                .filter(a -> a.startsWith("2") || a.startsWith("-2"))
                                .map(a -> Integer.parseInt(a))
                                .collect(Collectors.toList());

                System.out.println(ans);
        }

        public static void evenOdd(List<Integer> list) {

                List<Integer> evIntegers = list.stream()
                                .filter(a -> a % 2 == 0)
                                .collect(Collectors.toList());

                System.out.println(evIntegers);

                List<Integer> odIntegers = list.stream()
                                .filter(a -> a % 2 != 0)
                                .collect(Collectors.toList());
                System.out.println(odIntegers);
        }

        public static void squareFilterAverage(List<Integer> list) {

                OptionalDouble optionalDouble = list.stream()
                                .map(a -> a * a)
                                // .peek(a -> System.out.println(a))
                                .filter(a -> a > 5)
                                // .peek(a -> System.out.println(a))
                                .mapToInt(a -> a)
                                // .peek(a -> System.out.println(a))
                                .average();

                System.out.println(optionalDouble.getAsDouble());
        }

        public static void averageOfAllNumbers(List<Integer> list) {

                // directly cant apply average have to use mapToInt first to convert all numbers
                // into integer
                OptionalDouble average = list.stream().mapToInt(e -> e).average();

                if (average.isPresent())
                        System.out.println(average.getAsDouble());

        }

        public static void sumOfAllNumbers(List<Integer> list) {
                Optional<Integer> sum = list.stream().reduce((Integer val1, Integer val2) -> val1 + val2);
                if (sum.isPresent())
                        System.out.println(sum.get());

        }
}

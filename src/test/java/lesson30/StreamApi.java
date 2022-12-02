package lesson30;/*
Created by Pavel Gryshchenko on 13.11.2022
*/

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class StreamApi {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("one");
        list.add("two");
        list.add("two");

        for (String i : list) {
            System.out.println(i);
        }
        //write list into Stream and print
        System.out.println("\nWrite list into Stream and print");
        Stream<String> exampleStream = list.stream();
        exampleStream.forEach(item -> System.out.println("Stream out " + item)); //item - iterator

        //write Set into Stream and print
        System.out.println("\nWrite Set into Stream and print");
        Set<String> exampleSetStream = list.stream().collect(Collectors.toSet());
        exampleSetStream.forEach(i -> System.out.println(i));

        //filtered Set by value "two" => should out only ine "two" from list
        System.out.println("\nWrite list into stream and filter by value \"two\" => should out only one \"two\" from list");
        Set<String> filteredSet = list.stream()
                .filter(item -> item.equals("two"))
                .collect(Collectors.toSet());
        filteredSet.forEach(j -> System.out.println(j));

        //map method - will transform each element of list to UpperCase
        System.out.println("\nMap method - will transform each element of list to UpperCase");
        Stream<String> toUpperCaseList = list.stream()
                .map(x -> x.toUpperCase());
        toUpperCaseList.forEach(k -> System.out.println(k));

        //create stream from two lists and print
        System.out.println("\nCreate stream from two lists");
        List<String> streamFromTwoLists = Stream.of(List.of("one", "two"), List.of("three", "four"))
                .flatMap(j -> j.stream()).collect(Collectors.toList());
        streamFromTwoLists.forEach(i -> System.out.println(i));

        //Cycle "for" return sum of array
        int count = 0;
        for (int element : new int[]{1, 2, 3}) {
            count += element;
        }
        System.out.println("Sum from for " + count);
        //Replace above cycle "for" with stream
        int countExample = Stream.of(1, 2, 3)
                .reduce(0, (sum, element) -> sum + element);
        System.out.println("Sum from stream " + countExample);

        //sorted() method of stream
        Stream.of("one", "two").sorted().collect(Collectors.toList());
        //limit() method of stream will limit output by number of elements shown as parameter of limit => 1 in this case
        Stream.of("one", "two").limit(1).collect(Collectors.toList());
        //distinct() method display only distinct values
        Stream.of("one", "two", "two").distinct().collect(Collectors.toList());
        //skip() skips number of first elements => 2 in this case
        Stream.of("one", "two", "three").skip(2).collect(Collectors.toList());
    }

}

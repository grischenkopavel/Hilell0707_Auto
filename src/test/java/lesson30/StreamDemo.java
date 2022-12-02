package lesson30;/*
Created by Pavel Gryshchenko on 13.11.2022
*/

import java.util.List;
import java.util.stream.Stream;

class StreamDemo {
    public static void main(String[] args) {
        List<Car> cars = List.of(
                new Car("BMW", 2012),
                new Car("BMW", 2020),
                new Car("Bmw", 2014),
                new Car("Audi", 2013),
                new Car("TESLA", 2017),
                new Car("HONDA", 2017)
        );
        //place list into stream carStream
        Stream<Car> carStream = cars.stream();
        //place list into stream carStream2
        Stream<Car> carStream2 = cars.stream();
        //filter stream by model != BMW and year > 2012
        Stream<Car> filteredStreamWithoutBMWandYearMoreThan2012 = carStream
                .filter(x -> x.getYear() > 2012)
                .filter(x -> !"BMW".equalsIgnoreCase(x.getModel()));
        //filter stream by model == BMW and year > 2012
        Stream<Car> sortedStreamBmvOnly = carStream2
                .filter(x -> x.getYear() > 2012)
                .filter(x -> "BMW".equalsIgnoreCase(x.getModel()));
        filteredStreamWithoutBMWandYearMoreThan2012.forEach(x -> System.out.println(x.getModel()));
        System.out.println("------------");
        sortedStreamBmvOnly.forEach(x -> System.out.println(x.getModel()));
    }
}

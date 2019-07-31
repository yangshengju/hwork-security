package com.hwork.lambda;

import java.util.List;

/**
 * Created by yangshengju on 2019-7-27.
 */
public class Test01ForEach {
    public static void main(String[] args) {
        List<Person> pl = Person.createShortList();

        System.out.println("\n=== Western Phone List ===");
        pl.forEach( p -> p.printWesternName() );
        System.out.println("\n=== Eastern Phone List ===");
        pl.forEach(Person::printEasternName);
    }
}

package com.hwork.lambda;

import java.util.List;

/**
 * Created by yangshengju on 2019-7-27.
 */
public class Test02Filter {
    public static void main(String[] args) {
        List<Person> pl = Person.createShortList();

        SearchCriteria search = SearchCriteria.getInstance();

        System.out.println("\n=== Western Pilot Phone List ===");

        pl.stream().filter(search.getCriteria("isPilot")).forEach(Person::printWesternName);
    }
}

package com.hwork.lambda;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by yangshengju on 2019-7-28.
 */
public class Test03toList {
    public static void main(String[] args) {
        List<Person> pl = Person.createShortList();

        SearchCriteria search = SearchCriteria.getInstance();

        // Make a new list after filtering.
        List<Person> pilotList = pl
                .stream()
                .filter(search.getCriteria("isPilot"))
                .collect(Collectors.toList());

        System.out.println("\n=== Western Pilot Phone List ===");
        pilotList.forEach(Person::printWesternName);
    }
}

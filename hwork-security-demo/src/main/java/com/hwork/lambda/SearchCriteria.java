package com.hwork.lambda;

import java.util.Map;
import java.util.HashMap;
import java.util.function.Predicate;

/**
 * Created by yangshengju on 2019-7-27.
 */
public class SearchCriteria {
    private final Map<String,Predicate<Person>> searchMap = new HashMap<>();
    private SearchCriteria() {
        initSearchMap();
    }

    private void initSearchMap() {
        Predicate<Person> isDriver = p -> p.getAge() >= 16;
        Predicate<Person> isDraftee = p -> p.getAge() >= 18 && p.getAge() <= 25 && p.getGender() == Gender.MALE;
        Predicate<Person> isPilot = p -> p.getAge() >= 23 && p.getAge() <= 65;
        searchMap.put("isDriver",isDriver);
        searchMap.put("isDraftee",isDraftee);
        searchMap.put("isPilot",isPilot);
    }

    public Predicate<Person> getCriteria(String PredicateName) {
        Predicate<Person> target;

        target = searchMap.get(PredicateName);

        if (target == null) {

            System.out.println("Search Criteria not found... ");
            System.exit(1);

        }

        return target;
    }

    public static SearchCriteria getInstance() {
        return new SearchCriteria();
    }
}

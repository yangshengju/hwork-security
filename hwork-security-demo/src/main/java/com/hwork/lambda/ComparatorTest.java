package com.hwork.lambda;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by yangshengju on 2019-7-25.
 */
public class ComparatorTest {
    public static void main(String[] args) {
        List<Person> personList = Person.createShortList();
        /*Collections.sort(personList, new Comparator<Person>() {
            @Override
            public int compare(Person p1, Person p2) {
                return p1.getSurName().compareTo(p2.getSurName());
            }
        });

        System.out.println("=== Sorted Asc SurName ===");
        for(Person p:personList){
            p.printName();
        }*/

        // Print Asc
        System.out.println("=== Sorted Asc SurName ===");
        Collections.sort(personList, (  p1, p2) -> p1.getSurName().compareTo(p2.getSurName()));
        for(Person p:personList){
            p.printName();
        }

        // Print Desc
        System.out.println("=== Sorted Desc SurName ===");
        Collections.sort(personList,(p1,p2)->p1.getSurName().compareTo(p2.getSurName()));
        for(Person p:personList){
            p.printName();
        }
    }
}

package com.hwork.lambda;

import com.hwork.lambda.MyTest;
import com.hwork.lambda.Person;
import com.hwork.lambda.RoboContactAnon;

import java.util.List;

/**
 * Created by yangshengju on 2019-7-26.
 */
public class RoboCallTest03 {
    public static void main(String[] args) {
        List<Person> pl = Person.createShortList();
        RoboContactAnon robo = new RoboContactAnon();

        System.out.println("\n==== Test 03 ====");
        System.out.println("\n=== Calling all Drivers ===");
        robo.phoneContacts(pl, new MyTest<Person>() {
            @Override
            public boolean test(Person person) {
                return person.getAge() >=16;
            }
        });

        System.out.println("\n=== Emailing all Draftees ===");
        robo.emailContacts(pl, new MyTest<Person>() {
            @Override
            public boolean test(Person p) {
                return p.getAge() >= 18 && p.getAge() <= 25 && p.getGender() == Gender.MALE;
            }
        });

        System.out.println("\n=== Mail all Pilots ===");
        robo.mailContacts(pl,
                new MyTest<Person>(){
                    @Override
                    public boolean test(Person p){
                        return p.getAge() >= 23 && p.getAge() <= 65;
                    }
                }
        );
    }
}

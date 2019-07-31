package com.hwork.lambda;

import java.util.List;
import java.util.function.Predicate;

/**
 * Created by yangshengju on 2019-7-26.
 */
public class RoboCallTest04 {
    public static void main(String[] args) {

        List<Person> pl = Person.createShortList();
        RoboContactsLambda robo = new RoboContactsLambda();

        Predicate<Person> isDriver = p->p.getAge()>=16;
        Predicate<Person> isDraftee=p->p.getAge() >= 18 && p.getAge() <= 25 && p.getGender() == Gender.MALE;
        Predicate<Person> isPilot=p->p.getAge()>=23&&p.getAge()<=65;

        robo.phoneContacts(pl,isDriver);
        robo.emailContacts(pl,isDraftee);
        robo.mailContacts(pl,isPilot);
    }
}

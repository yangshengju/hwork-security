package com.hwork.lambda;

import java.util.List;
import java.util.function.Function;

/**
 * Created by yangshengju on 2019-7-27.
 */
public class NameTestNew {
    public static void main(String[] args) {
        System.out.println("\n==== NameTestNew ===");

        List<Person> list1 = Person.createShortList();

// Print Custom First Name and e-mail
        System.out.println("===Custom List===");

        Function<Person,String> personInfo = p->"Name: " + p.getGivenName() + " EMail: " + p.getEmail();
// Define Western and Eastern Lambdas

        Function<Person, String> westernStyle = p -> {
            return "\nName: " + p.getGivenName() + " " + p.getSurName() + "\n" +
                    "Age: " + p.getAge() + "  " + "Gender: " + p.getGender() + "\n" +
                    "EMail: " + p.getEmail() + "\n" +
                    "Phone: " + p.getPhone() + "\n" +
                    "Address: " + p.getAddress();
        };

        Function<Person, String> easternStyle =  p -> "\nName: " + p.getSurName() + " "
                + p.getGivenName() + "\n" + "Age: " + p.getAge() + "  " +
                "Gender: " + p.getGender() + "\n" +
                "EMail: " + p.getEmail() + "\n" +
                "Phone: " + p.getPhone() + "\n" +
                "Address: " + p.getAddress();

        /*for (Person person:list1){
            System.out.println(personInfo.apply(person));
        }
*/
        for (Person person:list1){
            System.out.println(westernStyle.apply(person));
        }

        // Print Eastern List
        System.out.println("\n===Eastern List===");
        for (Person person:list1){
            System.out.println(easternStyle.apply(person));
        }
    }
}

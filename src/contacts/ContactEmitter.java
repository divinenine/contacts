package contacts;

import java.util.regex.Pattern;

import static contacts.Contact.validatePhoneNumber;
import static contacts.Main.scn;
import static contacts.Person.validateNumber;

public class ContactEmitter extends ContactFactory {

    @Override
    Contact createContact(String type) {
        if (type.equals("person")) {
            System.out.println("Enter the name:");
            String name = scn.nextLine();
            System.out.println("Enter the surname:");
            String surname = scn.nextLine();
            System.out.println("Enter the birth date:");
            String birthDate = scn.nextLine();
            if (!Person.validateDateBirth(birthDate)) {
                System.out.println("Bad birth date!");
                birthDate = "[no data]";
            }
            System.out.println("Enter the gender (M, F):");
            String gender = scn.nextLine();
            if (!Person.validateGender(gender)) {
                System.out.println("Bad gender!");
                gender = "[no data]";
            }
            System.out.println("Enter the number:");

            String number = scn.nextLine();

            number = validateNumber(number);

            return new Person(name, surname, birthDate, gender, number);

        } else if (type.equals("organization")) {
            System.out.println("Enter the organization name:");
            String orgName = scn.nextLine();
            System.out.println("Enter the address:");
            String address = scn.nextLine();
            System.out.println("Enter the number:");
            String number = scn.nextLine();
            validatePhoneNumber(number);
            return new Organization(orgName, address, number);
        } else {
            throw new IllegalArgumentException();
        }
    }


}

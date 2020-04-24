package contacts;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

import static contacts.Main.scn;

public class Person extends Contact{

    private String name;
    private String surname;
    private String dateBirth;
    private String gender;


    public Person(String name, String surname, String dateBirth, String gender, String phone) {
    this.name = name;
    this.surname = surname;
    this.dateBirth = dateBirth;
    this.gender = gender;
    super.number = phone;
    setLocalDateTime();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(String dateBirth) {
        if (validateDateBirth(dateBirth)) {
            this.dateBirth = dateBirth;
        } else {
            System.out.println("Bad birth date!");
            this.dateBirth  = "[no data]";
        }

    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        if (validateGender(gender)) {
            this.gender = gender;
        } else {
            System.out.println("Bad gender!");
            this.gender = "[no data]";
        }

    }

    public String getNumber() {
        return super.number;
    }

    @Override
    public void showInfo() {

        System.out.println("Name: " + this.getName());
        System.out.println("Surname: " + this.getSurname());
        System.out.println("Birth date: " + this.getDateBirth());
        System.out.println("Gender: " + this.getGender());
        System.out.println("Number: " + this.getNumber());
        System.out.println("Time created: " + this.getLocalDateTimeCreated());
        System.out.println("Time last edit: " + this.getLocalDateTimeEdited());
        System.out.println();
    }

    @Override
    public void showName() {
        System.out.println(this.name);
    }

    @Override
    public void editInfo() {
        System.out.println("Select a field (name, surname, birth, gender, number):");
        String field = scn.nextLine();
        switch (field) {
            case "name":
                System.out.println("Enter name:");
                String name = scn.nextLine();
                this.setName(name);
                localDateTimeEdited = LocalDateTime.now().withNano(0);
                break;
            case "surname":
                System.out.println("Enter surname:");
                String surname = scn.nextLine();
                this.setSurname(surname);
                localDateTimeEdited = LocalDateTime.now().withNano(0);
                break;
            case "birth":
                System.out.println("Enter birth date::");
                String birth = scn.nextLine();
                this.setDateBirth(birth);
                localDateTimeEdited = LocalDateTime.now().withNano(0);
                break;
            case "gender":
                System.out.println("Enter gender:");
                String gender = scn.nextLine();
                this.setGender(gender);
                localDateTimeEdited = LocalDateTime.now().withNano(0);
                break;
            case "number":
                System.out.println("Enter number:");
                String number = scn.nextLine();
                this.setNumber(number);
                localDateTimeEdited = LocalDateTime.now().withNano(0);
                break;
        }
    }


    @Override
    public String toString() {
        return name + " " + surname + number;
    }

    public static boolean validateDateBirth(String dateBirth) {
        Pattern pattern = Pattern.compile("\\d\\d\\.\\d\\d\\.\\d\\d\\d\\d");

        return pattern.matcher(dateBirth).matches();

    }

    public static boolean validateGender(String gender) {
        return gender.equals("M") || gender.equals("F");
    }

   static String validateNumber(String number) {
        if (number.isEmpty()) {
            return "[no number]";
        }

        if (validatePhoneNumber(number)) {
            return number;
        } else {
            System.out.println("Wrong number format!");
            return "[no number]";
        }

    }

}

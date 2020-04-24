package contacts;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    static List<Contact> contactList = new ArrayList<>();
    static Scanner scn = new Scanner(System.in);
    static String fileName = "Contacts/task/phonebook.db";

    public static void main(String[] args) {


        if (!(args.length == 0)) {
            loadFile(args[1]);
        } else {
            try {
                FileWriter fw = new FileWriter(new File(fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        menu();
    }

    private static void loadFile(String fileName) {

        File file = new File(fileName);
        if (file.exists()) {
            System.out.println("open phonebook.db" + "\n");
            try {
                contactList = (List<Contact>) deserialize(fileName);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            try {
                FileWriter fw = new FileWriter(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private static void menu() {

        boolean userWork = true;
        while (userWork) {
            System.out.println("[menu] Enter action (add, list, search, count, exit):");
            String input = scn.nextLine();

            switch (input) {

                case "add":
                    addContact();
                    break;
                case "list":
                    list();
                    break;
                case "search":
                    searchContact();
                    break;
                case "count":
                    System.out.println("The Phone Book has " + contactList.size() + " records.");
                    System.out.println();
                    break;
                case "exit":
                    userWork = false;
                    break;

            }
        }

    }

    private static void searchContact() {
        System.out.println("Enter search query:");
        Pattern pattern = Pattern.compile(scn.nextLine(), Pattern.CASE_INSENSITIVE);
        List<Integer> found = new ArrayList<>();
        for (int i = 0; i < contactList.size(); i++) {
            Matcher matcher = pattern.matcher(contactList.get(i).toString());
            while (matcher.find()) {
                found.add(i);
            }
        }
        if (found.size() > 0) {
            System.out.println("Found " + found.size() + " results:");
            int count = 0;
            for (Integer i : found) {
                System.out.print(++count + ". ");
                contactList.get(i).showName();
            }
            System.out.println();
        } else {
            System.out.println("Found 0 results." + "\n");
        }


        boolean menu = true;

        while (menu) {
            System.out.println("[search] Enter action ([number], back, again):");
            String input = scn.nextLine();

            switch (input) {

                case "back":
                    return;
                case "again":
                    menu = false;
                    searchContact();
                    break;
                default:
                    int selectedContact = Integer.parseInt(input) - 1;
                    boolean inRecord = true;
                    contactList.get(found.get(selectedContact)).showInfo();
                    showInRecord(found.get(selectedContact), inRecord);
                    menu = false;
            }
        }

    }



    private static void list() {
        int counterList = 1;
        int counter = 0;
        for (Contact c : contactList) {
            System.out.println(counterList + "." + " " + contactList.get(counter));
            counterList++;
            counter++;
        }
        System.out.println();
        System.out.println("[list] Enter action ([number], back):");
        String input = scn.nextLine();
        if (input.equals("back")) {
            System.out.println();
            return;
        } else {
            int selectedContact = Integer.parseInt(input) - 1;
            info(selectedContact);
            boolean inRecord = true;
            showInRecord(selectedContact, inRecord);

        }

    }

    private static void showInRecord(int selectedContact, boolean inRecord) {
        String input;
        while (inRecord) {
            System.out.println("[record] Enter action (edit, delete, menu):");
            input = scn.nextLine();
            switch (input) {
                case "edit":
                    editContact(selectedContact);
                    break;
                case "delete":
                    removeContact(selectedContact);
                    inRecord = false;
                    break;
                case "menu":
                    inRecord = false;
                    System.out.println();
                   // menu();
                    break;
            }
        }
    }

    private static void info (int selectedContact) {
        Contact contact = contactList.get(selectedContact);
        contact.showInfo();
    }

    private static void editContact(int selectedContact) {
/*        if (contactList.isEmpty()) {
            System.out.println("No records to edit!");
            return;
        }
        list();
        System.out.println("Select a record:");
       int record = Integer.parseInt(scn.nextLine())-1;*/
        Contact contact = contactList.get(selectedContact);
        contact.editInfo();
       try {
            serialize(contactList, fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Saved");
        info(selectedContact);
    }

    private static void removeContact(int selectedContact) {
  /*      if (contactList.isEmpty()) {
                System.out.println("No records to remove!");
                return;
        }
        list();
        System.out.println("Select a record:");*/
        /*  int record = Integer.parseInt(scn.nextLine());*/
        contactList.remove(selectedContact);

        try {
            serialize(contactList, fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Saved");
        System.out.println();
    }

    private static void addContact() {
        System.out.println("Enter the type (person, organization):");
        String type = scn.nextLine();
        ContactEmitter emitter = new ContactEmitter();
        Contact contact = emitter.createContact(type);
        contactList.add(contact);
        System.out.println("The record added.");
        System.out.println();
        try {
            serialize(contactList, fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Serialize the given object to the file
     */
    public static void serialize(Object obj, String fileName) throws IOException, FileNotFoundException {
        FileOutputStream fos = new FileOutputStream(fileName);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(obj);
        oos.close();
    }

    /**
     * Deserialize to an object from the file
     */
    public static Object deserialize(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(fileName);
        BufferedInputStream bis = new BufferedInputStream(fis);
        ObjectInputStream ois = new ObjectInputStream(bis);
        Object obj = ois.readObject();
        ois.close();
        return null;
    }

}

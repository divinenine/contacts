package contacts;

import java.time.LocalDateTime;

import static contacts.Main.scn;

public class Organization extends Contact {


    private String orgName;
    private String address;


    public Organization(String orgName, String address, String number) {

        this.orgName = orgName;
        this.address = address;
        super.number = number;
        setLocalDateTime();

    }
    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return orgName + address + number;
    }


    @Override
    public String getNumber() {
        return super.number;
    }

    @Override
    public void showInfo() {
        System.out.println("Organization name: " + this.getOrgName());
        System.out.println("Address: " + this.getAddress());
        System.out.println("Number: " + this.getNumber());
        System.out.println("Time created: " + this.getLocalDateTimeCreated());
        System.out.println("Time last edit: " + this.getLocalDateTimeEdited());
        System.out.println();
    }

    @Override
    public void showName() {
        System.out.println(this.orgName);
    }

    @Override
    public void editInfo() {
        System.out.println("Select a field (name, address, number):");
        String field = scn.nextLine();
        switch (field) {
            case "name":
                System.out.println("Enter name:");
                String name = scn.nextLine();
                this.setOrgName(name);
                localDateTimeEdited = LocalDateTime.now().withNano(0);
                break;
            case "address":
                System.out.println("Enter address:");
                String gender = scn.nextLine();
                this.setAddress(gender);
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
}

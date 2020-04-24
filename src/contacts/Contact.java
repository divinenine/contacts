package contacts;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.regex.Pattern;

public abstract class Contact implements Serializable {

    private static final long serialVersionUID = 2L;

    String number;
    LocalDateTime localDateTimeCreated;
    LocalDateTime localDateTimeEdited;

    public void setLocalDateTime() {
        localDateTimeCreated = LocalDateTime.now().withNano(0);
        localDateTimeEdited = LocalDateTime.now().withNano(0);
    }

    public LocalDateTime getLocalDateTimeCreated() {
        return localDateTimeCreated;
    }
    public LocalDateTime getLocalDateTimeEdited() {
        return localDateTimeEdited;
    }



    public void setNumber(String number) {
        if (validatePhoneNumber(number)) {
            this.number = number;
        } else {
            System.out.println("Wrong number format!");
            this.number = "[no number]";
        }

    }

    static boolean validatePhoneNumber(String number) {

        Pattern pattern = Pattern.compile("^\\+?([\\da-zA-Z]{1,}[\\s-]?)?(\\([\\da-zA-Z]{2,}(\\)[\\s-]|\\)$))?([\\da-zA-Z]{2,}[\\s-]?)*([\\da-zA-Z]{2,})?$");

        return pattern.matcher(number).matches();

    }


    public abstract String getNumber();

    public abstract void showInfo();

    public abstract void showName();

    public abstract void editInfo();
}


package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    public boolean checkPhone(String phone) {
        String regex = "\\d{10}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }

    public boolean checkPassword(String pass) {
        String regex = "(?=.*[@,#,$,%])(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{6,}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(pass);
        return matcher.matches();
    }

}

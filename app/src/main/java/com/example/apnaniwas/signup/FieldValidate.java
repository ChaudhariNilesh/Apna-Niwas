package com.example.apnaniwas.signup;

import android.util.Patterns;
import android.widget.EditText;

import java.util.regex.Pattern;

public class FieldValidate
{
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +         //at least 1 digit
                    "(?=.*[a-z])" +         //at least 1 lower case letter
                    "(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{8,}" +               //at least 8 characters
                    "$");

    static boolean validateEmail(String emailInput){
         if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) return false;
         else return true;
    }

    static boolean validateUsername(String  UsrNameInput) {
        if (UsrNameInput.length() > 20) return false;
        else  return true;
    }

    static boolean validatePassword(String UsrPswdInput) {

        if (!PASSWORD_PATTERN.matcher(UsrPswdInput).matches())return false;
         else return true;
    }

    static boolean validateMobile(String UsrMobInput) {
       if (!Patterns.PHONE.matcher(UsrMobInput).matches()) return  false;
       else return true;

    }
}

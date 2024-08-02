package com.lcaohoanq.utils;

public class ValidateUtils {
    public static boolean checkTypeAccount(String email_phone) {
        return email_phone.contains("@");
    }
}

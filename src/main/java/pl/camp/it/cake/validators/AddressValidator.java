package pl.camp.it.cake.validators;

import pl.camp.it.cake.exceptions.ValidationException;


public class AddressValidator {
    public static void validateAddress(String address) {
        if (!address.matches("[A-Z][a-z]+\\ [1-9]([0-9]{0,3})?([A-Za-z]{1})?(\\/[1-9][0-9]{0,2})?")) {
            throw new ValidationException("Address incorrect");
        }
    }

    public static void validatePostalCode(String postalCode) {
        if (!postalCode.matches("[0-9]{2}-[0-9]{3}")) {
            throw new ValidationException("Postal code incorrect");
        }
    }

    public static void validateCity(String city) {
        if (!city.matches("[A-ZĄŻŹĘĆŃÓŁ][a-zóążćśńłę]+(-[A-ZĄŻŹĘĆŃÓŁ][a-zóążćśńłę]+)?(\\ [A-ZĄŻŹĘĆŃÓŁ][a-zóążćśńłę]+)?")) {
            throw new ValidationException("City incorrect");
        }
    }

    public static void validatePhone(String phone) {
        if (!phone.matches("[1-9][0-9]{8}")) {
            throw new ValidationException("Phone incorrect");
        }
    }
}
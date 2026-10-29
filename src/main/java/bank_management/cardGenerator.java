package bank_management;

import java.util.Random;

public class cardGenerator {
    private static final String BANK_BIN = "507890";

    public static String generateCardNumber() {
        Random rand = new Random();
        StringBuilder card = new StringBuilder(BANK_BIN);
        for (int i =0 ; i<10; i++){
            int digit = rand.nextInt(10);
            card.append(digit);
        }
        int checkDigit = calculateLuhnCheckDigit(card.toString());
        card.append(checkDigit);

        return card.toString();
    }

    private static int calculateLuhnCheckDigit(String partialNumber) {
        int sum = 0;
        boolean alternate = true;

        for (int i = partialNumber.length() - 1; i >= 0; i--) {
            int digit = Character.getNumericValue(partialNumber.charAt(i));

            if (alternate) {
                digit *= 2;
                if (digit > 9) digit -= 9;
            }

            sum += digit;
            alternate = !alternate;
        }

        return (10 - (sum % 10)) % 10;
    }
}


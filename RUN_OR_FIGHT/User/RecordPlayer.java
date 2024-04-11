package User;

import java.util.Random;

public class RecordPlayer {

    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int LENGTH = 1;
    private static Random random = new Random();

    public static String generateRecord() {
        StringBuilder registro = new StringBuilder();


        for (int i = 0; i < LENGTH; i++) {
            registro.append(ALPHABET.charAt(random.nextInt(ALPHABET.length())));
        }


        for (int i = 0; i < 2; i++) {
            registro.append(random.nextInt(10));
        }


        for (int i = 0; i < LENGTH + 1; i++) {
            registro.append(ALPHABET.charAt(random.nextInt(ALPHABET.length())));
        }

        return registro.toString();
    }
}

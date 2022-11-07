package helpers.utils;

import java.util.Random;

public class CustomUtilities {

    /**
     * Генерирует рандомную буквенно-числовую строку
     *
     * @param lengthRow длина строки
     * @return сгенерированная строка
     * @author A.Shilov
     */
    public static String randomizer(int lengthRow) {
        Random random = new Random();
        String alphanumeric = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder chars = new StringBuilder();
        while (chars.length() < lengthRow) {
            int index = (int) (random.nextFloat() * alphanumeric.length());
            chars.append(alphanumeric.charAt(index));
        }
        return chars.toString();
    }
}

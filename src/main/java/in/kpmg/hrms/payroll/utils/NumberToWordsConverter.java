package in.kpmg.hrms.payroll.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class NumberToWordsConverter {
    public static String convertToIndianCurrency(String num) {

        BigDecimal bd = new BigDecimal(num);
        long number = bd.longValue();
        long no = bd.longValue();
        int decimal = (int) (bd.remainder(BigDecimal.ONE).doubleValue() * 100);
        int digits_length = String.valueOf(no).length();
        int i = 0;
        String finalResult = "";

        ArrayList<String> str = new ArrayList<>();
        HashMap<Integer, String> words = getIntegerStringHashMap();

        String[] digits = {"", "Hundred", "Thousand", "Lakh", "Crore", "Arab", "Kharab", "Nil", "Padma", "Shankh"};

        while (i < digits_length) {
            int divider = (i == 2) ? 10 : 100;
            number = no % divider;
            no = no / divider;
            i += divider == 10 ? 1 : 2;
            if (number > 0) {
                int counter = str.size();
                String plural = (counter > 0 && number > 9) ? "s" : "";
                String tmp = (number < 21) ? words.get((int) number) + " " +
                        digits[counter] + plural :
                        words.get((int) Math.floor((double) number / 10) * 10)
                                + " " + words.get((int) (number % 10))
                                + " " + digits[counter] + plural;
                str.add(tmp);
            } else {
                str.add("");
            }
        }

        Collections.reverse(str);
        String Rupees = null;

        Rupees = String.join(" ", str).trim();


        String paise = (decimal) > 0 ? " And " + words.get(
                (int) (decimal - decimal % 10)) + " " +
                words.get((int) (decimal % 10)) : "";
        // AND FORTNY NINE PAISA
        if (!paise.isEmpty()) {

            paise = paise.concat(" Paise");
        }

        finalResult = "Rupees " + Rupees + paise + " Only";
        return finalResult.replace("  ", " ")
                .replace("   ", " ");
    }

    private static HashMap<Integer, String> getIntegerStringHashMap() {
        HashMap<Integer, String> words = new HashMap<>();
        words.put(0, "");
        words.put(1, "One");
        words.put(2, "Two");
        words.put(3, "Three");
        words.put(4, "Four");
        words.put(5, "Five");
        words.put(6, "Six");
        words.put(7, "Seven");
        words.put(8, "Eight");
        words.put(9, "Nine");
        words.put(10, "Ten");
        words.put(11, "Eleven");
        words.put(12, "Twelve");
        words.put(13, "Thirteen");
        words.put(14, "Fourteen");
        words.put(15, "Fifteen");
        words.put(16, "Sixteen");
        words.put(17, "Seventeen");
        words.put(18, "Eighteen");
        words.put(19, "Nineteen");
        words.put(20, "Twenty");
        words.put(30, "Thirty");
        words.put(40, "Forty");
        words.put(50, "Fifty");
        words.put(60, "Sixty");
        words.put(70, "Seventy");
        words.put(80, "Eighty");
        words.put(90, "Ninety");
        return words;
    }


}
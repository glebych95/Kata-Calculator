import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

public class Calculator {

    private static final String OOPS = "Упс!.. Похоже, что ты ввёл неправильный знак. Разрешённые знаки: +, -, *, /.";
    private static final ArrayList <String> ALLOWED_ARABIC_NUMBERS = new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10"));
    private static final ArrayList <String> ALLOWED_ROMAN_NUMBERS = new ArrayList<>(Arrays.asList("I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"));
    private static final ArrayList <String> ROMAN_RESULTS = new ArrayList<>(Arrays.asList(
            "I", "II", "III", "IV", "V",
            "VI", "VII", "VIII", "IX", "X",
            "XI", "XII", "XIII", "XIV", "XV",
            "XVI", "XVII", "XVIII", "XIX", "XX",
            "XXI", "XXII", "XXIII", "XXIV", "XXV",
            "XXVI", "XXVII", "XXVIII", "XXIX", "XXX",
            "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV",
            "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
            "XLI", "XLII", "XLIII", "XLIV", "XLV",
            "XLVI", "XLVII", "XLVIII", "XLIX", "L",
            "LI", "LII", "LIII", "LIV", "LV",
            "LVI", "LVII", "LVIII", "LIX", "LX",
            "LXI", "LXII", "LXIII", "LXIV", "LXV",
            "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
            "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV",
            "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
            "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV",
            "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
            "XCI", "XCII", "XCIII", "XCIV", "XCV",
            "XCVI", "XCVII", "XCVIII", "XCIX", "C"));

    public static void main (String[] args) {
        while (true) {
            System.out.println("Введи арифметическое выражение из 2-х целых чисел от 1 до 10 (I-X) включительно и знака математической операции (+, -, *, /) через пробел (например, 7 + 5):");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
                if (input.equalsIgnoreCase("СТОП")) {
                    break;
                }
            System.out.println("Результат: "+ calc(input)+". Для завершения работы программы введи слово СТОП");
        }
    }
    public static String calc (String input) {
        String[] checkInput = input.trim().split(" ");
        if (checkInput.length != 3) {
            throw new RuntimeException("Строка не является математическим выражением или ты ввёл слишком длинное выражение. Калькулятор работает только с 2-мя операндами и 1-м оператором (например, X * II).");
        }
        boolean checkArabicOperands = ALLOWED_ARABIC_NUMBERS.contains(checkInput[0]) && ALLOWED_ARABIC_NUMBERS.contains(checkInput[2]);
        boolean checkRomanOperands = ALLOWED_ROMAN_NUMBERS.contains(checkInput[0]) && ALLOWED_ROMAN_NUMBERS.contains(checkInput[2]);
        boolean arabicAndRoman = ALLOWED_ROMAN_NUMBERS.contains(checkInput[0]) && ALLOWED_ARABIC_NUMBERS.contains(checkInput[2]);
        boolean romanAndArabic = ALLOWED_ARABIC_NUMBERS.contains(checkInput[0]) && ALLOWED_ROMAN_NUMBERS.contains(checkInput[2]);
        if (checkArabicOperands) {
            return arabicCalc(checkInput);
        }
        else if (checkRomanOperands) {
            return romanCalc(checkInput);
        }
        else if (arabicAndRoman || romanAndArabic) {
            throw new RuntimeException("Калькулятор не производит операции с разными системами счисления (VII / 2).");
        }
        else {
            throw new RuntimeException("Что-то пошло не так... Введи 2 целых числа от 1 до 10 (I-X) и знак математической операции, используя пробелы (например, 8 - 6).");
        }

    }
    static String arabicCalc (String [] arabicExpression) {
        int firstOperand = Integer.parseInt(arabicExpression[0]);
        char sign = arabicExpression[1].charAt(0);
        int secondOperand = Integer.parseInt(arabicExpression[2]);
        int result;
        switch (sign) {
            case '+' -> {
                result = firstOperand + secondOperand;
                return Integer.toString(result);
            }
            case '-' -> {
                result = firstOperand - secondOperand;
                return Integer.toString(result);
            }
            case '*' -> {
                result = firstOperand * secondOperand;
                return Integer.toString(result);
            }
            case '/' -> {
                result = firstOperand / secondOperand;
                return Integer.toString(result);
            }
            default ->
                    throw new RuntimeException(OOPS);
        }
    }
    static String romanCalc (String [] romanExpression) {
        int firstOperand = ALLOWED_ROMAN_NUMBERS.indexOf(romanExpression[0]) + 1;
        char sign = romanExpression[1].charAt(0);
        int secondOperand = ALLOWED_ROMAN_NUMBERS.indexOf(romanExpression[2]) + 1;
        int result;
        switch (sign) {
            case '+' -> {
                result = firstOperand + secondOperand;
                    return ROMAN_RESULTS.get(result-1);
            }
            case '-' -> {
                try {
                    result = firstOperand - secondOperand;
                    return ROMAN_RESULTS.get(result-1);
                }
                catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                    throw new RuntimeException("В римской системе не может быть чисел меньших или равных 0.");
                }
            }
            case '*' -> {
                result = firstOperand * secondOperand;
                return ROMAN_RESULTS.get(result-1);
            }
            case '/' -> {
                result = firstOperand / secondOperand;
                return ROMAN_RESULTS.get(result-1);
            }
            default ->
                    throw new RuntimeException(OOPS);
        }
    }
}
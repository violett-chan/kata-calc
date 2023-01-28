import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static final String[] rimNumbersFrom1to10 = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
    public static final String[] rimNumbersFrom10to100 = {"X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC", "C"};
    public static final String[] operators = {"+", "-", "*", "/"};

    public static void main(String[] args) throws IOException {
        String input = System.console().readLine();
        System.out.println(calc(input));
    }

    public static String calc(String input) throws IOException {
        String[] args = input.split(" ");        
        if (args.length < 3) {
            System.out.println("строка не является математической операцией");
            throw new IOException();
        }
        if (args.length > 3) {
            System.out.println("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
            throw new IOException();
        }

        int a = Arrays.asList(rimNumbersFrom1to10).indexOf(args[0]);
        int op = Arrays.asList(operators).indexOf(args[1]);
        int b = Arrays.asList(rimNumbersFrom1to10).indexOf(args[2]);

        boolean isRim = false;

        int result;
        String output = "";

        if (a != -1 && b != -1) {
            a++;
            b++;
            isRim = true;
        } else if (a != -1 || b != -1) {
            System.out.println("используются одновременно разные системы счисления или римское число больше 10");
            throw new IOException();
        } else {
            try {
                a = Integer.parseInt(args[0]);
                b = Integer.parseInt(args[2]);
            } catch (Exception e) {
                System.out.println("калькулятор умеет работать только с целыми числами");
                throw new IOException();
            }
            if (a < 1 || a > 10 || b < 1 || b > 10) {
                System.out.println("калькулятор умеет работать только с числами от 1 до 10 включительно");
                throw new IOException();
            }

        }
        switch (op) {
            case 0 -> result = a + b;
            case 1 -> result = a - b;
            case 2 -> result = a * b;
            case 3 -> result = a / b;
            default -> {
                System.out.println("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
                throw new IOException();
            }
        }
        output = String.valueOf(result);
        if (isRim) {
            if (result < 1) {
                System.out.println("в римской системе нет отрицательных чисел и нуля");
                throw new IOException();
            } else {
                if (result > 10) {
                    output = rimNumbersFrom10to100[result / 10 - 1];
                    if (result % 10 != 0) output += rimNumbersFrom1to10[result % 10 - 1];
                } else {
                    output = rimNumbersFrom1to10[result - 1];
                }
            }
        }
        return output;
    }
}

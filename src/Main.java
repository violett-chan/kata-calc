import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static final String[] rimNumbersFrom1to10 = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
    public static final String[] rimNumbersFrom10to100 = {"X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC", "C"};
    public static final String[] operators = {"+", "-", "*", "/"};

    public static void main(String[] args) throws IOException {
        if (args.length < 3) {
            //ладно...
            System.out.println("строка не является математической операцией");
            throw new IOException();
        }
        if (args.length > 3) {
            //должно быть только 3 аргумента!
            System.out.println("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
            throw new IOException();
        }
        System.out.println(calc(args[0] + " " + args[1] + " " + args[2])); //чел ты...
    }

    public static String calc(String input) throws IOException {
        //в условии написано принимать строку хз зачем типа проверить шаришь за сплит или вилкой в глаз
        String[] args = input.split(" ");

        int a = Arrays.asList(rimNumbersFrom1to10).indexOf(args[0]);
        int op = Arrays.asList(operators).indexOf(args[1]);
        int b = Arrays.asList(rimNumbersFrom1to10).indexOf(args[2]);

        boolean isRim = false;

        int result;
        String output = "";

        if (a != -1 && b != -1) {
            //это точно римские целые числа от 1 до 10
            a++;
            b++;
            isRim = true;
        } else if (a != -1 || b != -1) {
            //арабские с римскими не складываем так в тз написано!
            System.out.println("используются одновременно разные системы счисления или римское число больше 10");
            throw new IOException();
        } else {
            //предполагаем что это арабские цифры, но нужно многое проверить
            try {
                a = Integer.parseInt(args[0]);
                b = Integer.parseInt(args[2]);
            } catch (Exception e) {
                //с числами что-то не так!
                System.out.println("калькулятор умеет работать только с целыми числами");
                throw new IOException();
            }
            //это нормальные числа
            if (a < 1 || a > 10 || b < 1 || b > 10) {
                //в тз написано только от 1 до 10 включительно!
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
                //неверный оператор
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
                    output = String.valueOf(rimNumbersFrom1to10[result - 1]);
                }
            }
        }
        return output;
    }
}

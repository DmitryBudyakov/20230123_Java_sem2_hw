import java.util.Scanner;

/**
 * PowerOfNumber
 */
public class PowerOfNumber {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // заголовок
        StringBuilder sb = new StringBuilder();
        String header = "Возведение числа а в степень b";
        for (int i = 0; i < header.length(); i++) {
            sb.append("-");
        }
        System.out.println(header + "\n" + sb);

        // получение чисел из терминала от пользователя
        int a = getInt("Введите целое число a: ");
        int b = getInt("Введите целое число b: ");

        Object result = countPowerOfNumber(a, b);

        // обработка результата
        String str = String.format("%d ^ %d ", a, b);   // a в степени b (a ^ b)
        if (result instanceof String) {
            System.out.printf("%s - %s", str, result);
        }
        if (result instanceof Integer) {
            System.out.printf("%s = %d", str, result);
        }
        if (result instanceof Double) {
            System.out.printf("%s = %.2f", str, result);
        }
        
    }

    /**
     * Получение целого числа из терминала
     * @param prompt
     * @return
     */
    public static int getInt(String prompt) {
        while (true) {
            System.out.printf(prompt);
            while(!sc.hasNextInt()){
                System.out.printf(prompt);
                sc.next();
            }
            return sc.nextInt();
        }
    }

    /**
     * Возведение в степень целого числа (a, b ∈ Z)
     * @param numA
     * @param numB
     * @return
     */
    public static Object countPowerOfNumber(int numA, int numB) {
        if (numA == 0 && numB == 0) return "не определено"; // 0 ^ 0
        if (numB == 0) return 1;    // a ^ 0
        if (numB > 0) {             // a ^ b при b > 0
            Integer res = 1;
            for (int i = 1; i <= numB; i++) {
                res *= numA;
            }
            return res;
        } else {                    // a ^ b при b < 0
            Double res = 1.0;
            for (int i = 1; i <= -numB; i++) {
                res *= numA;
            }
            return (1 / res);            
        }
    }
}
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
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
        // int a = getIntFromTerm("Введите целое число a: ");
        // int b = getIntFromTerm("Введите целое число b: ");

        // получение чисел из файла
        File inputFile = new File("input.txt");
        ArrayList<String> dataList = getDataFromFile(inputFile);

        System.out.printf("Данные из файла %s:\n", inputFile);
        int[] data = getValuesForPowerCalc(dataList);
        int a = data[0];
        int b = data[1];

        System.out.printf("Число a = %d\n", a);
        System.out.printf("Число b = %d\n", b);

        Object result = countPowerOfNumber(a, b);

        // обработка результата для вывода в терминал
        // System.out.println();
        System.out.println("\nРезультат вычисления:");
        String str = String.format("%d ^ %d ", a, b);   // a в степени b (a ^ b)
        if (result instanceof String) {
            System.out.printf("%s - %s\n", str, result);
        }
        if (result instanceof Integer) {
            System.out.printf("%s = %d\n", str, result);
        }
        if (result instanceof Double) {
            System.out.printf("%s = %.2f\n", str, result);
        }

        // запись результата в файл
        File outpuFile = new File("output.txt");
        saveToFile(outpuFile, result);
        
    }

    /**
     * Получение целого числа из терминала
     * @param prompt
     * @return
     */
    public static int getIntFromTerm(String prompt) {
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
     * Извлечение данных из текстового файла в список
     * @param filename
     * @return
     */
    public static ArrayList<String> getDataFromFile(File filename) {
        ArrayList<String> list = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(filename))) {
            String line;
            int i = 0;
            while ((line = in.readLine()) != null ) {
                list.add(line);
                i++;
            }
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return list;
    }

    /**
     * Извлечение из ArrayList значений a и b
     * @param arrayList
     * @return
     */
    public static int[] getValuesForPowerCalc(ArrayList<String> arrayList) {
        int[] array = new int[2];
        for (int i = 0; i < arrayList.size(); i++) {
            String[] line = arrayList.get(i).split(" ");
            if (line[0].equals("a")) array[0] = Integer.parseInt(line[1]);
            if (line[0].equals("b")) array[1] = Integer.parseInt(line[1]);
        }
        return array;
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

    /**
     * Запись результата вычисления в файл
     * @param filename
     * @param dataToSave
     */
    public static void saveToFile(File filename, Object dataToSave) {
        try (FileWriter out = new FileWriter(filename)) {
            out.write(dataToSave.toString());
            System.out.printf("Результат записан в файл %s\n", filename);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
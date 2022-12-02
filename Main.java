import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        /*
        Создание и запись массивов
         */

        int[] array1 = toRecord();
        int[] array2 = toRecord();
        int[] array3 = toRecord();

        /*
        Создание и запуск потоков
        в качестве параметров передаются заполненные массивы
         */
        Thread thread1 = new Thread(new SaveAsThread(array1, 1));
        Thread thread2 = new Thread(new SaveAsThread(array2, 2));
        Thread thread3 = new Thread(new SaveAsThread(array3, 3));

        thread1.start();
        thread2.start();
        thread3.start();
    }

    public static int[] toRecord(){
        int[] array;
        System.out.print("Введите длину массива: ");
        array = new int[in.nextInt()];

        for(int i = 0; i < array.length; i++){
            System.out.printf("Введите элемент массива под номером %s: ", i);
            array[i] = in.nextInt();
        }

        return array;
    }
}

class SaveAsThread implements Runnable{
    int[] array;
    int id;

    SaveAsThread(int[] array, int id){
        this.array = array;
        this.id = id;
    }
    @Override
    public void run(){
        String text = Arrays.toString(array); // Массив, переданный в качестве параметров, переделывается в строчку

        /*
        Запись массива в файл.
        При создании поток в параметрах передаётся файл и параметр "true".
        Без этого параметра файл будет перезаписываться в каждом потоке.
         */
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("file.txt", true))){
            bw.write("№" + id + " " + text + "\t");
            bw.flush();
        }
        catch (Exception e){
            System.out.print(e.getMessage());
        }
    }
}

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        int[] array1 = toRecord();
        int[] array2 = toRecord();
        int[] array3 = toRecord();

        Thread thread1 = new Thread(new SaveAsThread(array1));
        Thread thread2 = new Thread(new SaveAsThread(array2));
        Thread thread3 = new Thread(new SaveAsThread(array3));

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

        SaveAsThread(int[] array){
            this.array = array;
        }
        @Override
        public void run(){
            String text = Arrays.toString(array);

            try(BufferedWriter bw = new BufferedWriter(new FileWriter("file.txt", true))){
                bw.write(text + "\t");
                bw.flush();
            }
            catch (Exception e){
                System.out.print(e.getMessage());
            }
        }
    }
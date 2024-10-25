import java.io.*;
import java.util.Random; 
import java.util.Scanner; 

public class App 
{

    public static int[] createRandomArray(int arrayLength) 
    {
        Random random = new Random(); 
        int[] array = new int[arrayLength]; 

        for (int i = 0; i < arrayLength; i++) 
        {
            array[i] = random.nextInt(101); 
        }
        return array; 
    }

    public static void writeArrayToFile(int[] array, String filename) 
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) 
        {
            for (int num : array) {
                writer.write(num + "\n"); 
            }
            System.out.println("Array written to file: " + filename);
        } catch (IOException e) 
        {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public static int[] readFileToArray(String filename) 
    {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) 
        {
            return reader.lines().mapToInt(Integer::parseInt).toArray(); 
        } catch (IOException e) 
        {
            System.out.println("Error reading from file: " + e.getMessage());
            return new int[0]; 
        }
    }

    public static void mergeSort(int[] array) 
    {
        if (array.length < 2) 
        {
            return; // Base case
        }
        int mid = array.length / 2;

        int[] left = new int[mid];
        int[] right = new int[array.length - mid];

        System.arraycopy(array, 0, left, 0, mid);
        System.arraycopy(array, mid, right, 0, array.length - mid);

        mergeSort(left);
        mergeSort(right);
        merge(array, left, right);
    }

    private static void merge(int[] array, int[] left, int[] right) 
    {
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) 
        {
            if (left[i] <= right[j]) 
            {
                array[k++] = left[i++];
            } else 
            {
                array[k++] = right[j++];
            }
        }

        while (i < left.length) 
        {
            array[k++] = left[i++];
        }
        while (j < right.length) 
        {
            array[k++] = right[j++];
        }
    }

    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in); 

        System.out.print("Enter the length of the array: ");
        int length = scanner.nextInt(); 

        int[] randomArray = createRandomArray(length);

        String filename = "array.txt";

        writeArrayToFile(randomArray, filename);

        int[] readArray = readFileToArray(filename);
        System.out.println("Array read from file:");

        for (int num : readArray) 
        {
            System.out.print(num + " "); 
        }
        System.out.println(); 

        mergeSort(readArray);

        System.out.println("Sorted array:");

        for (int num : readArray) 
        {
            System.out.print(num + " "); 
        }
        System.out.println(); 

        scanner.close();
    }
}
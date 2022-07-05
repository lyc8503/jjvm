package lab3;

public class BubbleSort {

    public static void main(String[] args) {

        int[] arr = new int[] {74, 28, 98, 54, 78, 69, 2, 32, 20, 42, 91, 76, 9, 35, 39, 70, 68, 89, 19, 16, 30, 24, 33, 26, 84, 90, 10, 56, 27, 85, 4, 99, 22, 25, 1, 41, 72, 5, 64, 45, 49, 11, 83, 46, 95, 82, 57, 29, 34, 55, 59, 79, 14, 96, 38, 53, 62, 67, 12, 86, 40, 71, 75, 88, 37, 7, 73, 61, 43, 97, 17, 21, 36, 23, 66, 13, 44, 50, 31, 18, 92, 3, 6, 80, 87, 60, 93, 47, 63, 65, 81, 58, 77, 51, 94, 8, 52, 48, 15};

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }

        for (int i = 0; i < arr.length; i++) {
            IOUtil.writeInt(arr[i]);
        }

    }
}

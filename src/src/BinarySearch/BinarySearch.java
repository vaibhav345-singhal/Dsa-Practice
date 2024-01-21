package BinarySearch;

public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        int target = 5;
        System.out.println(binarySearch(arr, arr.length, target));

    }

    public static int binarySearch(int[] arr, int n, int target) {
        int s = 0;
        int e = n - 1;
        while (s <= e) {
            int mid = s + (e - s) / 2;

            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                s = mid + 1;
            } else {
                e = mid - 1;
            }
        }
        return -1;
    }
}

public class SortingMain {

    public static void main(String[] args) {
        
        int[] unsorted = {3,5,1,6,7,8,2,10,24,13,15};
        //insertionSort(unsorted);
        //selectionSort(unsorted);
        //mergeSort(unsorted, 0, unsorted.length-1);
        mergeSortWithSubArrays(unsorted);
        printArr(unsorted);

    }

    public static void insertionSort(int[] arr) {

        int len = arr.length;

        for (int i=1; i<len; i++) {

            int temp = arr[i];
            int j = i - 1;

            while ((j >= 0) && temp < arr[j]) {
                arr[j+1] = arr[j];
                j  -= 1;
            }
            
            arr[j+1] = temp;

        }
        
    }

    public static void selectionSort(int[] arr) {

        int len = arr.length;

        for (int i = 0; i < len - 1; i++) {

            // find the smallest value in the array
            int smallest = i;
            for (int j = i; j < len; j++) {
                if (arr[j] < arr[smallest]) smallest = j;
            }

            // swap values
            int temp = arr[smallest];
            arr[smallest] = arr[i];
            arr[i] = temp;

        }

    }

    /*
     * DIFFERENCE BETWEEN QUICKSORT AND MERGESORT:
     * Quicksort does all the heavy lifting during the "Divide" phase
     * Mergesorts 'divide' phase is trivial and the actual conqouring happens during the merge
     */

    public static void quickSort(int[] arr, int n) {

        if (n > 1) {

            int[] smaller = new int[n];
            int[] equal = new int[n];
            int[] larger = new int[n];

            int ns, ne, nl;
            ns = ne = nl = 0;

            int pivot = arr[0];

            for (int i = 0; i<n; i++) {
                if (arr[i] < pivot) smaller[ns++] = arr[i];
                else if (arr[i] == pivot) equal[ne++] = arr[i];
                else larger[nl++] = arr[i];
            }

            quickSort(smaller, ns);
            quickSort(larger, nl);

            int i = 0;

            for (int j=0; j<ns; j++) arr[i++] = smaller[j];
            for (int j=0; j<ne; j++) arr[i++] = equal[j];
            for (int j=0; j<nl; j++) arr[i++] = larger[j];

        }

    }

    /*
     * First and Last are good parameters to use because making a sub array isnt as trivial as making a substring
     * 
     * You can make the program more memory efficient by using sub arrays instead of creating new arrays of the same length 
     * over and over
     */
    public static void mergeSort(int[] arr, int first, int last) {

        if (first < last) {

            int mid = (first + last) / 2;
            mergeSort(arr, first, mid);
            mergeSort(arr, mid+1, last);
            merge(arr, first, mid, last);

        }

    }

    // merge method for the merge sort method
    public static void merge(int[] arr, int first, int mid, int last) {

        // instead of using sub-arrays, pointers are used on the same array
        int[] temp = new int[last-first+1];

        int i = first;
        int j = mid + 1;
        int k = 0;

        while (i <= mid && j <= last) {

            if (arr[i] < arr[j]) temp[k] = arr[i++];
            else temp[k] = arr[j++];
            k++;

        }

        // the two while loops below if there one of the arrays still have values, if they do, they are just added to 
        // end of the temp array
        while (i <= mid) temp[k++] = arr[i++];
        while (j <= last) temp[k++] = arr[j++];

        // copies the now sorted temp array in the main array starting from the back and going to the end
        while (k-- > 0) arr[k+first] = temp[k];

    }

    public static void mergeSortWithSubArrays(int[] arr) {

        if (arr.length > 1) {

            int mid = arr.length / 2;

            int[] left = new int[mid];
            int[] right = new int[arr.length - mid];

            System.arraycopy(arr, 0, left, 0, left.length);
            System.arraycopy(arr, mid, right, 0, right.length);

            mergeSortWithSubArrays(left);
            mergeSortWithSubArrays(right);

            mergeWithSubArrays(arr, left, right);

        }

    }

    public static void mergeWithSubArrays(int[] arr, int[] left, int[] right) {

        int i=0, j=0, k=0;

        while (i < left.length && j < right.length) {

            if (left[i] < right[j]) arr[k++] = left[i++];
            else arr[k++] = right[j++];

        }

        while (i < left.length) arr[k++] = left[i++];
        while (j < right.length) arr[k++] = right[j++];

    }

    public static void printArr(int[] arr) {
        for (int i=0; i<arr.length-1; i++) {
            System.out.print(arr[i] + ", ");
        }
        System.out.print(arr[arr.length-1] + " ");
    }

}
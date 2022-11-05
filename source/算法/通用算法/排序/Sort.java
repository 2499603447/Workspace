package com.chasedream.algorithmprinciple;

import com.chasedream.utils.Out;

public class Sort {
    public static void main(String[] args) {
        Sort sort = new Sort();
        int arr[] = {49, 38, 65, 97, 76, 13, 27, 49, 55, 04};
        //sort.bubbleSort(arr);
        //sort.insertionSort(arr);
        //sort.selectionSort(arr);
        //sort.shellSort(arr);
        sort.quickSort(arr, 0, arr.length - 1);
        Out.println("Sorted array");
        sort.printArray(arr);
    }

    /* Prints the array */
    void printArray(int arr[]) {
        int n = arr.length;
        for (int i = 0; i < n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    public boolean isParamsValid(int[] arr) {
        return !(arr == null || arr.length <= 1);
    }

    public void bubbleSort(int[] arr) {
        if (!isParamsValid(arr)) {
            return;
        }
        int len = arr.length;
        boolean swapped;
        for (int i = 0; i < len - 1; i++) {
            swapped = false;
            for (int j = 1; j < len - 1 - i; j++) {
                if (arr[j] < arr[j - 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                    swapped = true;
                }
            }

            if (!swapped) {
                break;
            }
        }
    }

    public void insertionSort(int[] arr) {
        if (!isParamsValid(arr)) {
            return;
        }

        int len = arr.length;
        int j;
        int key;
        for (int i = 1; i < len; i++) {
            key = arr[i];
            // key < arr[j - 1]:从小到大;key > arr[j - 1]:从大到小
            for (j = i; j > 0 && key < arr[j - 1]; j--) {
                arr[j] = arr[j - 1];
            }
            arr[j] = key;
        }
    }

    public void selectionSort(int[] arr) {
        if (!isParamsValid(arr)) {
            return;
        }

        int len = arr.length;
        int minIndex;
        int temp;
        for (int i = 0; i < len; i++) {
            minIndex = i;
            for (int j = i + 1; j < len; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }

    public void shellSort(int[] arr) {
        if (!isParamsValid(arr)) {
            return;
        }

        int len = arr.length;
        /*int increase = len / 2;
        int key;
        int j;
        while (increase >= 1) {
            for (int i = increase; i < len; i++) {
                key = arr[i];
                for (j = i; j - increase >= 0 && key < arr[j - increase]; j -= increase) {
                    arr[j] = arr[j - increase];
                }
                arr[j] = key;
            }

            increase = increase / 2;
        }*/
        shellSortRecursive(arr, len, len / 2);
    }

    public void shellSortRecursive(int[] arr, int len, int increase) {
        if (increase < 1) {
            return;
        }

        int key;
        int j;
        for (int i = increase; i < len; i++) {
            key = arr[i];
            for (j = i; j >= increase && key < arr[j - increase]; j -= increase) {
                arr[j] = arr[j - increase];
            }
            arr[j] = key;
        }
        shellSortRecursive(arr, len, increase / 2);
    }

    public void mergeSort(int[] arr, int len) {

    }

    public void quickSort(int[] arr, int low, int high) {
        if (low > high) {
            return;
        }
        int key = arr[low];
        int i = low;
        int j = high;
        while (i < j) {
            while (i < j && arr[j] >= key) {
                j--;
            }
            arr[i] = arr[j];
            while (i < j && arr[i] < key) {
                i++;
            }
            arr[j] = arr[i];
        }

        arr[i] = key;

        quickSort(arr, low, i - 1);
        quickSort(arr, i + 1, high);
    }

    public void heapSort(int[] arr, int len) {

    }

}

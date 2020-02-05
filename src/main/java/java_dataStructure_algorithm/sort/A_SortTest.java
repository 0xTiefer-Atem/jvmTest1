package java_dataStructure_algorithm.sort;

import java.util.Arrays;

public class A_SortTest {
    public static void main(String[] args) {
        int[] values1 ={20, 76, 32, 1, 10, 67, 4, 89, 0, 21};
//        BubbleSort.bubble_sort(values1);
//        SelectSort.select_sort(values1);
//        InsertSort.insert_sort(values1);
        int[] values2 = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        ShellSort.shell_sort1(values2);
    }
}
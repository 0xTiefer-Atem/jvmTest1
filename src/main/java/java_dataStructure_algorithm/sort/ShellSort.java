package java_dataStructure_algorithm.sort;

import java.util.Arrays;
public class ShellSort {

    public static void shell_sort1(int[] values){
        System.out.println("希尔排序(交换的方式)");
        System.out.println("排序前:  " + Arrays.toString(values));
        int length = values.length;
        for (int gap = values.length / 2; gap >0; gap = gap/2){
            for (int i = gap; i < length; i++) {
                for (int j = i - gap; j >= 0 ; j = j - gap) {
                    if(values[j] > values[j + gap]){
                        int temp = values[j];
                        values[j] = values[j + gap];
                        values[j + gap] = temp;
                    }
                }
            }
            System.out.println("中间排序结果:     "+Arrays.toString(values));
        }
        System.out.println("排序后:  " + Arrays.toString(values));
    }


    public static void shell_sort2(int[] values){
        System.out.println("希尔排序(移位的方式)");
        System.out.println("排序前:  " + Arrays.toString(values));
        int length = values.length;
        for (int gap = values.length / 2; gap >0; gap = gap/2){
            for (int i = gap; i < length; i++) {
                for (int j = i - gap; j >= 0 ; j = j - gap) {
                    if(values[j] > values[j + gap]){
                        int temp = values[j];
                        values[j] = values[j + gap];
                        values[j + gap] = temp;
                    }
                }
            }
            System.out.println("中间排序结果:     "+Arrays.toString(values));
        }
        System.out.println("排序后:  " + Arrays.toString(values));
    }

}

package java_dataStructure_algorithm.sort;

import java.util.Arrays;

public class SelectSort {

    //选择排序
    public static void select_sort(int[] values){
        System.out.println("选择排序");
        System.out.println("排序前:  " + Arrays.toString(values));
        int length = values.length;
        for (int i = 0; i < length; i++) {
            int min_index = i;
            for (int j = i; j < length; j++) {
                if(values[min_index] > values[j]){
                    min_index = j;
                }
            }
            int temp_value = values[min_index];
            values[min_index] = values[i];
            values[i] = temp_value;
            System.out.println("中间排序结果:     "+Arrays.toString(values));
        }
        System.out.println("排序后:  " + Arrays.toString(values));
    }

}

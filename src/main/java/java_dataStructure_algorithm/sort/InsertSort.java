package java_dataStructure_algorithm.sort;

import java.util.Arrays;

public class InsertSort {

    //插入排序  内部排序
    public static void insert_sort(int[] values) {
        System.out.println("插入排序");
        System.out.println("排序前:  " + Arrays.toString(values));
        int length = values.length;
        for (int i = 1; i < length; i++) {
            int insertIndex = i - 1;
            int insertValue = values[i];
            while (insertIndex >= 0 && insertValue < values[insertIndex]) {
                values[insertIndex + 1] = values[insertIndex];
                insertIndex--;
            }
            if(insertIndex + 1 != i) {
                values[insertIndex + 1] = insertValue;
            }
            System.out.println("中间排序结果:     " + Arrays.toString(values));
        }
        System.out.println("结果:  " + Arrays.toString(values));
    }

}


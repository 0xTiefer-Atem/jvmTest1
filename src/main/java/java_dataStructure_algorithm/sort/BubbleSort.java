package java_dataStructure_algorithm.sort;

import java.util.Arrays;

public class BubbleSort {

    //冒泡排序: 两两比较逆序交换 时间复杂度(O(n*n))
    public static void bubble_sort( int[] values){
        System.out.println("冒泡排序");
        System.out.println("排序前:  " + Arrays.toString(values));
        int length = values.length;
        boolean flag = false; //标识符，表示是否进行过交换
        for (int i = 0; i < length -1; i++) {
            for (int j = 0; j < length-1-i; j++) {
                if(values[j] > values[j+1]){
                    flag = true;
                    int temp_value = values[j];
                    values[j] = values[j+1];
                    values[j+1] = temp_value;
                }
            }
            System.out.println("中间排序结果:     "+Arrays.toString(values));
            if(!flag){ //在一趟排序中，一次交换都没有
                break;
            }else {
                flag = false;
            }
        }
        System.out.println("结果:  " + Arrays.toString(values));
    }

}

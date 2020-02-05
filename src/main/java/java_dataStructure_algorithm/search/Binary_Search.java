package java_dataStructure_algorithm.search;


//二分查找法
public class Binary_Search {
    public static int binary_search(int key, int[] values){
        int low = 0;
        int high = values.length;
        while (low < high){
            int mid = (low + high)/2;
            if(key == values[mid]){
                return mid;
            }
            if(key > values[mid]){
                low = mid + 1;
            }
            if(key < values[mid]){
                high = mid - 1;
            }
        }
        return -1;
    }
}

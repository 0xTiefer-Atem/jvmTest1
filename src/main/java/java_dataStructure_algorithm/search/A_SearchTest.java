package java_dataStructure_algorithm.search;

public class A_SearchTest {
    public static void main(String[] args) {
        int[] values = {1, 3, 5, 8, 10, 23, 56, 87};
        int index = Binary_Search.binary_search(4,values);
        System.out.println(index);
    }
}

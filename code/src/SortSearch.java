import java.util.Arrays;

public class SortSearch {
    public static void main(String args[]) {
        int[] ip = {5, 2, 4, 9, 6, 7};
        quickSort(ip, 0, ip.length - 1);
        System.out.println(Arrays.toString(ip));

    }

    static void quickSort(int[] ip, int start, int end) {
        if (start >= end) return;
        System.out.println("QS - st:" + start + " end:" + end);

        int p = qPartition(ip, start, end);
        quickSort(ip, start, p - 1);
        quickSort(ip, p + 1, end);

    }

    static int qPartition(int[] ip, int start, int end) {
        int pivot = ip[end];
        int i = start;
        for (int j = i; j < end; j++) {
            if (ip[j] < pivot) {
                swap(ip, i, j);
                i++;
            }
        }
        swap(ip, i, end);
        return i;
    }

    static void swap(int[] ip, int a, int b) {
//        In this swap method if u do the arithmetic than the forloop logic gets messed up as it is keeping addresses... instead use temp variables
//        ip[a] = ip[a] + ip[b];
//        ip[b] = ip[a] - ip[b];
//        ip[a] = ip[a] - ip[b];
        int tmp = ip[a];
        ip[a] = ip[b];
        ip[b] = tmp;
    }

}


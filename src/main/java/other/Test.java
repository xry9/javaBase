package other;


//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Scanner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test{
    static Map<String, String> map = new HashMap();
    public static void main(String[] args) throws InterruptedException {
        int[] arr = {1, 4, 2, 3, 5}; // 0 11, 0 5, 6 11,   ,,, 0 10
        List<Integer> list = new ArrayList<>();
        f(arr, list, 0);
    }

    public static void f(int[] arr, List<Integer> list, int start) throws InterruptedException {
//        Thread.sleep(100);
        if (arr.length==start+1){
            System.out.println(list);
        }else {
            int size = list.size();
            System.out.println(start+"===111==="+size);
            for (int i=size; i< arr.length; i++){
                System.out.println(start+"===222==="+size);
                if ((list.size()>0 && arr[i]>list.get(list.size()-1))||
                    list.size()==0){
                    list.add(arr[i]);
                    f(arr, list, start+1);
                    int size_new = list.size();
                    for (int j=0; j<size_new - size; j++){
                        list.remove(list.size()-1);
                    }
                    f(arr, list, start+1);
                }else {
                    start = arr.length-1;
                    f(arr, list, start);
                }
            }
        }
    }

//    public static void f(int[] arr, List<Integer> list, int deep){
//        if (list.size()==deep){
//            System.out.println(list);
//        }else {
//            for (int i=0; i< arr.length; i++){
//                if (arr[i]==-1){
//                    continue;
//                }
//                int copy[] = copy(arr);
//                int size = list.size();
//                list.add(arr[i]);
//                arr[i] = -1;
//                f(arr, list, deep);
//                for (int j=0; j<list.size() - size; j++){
//                    list.remove(list.size()-1);
//                }
//                arr = copy;
//            }
//        }
//    }
//
//
//    public static int[] copy(int[] source){
//        int[] target = new int[source.length];
//        for (int i=0; i<target.length; i++){
//            target[i] = source[i];
//        }
//        return target;
//    }
//    public int[] make(){
//        int[] weight = {1, 3, 2, 3, 5, 2, 5, 7};
//        return weight;
//    }







//    public static void f(int[] arr, List<Integer> list, int deep) throws InterruptedException {
//        if (list.size()==deep){
//            List<Integer> list1 = new ArrayList<>(list);
//            list1.sort(new Comparator<Integer>() {
//                @Override
//                public int compare(Integer o1, Integer o2) {
//                    return o1 - o2;
//                }
//            });
//            String r = list1.toString();
//            if (!map.containsKey(r)){
//                map.put(r, "");
//                System.out.println(list1);
//            }
//            return;
//        }else {
//            for (int i=0; i<arr.length; i++){
//                if (list.contains(arr[i])){
//                    continue;
//                }
//                list.add(arr[i]);
//                f(arr, list, deep);
//                for (int j=0; j<1; j++){
//                    list.remove(list.size()-1);
//                }
//            }
//        }
//    }



//    int maze[][] = new int[5][5];
//    int[] a0 = {0, 1, 0, 0, 0};
//    int[] a1 = {0, 1, 1, 1, 0};
//    int[] a2 = {0, 0, 0, 0, 0};
//    int[] a3 = {0, 1, 1, 1, 0};
//    int[] a4 = {0, 0, 0, 1, 0};

//    public static int f(int[][] maze, int x, int y){
//        if(x>=5 || y>=5){
//            return 1;
//        }else{
//            int v = maze[x][y];
//            if(v==0){
//                v = f(maze, ++x, y);
//                if(v==1){
//                    v = f(maze, --x, ++y);
//                }else {
//                    v = f(maze, ++x, y);
//                }
//                System.out.println("("+x+","+y+")");
//            }
//            return v;
//        }
//    }
}

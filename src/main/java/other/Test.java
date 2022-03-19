package other;

import java.util.*;

public class Test{
    static Map<String, String> map = new HashMap();
    public static void main(String[] args) throws InterruptedException {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}; //
        List<Integer> list = new ArrayList<>();
        f(arr, list, 9);
    }

    public static void f(int[] arr, List<Integer> list, int deep) throws InterruptedException {
        if (list.size()==deep){
            List<Integer> list1 = new ArrayList<>(list);
            list1.sort(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o1 - o2;
                }
            });
            String r = list1.toString();
            if (!map.containsKey(r)){
                map.put(r, "");
                System.out.println(list1);
            }
            return;
        }else {
//            cur++;
//            System.out.println(cur+"===111==="+list.size());
            for (int i=0; i<arr.length; i++){
                if (list.contains(arr[i])){
                    continue;
                }
                list.add(arr[i]);
                f(arr, list, deep);
//                System.out.println(cur+"===222==="+list.size());
                for (int j=0; j<1; j++){
                    list.remove(list.size()-1);
                }
            }
        }
    }


    public int[] make(){
        int[] weight = {1, 3, 2, 3, 5, 2, 5, 7};
        return weight;
    }

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

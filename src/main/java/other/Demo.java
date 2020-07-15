package other;

import java.io.IOException;
import java.util.*;

public class Demo {
    List<Node> nodes = new ArrayList<>();
    int index = -1;
    Node head = null;
    public static void main(String[] args) throws IOException, InterruptedException {
        int arr[] = {3,2,4,5,1,8,6,9,7,0,12,11,15,13,10,14,21,18,19,17,25,23,2};
        insertSort(arr);
        shellSort(arr);
//        quickSort(arr, 0, arr.length-1);
//        selectSort(arr);
//        merge(arr, 0, (arr.length-1)/2, arr.length-1);
//        countSort(arr, 10);
//        radixSort(arr, 3);
//        heapSort(arr);
        System.out.println(Arrays.toString(arr));
//
//        //匿名内部类
//        Comparator<Integer> cpt = new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return Integer.compare(o1,o2);
//            }
//        };
//        TreeSet<Integer> set = new TreeSet<>(cpt);
//        System.out.println("=========================");
//        //使用lambda表达式
//        Comparator<Integer> cpt2 = (x,y) -> Integer.compare(x,y);
//        TreeSet<Integer> set2 = new TreeSet<>(cpt2);
//
    }

//    public static void main(String[] args) {
//        Demo demo = new Demo();
//        Node root = new Node(8);
//        push(root, new Node(6));
//        push(root, new Node(12));
//        push(root, new Node(7));
//        push(root, new Node(10));
//        push(root, new Node(11));
//        push(root, new Node(9));
//        push(root, new Node(15));
//        push(root, new Node(14));
//        push(root, new Node(16));
////        push(root, new Node(17));
////        push(root, new Node(18));
////        push(root, new Node(4));
////        push(root, new Node(3));
////        push(root, new Node(5));
////        push(root, new Node(13));
////        System.out.println(findDeep(root, 1));
////        aftPrintStack(root);
////        System.out.println();
////        System.out.println();
////        aftPrintStack0(root);
////        System.out.println();
////        afterPrint(root);
////        midPrint(root);
//
//        demo.midPrint2Link(root);
//        System.out.println();
//        while (demo.head!=null){
//            System.out.print(demo.head.val+", ");
//            demo.head = demo.head.left;
//        }
////        midPrintStack(root);
////        midPrintStack2Link(root);
////        prePrint(root);
////        System.out.println();
////        prePrintStack(root);
//
////        Node head = findLeftBottom(root);
////        midPrintLink(root);
////        nextPrint(head);
////        System.out.println();
//
////        midPrint(root);
//
//
////        int[] arr = {8, 6, 7, 12, 10, 9, 11, 13};
////        prePrint(createPreNode(arr, 0, arr.length-1));
//
////        int[] arr = {7, 6, 9, 11, 10, 13, 12, 8};
////        afterPrint(createAfterNode(arr, 0, arr.length-1));
//
////        int[] arrpre = {1,2,4,7,3,5,6,8};
////        int[] arrmid = {4,7,2,1,5,3,8,6};
////        preMidCreate(arrpre, arrmid, 0, arrpre.length-1, 0, arrmid.length-1);
////        prePrint(preMidCreate(arrpre, arrmid, 0, arrpre.length-1, 0, arrmid.length-1));
////        System.out.println();
////        midPrint(preMidCreate(arrpre, arrmid, 0, arrpre.length-1, 0, arrmid.length-1));
//
////        int[] arr = {1,2,4,6,8,11,12};
////        System.out.println(twoSplitSelect(arr, 0, arr.length-1, 9));
////        System.out.println(twoSplitSelectStack(arr, 0, arr.length-1, 5));
////        Demo demo = new Demo();
////        int[] nums = {2,1,4,-1,-1,3};
////        demo.index=1;
////        Node root = demo.putTreeStack(nums);
////        Node root = demo.putTree(nums, new Node(nums[0]), demo.index=1);
////        prePrint(root);
//
////        midPrint(root);
//
//    }

    public Node putTree(int[] nums, Node root, int start){
        if (start>=nums.length){
            return root;
        }
        if (nums[start]!=-1){
            root.left = new Node(nums[start]);
        }
        if (start+1<nums.length&&nums[start+1]!=-1){
            root.right = new Node(nums[start+1]);
        }
        index = index+2;
        if (root.left!=null){
            root.left = putTree(nums, root.left, index);
        }
        if (root.right!=null){
            root.right = putTree(nums, root.right, index);
        }
        return root;
    }

    public Node putTreeStack(int[] nums){
        Stack<Node> stack = new Stack();
        Node root = new Node(nums[0]);
        stack.push(root);
        Node node = null;
        index = 0;
        while (!stack.isEmpty()){
            node = stack.pop();
            if (index+1<nums.length&&nums[index+1]!=-1){
                node.left = new Node(nums[index+1]);
            }
            if (index+2<nums.length&&nums[index+2]!=-1){
                node.right = new Node(nums[index+2]);
            }
            index = index + 2;
            if (node.right!=null){
                stack.push(node.right);
            }
            if (node.left!=null){
                stack.push(node.left);
            }
        }

        return root;
    }

    public void findBug(Node root){
        if (root.left!=null){
            if (root.left.val>root.val){
                nodes.add(root);
            }
            prePrint(root.left);
        }
        if (root.right!=null){
            if (root.right.val>root.val){
                nodes.add(root);
            }
            prePrint(root.right);
        }
    }

    public static Node preMidCreate(int[] pre, int[] mid, int preStart, int preEnd, int midStart, int midEnd){
        if (preStart==preEnd||midStart==midEnd){
            return new Node(pre[preStart]);
        }else if (preStart>preEnd||midStart>midEnd){
            return null;
        }
        int rootVal = pre[preStart];
        int index = midStart;
        while (mid[index]!=rootVal&&index<=midEnd){
            index++;
        }
        Node root = new Node(rootVal);
        root.left = preMidCreate(pre, mid, preStart+1, preStart+index-midStart, midStart, index-1);
        root.right = preMidCreate(pre, mid, preStart+index-midStart+1, preEnd, index+1, midEnd);
        return root;

    }

    public static void push(Node root, Node node){
        if (node.val<root.val){
            if (root.left==null){
                root.left = node;
            }else {
                push(root.left, node);
            }
        }else if (node.val>root.val){
            if (root.right==null){
                root.right = node;
            }else {
                push(root.right, node);
            }
        }
    }

    public static void prePrint(Node root){
        System.out.print(root.val+", ");
        if (root.left!=null){
            prePrint(root.left);
        }
        if (root.right!=null){
            prePrint(root.right);
        }
    }

    public static void prePrintStack(Node root){
        Stack<Node> stack = new Stack();
        stack.push(root);
        while (!stack.isEmpty()){
            root = stack.pop();
            System.out.print(root.val+", ");
            if (root.right!=null){
                stack.push(root.right);
            }
            if (root.left!=null){
                stack.push(root.left);
            }
        }
    }


    public static void midPrintStack(Node root){
        Stack<Node> stack = new Stack();
        while (!stack.isEmpty()||root!=null){
            if (root.left!=null){
                stack.push(root);
                root = root.left;
            }else {
                System.out.print(root.val+", ");
                if (root.right!=null){
                    root = root.right;
                }else {
                    if (!stack.isEmpty()){
                        root =  stack.pop();
                        System.out.print(root.val+", ");
                        root = root.right;
                    }else {
                        break;
                    }
                }
            }
        }

    }

    public static void midPrintStack2Link(Node root){
        Stack<Node> stack = new Stack();
        Node head = null;
        Node tmp = null;
        while (!stack.isEmpty()||root!=null){
            if (root.left!=null){
                stack.push(root);
                root = root.left;
            }else {
                System.out.print(root.val+". ");
                if (head == null){
                    head = root;
                    tmp = root;
                }else {
                    tmp.right = root;
                    root.left = tmp;
                    tmp = root;
                }
                if (root.right!=null){
                    root = root.right;
                }else {
                    if (!stack.isEmpty()){
                        root =  stack.pop();
                        if (head == null){
                            head = root;
                            tmp = root;
                        }else {
                            tmp.right = root;
                            root.left = tmp;
                            tmp = root;
                        }
                        System.out.print(root.val+", ");
                        root = root.right;
                    }else {
                        break;
                    }
                }
            }
        }
        System.out.println();
        while (head != null){
            System.out.print(head.val + "- ");
            head = head.right;
        }
        System.out.println();
        while (tmp != null){
            System.out.print(tmp.val + "- ");
            tmp = tmp.left;
        }

    }

    public static void aftPrintStack(Node root){
        Stack<Node> stack = new Stack<Node>();
        stack.push(root);
        boolean isLoad = false;
        Node last = null;
        while (!stack.isEmpty()){
            if (isLoad){//stack.peek().right==root
                System.out.print(root.val+", ");
                last = stack.pop();
                if (last.right==root){
                    root = last;
                    isLoad = true;
                }else {
                    root = last;
                    isLoad = false;
                }
            }else {
                if (root.left==null&&root.right==null){
                    System.out.print(root.val+", ");
                    last = stack.pop();
                    if (root==last.left||root==last.right){
                        root = last;
                        isLoad = true;
                        continue;
                    }else {
                        if (last.right==null&&last.left==null){
                            System.out.print(last.val+", ");
                            last = stack.pop();
                            root = last;
                            isLoad = true;
                            continue;
                        }else {
                            root = last;
                        }
                    }
                }
                if (root.left==null&&root.right!=null){
                    stack.push(root);
                    root = root.right;
                    continue;
                }
                if (root.left!=null&&root.right==null){
                    stack.push(root.left);
                    root = root.left;
                    continue;
                }
                if (root.left!=null&&root.right!=null){
                    stack.push(root);
                    stack.push(root.right);
                    root = root.left;
                    continue;
                }
            }
        }
    }

    public static void aftPrintStack0(Node root){
        Stack<Node> stack = new Stack<Node>();
        stack.push(root);
        Node last = null;
        while (!stack.isEmpty()){
            if (last!=null&&(root==last.left||root==last.right)){
                root = last;
                System.out.print(root.val+", ");
                last = stack.pop();
            }else {
                if (last!=null){
                    root = last;
                }
                if (root.left==null&&root.right==null){
                    System.out.print(root.val+", ");
                    last = stack.pop();
                    if (root==last.left||root==last.right){
                        continue;
                    }else {
                        if (last.right==null&&last.left==null){
                            System.out.print(last.val+", ");
                            last = stack.pop();
                            continue;
                        }
                    }
                }
                if (root.left==null&&root.right!=null){
                    stack.push(root);
                    last = root.right;
                    continue;
                }
                if (root.left!=null&&root.right==null){
                    stack.push(root.left);
                    last = root.left;
                    continue;
                }
                if (root.left!=null&&root.right!=null){
                    stack.push(root);
                    stack.push(root.right);
                    last = root.left;
                    continue;
                }
            }
        }
    }

    public static void midPrintLink(Node root){
        if (root.left!=null){
            midPrintLink(root.left);
        }
        Node tmpRight = root.right;
//        leftBOttomNode.left = root;
        if (tmpRight!=null){
            midPrintLink(tmpRight);
            if (root.right.left!=null){
                Node leftBOttomNode = findLeftBottom(root.right);
                root.right = leftBOttomNode;
            }
        }
    }

    public static void nextPrint(Node head){

        System.out.print(head.val+",");
        if (head.right!=null){
            nextPrint(head.right);
        }

    }

    public static Node findLeftBottom(Node root){
        if (root.left==null){
            return root;
        }
        return findLeftBottom(root.left);
    }

    public static Node findRightBottom(Node root){
        if (root.right==null){
            return root;
        }else {
            return findRightBottom(root.right);
        }
    }

    public static void midPrint(Node root){
        if (root.left!=null){
            midPrint(root.left);
        }
        System.out.print(root.val+", ");
        if (root.right!=null){
            midPrint(root.right);
        }
    }

    public Node midPrint2Link(Node root){
//        Node tmp = root;
        if (root.left!=null){
            midPrint2Link(root.left);
        }
        if (head==null){
            head = root;
        }else {
            head.right = root;
            root.left = head;
            head = root;
        }
        System.out.print(root.val+", ");
        if (root.right!=null){
            midPrint2Link(root.right);
        }
        return root;
    }

    public static int findDeep(Node root, int curDeep){
        int leftDeep =curDeep;
        int rightDeep =curDeep;
        if (root.left!=null){
            leftDeep = findDeep(root.left, ++curDeep);
            curDeep--;
        }
        if (root.right!=null){
            rightDeep = findDeep(root.right, ++curDeep);
            curDeep--;
        }
        return leftDeep>rightDeep?leftDeep:rightDeep;
    }



    public static void afterPrint(Node root){
        if (root.left!=null){
            afterPrint(root.left);
        }
        if (root.right!=null){
            afterPrint(root.right);
        }
        System.out.print(root.val+", ");
    }

    public static Node createPreNode(int[] arr, int start, int end){
        if(start==end){
            int val = arr[start];
            return new Node(val);
        }else if(start>end){
            return null;
        }
        int val = arr[start];
        Node root = new Node(val);
        int i = start+1;
        while(arr[i]<arr[start]){
            i++;
        }
        root.left = createPreNode(arr, start+1, i-1);
//        if (start+1<=i-1){
//        }
        root.right = createPreNode(arr, i, end);
        return root;
    }

    public static Node createAfterNode(int[] arr, int start, int end){
        if(start==end){
            int val = arr[start];
            return new Node(val);
        }else if(start>end){
            return null;
        }
        int val = arr[end];
        Node root = new Node(val);
        int i = end-1;
        while(arr[i]>arr[end]&&i>0){
            i--;
        }
        root.right = createAfterNode(arr, i+1, end-1);
        root.left = createAfterNode(arr, start, i);
        return root;
    }

    public static void rain(int[] nums){
        int sum = 0;
        int first=0;
        int max=0;
        while (nums[first++]!=0){
            max = nums[first];
        }
        int index = first;
        while (nums[index++]>=max&&index<nums.length-1){
            sum += sumRain(nums, first, index);
            max = nums[index];
            first = index;
        }
        if (nums.length-1>index+1){
            sum += endRain(nums, index);
        }
        System.out.println(sum);
    }

    public static int endRain(int[] nums, int start){
        int sum = 0;
        int max = nums[start+1];

        int index =start+1;
        while (index<nums.length){
            index++;
            if (nums[index]>max){
                max = nums[index];
                sum += sumRain(nums, start, index);
                sum += endRain(nums, index);
                start = index;
            }
        }
        return sum;
    }

    public static int sumRain(int[] nums, int start, int end){
        int sum = Math.abs(nums[start] - nums[end]) * (end - start-1);
        for (int i=start+1;i<end;i++){
            sum -= i;
        }
        return sum>0?sum:0;
    }
    public static int twoSplitSelect(int[] arr, int start, int end, int k){
        if (start>end){
            return -1;
        }
        if (start==end){
            return k==arr[start]?start:-1;
        }
        int mid = start+(end - start)/2;
        if (arr[mid]==k){
            return mid;
        }else if (arr[mid]>k){
            return twoSplitSelect(arr, start, mid, k);
        }else if (arr[mid]<k){
            return twoSplitSelect(arr, mid+1, end, k);
        }
        return -1;
    }

    public static int twoSplitSelectStack(int[] arr, int start, int end, int k){
        int mid = start+(end-start)/2;
        while (arr[mid]!=k){
            if (start==mid){
                return k==arr[start]?mid:-1;
            }
            if (mid==end){
                return k==arr[mid]?mid:-1;
            }
            if (arr[mid]>k){
                end = mid;
                mid = start+(mid-start)/2;
            }
            if (arr[mid]<k){
                start = mid;
                mid = mid+(end-mid)/2;
            }
        }
        if (arr[mid]==k){
            return mid;
        }
        return -1;
    }

    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        LinkedList<Node> queue = new LinkedList<Node>();
        if (root == null) {
            return res;
        }
        queue.offer(root);
        while (queue.size() != 0) {
            List<Integer> l = new ArrayList<Integer>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node temp = queue.poll();
                l.add(temp.val);
                if (temp.left != null) {
                    queue.offer(temp.left);
                }
                if (temp.right != null) {
                    queue.offer(temp.right);
                }
            }
            res.add(l);
        }
        return res;
    }


    public static void heapSort(int[] arr){
        for (int i=arr.length-1;i>=1;i--){
            if (arr[i]>arr[(i-1)/2]){
                swap(arr, i, (i-1)/2);
                downNode(arr, i, arr.length);
            }
        }
        for (int i=arr.length-1;i>0;i--){
            swap(arr, 0, i);
            downNode(arr,0, i);
        }
    }

    public static void downNode(int[] nums, int start, int end){
        while (start*2+2<=end){
            int next = -1;
            if ((start*2+1==end-1)||nums[start*2+1]>nums[start*2+2]){
                next = start*2+1;
            }else {
                next = start*2+2;
            }
            if (nums[start]<nums[next]){
                swap(nums, start, next);
                start = next;
            }else {
                break;
            }
        }
    }

    public static void radixSort(int arr[], int radix){
        List<List<Integer>> list = new ArrayList<>();
        for (int i=0;i<10;i++){
            list.add( new ArrayList<Integer>());
        }
        for (int r=0;r<radix;r++){
            for (int i=0;i<arr.length;i++){
                list.get(arr[i]/(int)Math.pow(10, r)%10).add(arr[i]);
            }
            int index = 0;
            for (List<Integer> ilist: list){
                for (Integer i: ilist){
                    arr[index++] = i;
                }
                ilist.clear();
            }
        }

    }
    public static void countSort(int[] arr, int k){
        int array[] = new int[k+1];
        for (int i=0;i<arr.length;i++){
            array[arr[i]] += 1;
        }
        int index = 0;
        for (int i=0;i<array.length;i++){
            for (int j=0;j<array[i];j++){
                arr[index++] = i;
            }
        }
    }

    public static void insertSort(int[] arr){
        for (int i=1;i<arr.length;i++){
            int tmp = arr[i];
            int index = i-1;
            while (index>=0&&tmp<arr[index]){
                arr[index+1] = arr[index];
                index--;
            }
            arr[index+1] = tmp;
        }
    }

    public static void shellSort(int[] arr){
        for (int n=arr.length/2;n>0;n=n/2){
            for (int i=n;i<arr.length;i++){
                int tmp = arr[i];
                int index = i-n;
                while (index>=0&&tmp<arr[index]){
                    arr[index+n] = arr[index];
                    index = index -n;
                }
                arr[index+n] = tmp;
            }
        }
    }

    public static void selectSort(int[] arr){
        for (int i=0;i<arr.length/2;i++){
            int min=arr[i], max=arr[arr.length-i-1], m=i, n=arr.length-1-i;
            for (int j=i;j<arr.length-i;j++){
                if (arr[j]<min){
                    min = arr[j];
                    m = j;
                }
                if (arr[j]>max){
                    max = arr[j];
                    n = j;
                }
            }
            swap(arr, i, m);
            swap(arr, n, arr.length-1-i);
        }
    }

    public static void merge(int[] arr, int low, int mid, int high){
        if (low>=high){
            return;
        }
        if (high-low>1){
            merge(arr, low, (low + mid)/2, mid);
            merge(arr, mid+1, (high + mid)/2, high);
        }

        int tmp[] = new int[high-low+1];
        int min_i = low;
        int led = high-low+1;
        int mm = mid;
        for (int i=0;i<led ;i++){
            if (mid==high||(arr[low]<=arr[mid+1] && low<=mm)){
                tmp[i] = arr[low];
                low++;
            }else if (low==mm || mid<high){
                tmp[i] = arr[mid+1];
                if (mid!=high){
                    mid++;
                }
            }
        }
        for (int i=0;i<tmp.length;i++){
            arr[min_i++] = tmp[i];
        }
    }



    public static void quickSort(int[] arr, int low, int hight){
        if (low>=hight){
            return;
        }
        int tmp = arr[low];
        int index = low;
        for (int i=low+1;i<=hight;i++){
            if (arr[i]<=tmp){
                if (index+1==i){
                }else {
                    swap(arr, index+1, i);
                }
                index ++;
            }else {
            }
        }
        if (arr[low]>arr[index]){
            swap(arr, low, index);
        }
        quickSort(arr, low, index);
        quickSort(arr, index+1, hight);
    }
    public static void swap(int[] arr, int i1, int i2){
        int tmp = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = tmp;
    }

}

class Node{
    public int val;
    public Node left;
    public Node right;

    public Node(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "Node{" + "val=" + val + '}';
    }
}

package dataStructure;

public class BTree<Key extends Comparable<Key>, Value> {
    //    https://www.cnblogs.com/jing99/p/11741685.html
    private static final int M = 4;  // B树的阶数
    private Node root;       //  B-tree 的根节点
    private int height;      //  B-tree 的高度
    private int N;           //  B-tree 树中键值对的数目

    // B-tree 节点类型
    private static final class Node {
        private int m;                             // number of children
        private Entry[] children = new Entry[M];   // the array of children
        // create a node with k children
        private Node(int k) { m = k; }
    }
    //  B-tree 节点中的元素类型
    private static class Entry {
        private Comparable key;
        private Object val;
        private Node next;     // 指向节点中下一元素
        public Entry(Comparable key, Object val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        BTree bt = new BTree();
        bt.put(1, "k1");
        bt.put(2, "k2");
        bt.put(3, "k3");
        bt.put(4, "k4");
        bt.put(5, "k5");
        bt.put(6, "k6");
        bt.put(7, "k7");
        bt.put(8, "k8");
        bt.put(9, "k9");
        bt.put(10, "k10");
        bt.put(11, "k11");
        bt.put(12, "k12");
        bt.put(13, "k13");
        bt.put(14, "k14");
        bt.put(15, "k15");
//        bt.put(16, "k16");
        System.out.println(bt.height);
        System.out.println(bt.N);
    }
    /**
     * 初始化空 B-tree树
     */
    public BTree() {
        root = new Node(0);
    }
    /**
     * 判断 B-tree 是否是空树
     */
    public boolean isEmpty() {
        return size() == 0;
    }
    public int size() {
        return N;
    }
    /**
     * get操作
     */
    public Value get(Key key) {
        if (key == null) throw new NullPointerException("key must not be null");
        return search(root, key, height);
    }
    /**
     * put 操作
     */
    public void put(Key key, Value val) {
        if (key == null) throw new NullPointerException("key must not be null");
        Node u = insert(root, key, val, height);
        N++;
        if (u == null){
            return;
        }
        // need to split root
        Node t = new Node(2);
        t.children[0] = new Entry(root.children[0].key, null, root);
        t.children[1] = new Entry(u.children[0].key, null, u);
        root = t;
        height++;
    }
    //  搜索操作
    private Value search(Node x, Key key, int ht) {
        Entry[] children = x.children;
        // 节点内数组操作  内部遍历
        if (ht == 0) {
            for (int j = 0; j < x.m; j++) {
                if (equals(key, children[j].key)) return (Value) children[j].val;
            }
        }
        //  外部定位
        else {
            for (int j = 0; j < x.m; j++) {
                if (j + 1 == x.m || less(key, children[j + 1].key))
                    return search(children[j].next, key, ht - 1);
            }
        }
        return null;
    }
    //  插入操作
    private Node insert(Node h, Key key, Value val, int ht) {
        int j;
        Entry t = new Entry(key, val, null);
        // 节点内部数组操作
        if (ht == 0) {
            for (j = 0; j < h.m; j++) {
                if (less(key, h.children[j].key)) break;
            }
        } else { // 外部遍历
            for (j = 0; j < h.m; j++) {
                if ((j + 1 == h.m) || less(key, h.children[j + 1].key)) {
                    Node u = insert(h.children[j++].next, key, val, ht - 1);
                    if (u == null){
                        return null;
                    }
                    t.key = u.children[0].key;
                    t.next = u;
                    break;
                }
            }
        }
        for (int i = h.m; i > j; i--){
            h.children[i] = h.children[i - 1];
        }
        h.children[j] = t;
        h.m++;
        if (h.m < M){
            return null;
        } else{
            return split(h);
        }
    }
    //  分裂节点成两半
    private Node split(Node h) {
        Node t = new Node(M / 2);
        h.m = M / 2;
        for (int j = 0; j < M / 2; j++){
            t.children[j] = h.children[M / 2 + j];
        }
        return t;
    }

    // 判断两个元素是否相等
    private boolean equals(Comparable k1, Comparable k2) {
        return k1.compareTo(k2) == 0;
    }

    // 判断两个元素的大小
    private boolean less(Comparable k1, Comparable k2) {
        return k1.compareTo(k2) < 0;
    }
}

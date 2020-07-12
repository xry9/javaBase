package tools.tmp;

public class Demo {
    public static void main(String[] args) {
        Node head1 = new Node(2);
        head1.next = new Node(4);
        head1.next.next = new Node(3);
        Node head2 = new Node(5);
        head2.next = new Node(6);
        head2.next.next = new Node(4);

        int k = 0;
        Node tmp1 = head1;
        Node tmp2 = head2;
        while (tmp1!=null&&tmp2!=null){
            if (tmp1.value + tmp2.value+k>=10){
                System.out.print(tmp1.value + tmp2.value+k-10);
                k = 1;
            }else {
                System.out.print(tmp1.value + tmp2.value+k);
                k = 0;
            }
            tmp1 = tmp1.next;
            tmp2 = tmp2.next;
        }
        if (tmp1==null&&tmp2!=null){
            tmp1 = tmp2;
        }else if (tmp1==null&&tmp2==null){
            return;
        }
        while (tmp1!=null){
            System.out.print(tmp1.value);
            tmp1 = tmp1.next;
        }
    }
}
class Node {
    public Node next ;
    public Integer value;
    public Node(int value){
        this.value = value;
    }
}
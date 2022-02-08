package other.test;

public class ForDemo {
    public static void main(String[] args) {
        for (int i=0;i<5;i++){
            for (int j=0;j<5;j++){
                if (j==3){
                    return;
                }
                System.out.println(i+"==="+j);
            }
        }
        System.out.println("===========");
    }
}

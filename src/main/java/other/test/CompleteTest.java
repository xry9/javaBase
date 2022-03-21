package other.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class CompleteTest {

    public static void main(String[] args) {

        ExecutorService executorService = new ThreadPoolExecutor(5, 5, 10,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(1));
//        List<CompletableFuture> list = new ArrayList<>();
//        for (int i = 0; i < 100; i++) {
//            CompletableFuture stringCompletableFuture = new CompletableFuture<>();
//            stringCompletableFuture.whenCompleteAsync((e, a) -> {
//                System.out.println("Complete " + e);
//                try {
//                    Thread.sleep(100);
//                } catch (InterruptedException e1) {
//                    e1.printStackTrace();
//                }
//            }, executorService);
//            list.add(stringCompletableFuture);
//        }
//        for (int i = 0; i < list.size(); i++) {
//            list.get(i).complete(i + "");
//        }




        List<CompletableFuture> list = new ArrayList<>();
        List<CompletableFuture> dependents = new ArrayList<>();
        for (int i = 0; i <100; i++) {
            CompletableFuture stringCompletableFuture = new CompletableFuture<>();
            CompletableFuture thisWillHaveException = stringCompletableFuture.whenCompleteAsync((e, a) -> {
                System.out.println("Complete " + e);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e1) {e1.printStackTrace();}
            }, executorService);
            dependents.add(thisWillHaveException);
            list.add(stringCompletableFuture);
        }
        for (int i = 0; i < list.size(); i++) {
            list.get(i).complete(i + "");
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        dependents.forEach(cf -> {
            cf.whenComplete((r, e) -> {
                if (e != null){
                    System.out.println(cf + "======" + e);
                }
            });
        });
    }
}
package tmp;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class CommondDemo {
    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("/home/tyx/words2.txt")));
        String line = "";
//        while ((line = bufferedReader.readLine())!=null){
//            System.out.println(line);
//        }
        bufferedReader.close();

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(30, 30, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());

        List<FutureTask<MyCallable>> list = new ArrayList<>();
        for (int i=0;i<30;i++){
            list.add(new FutureTask<MyCallable>(new MyCallable("/home/tyx/words"+i+".txt", "T"+i)));
            threadPoolExecutor.submit(list.get(i));
        }
        for (FutureTask<MyCallable> mc: list){
            mc.get();
//            System.out.println(mc.get());

        }

//        threadPoolExecutor.submit(new MyCallable("/home/tyx/words0.txt", "T0"));
//        threadPoolExecutor.submit(new MyCallable("/home/tyx/words1.txt", "T1"));
//        threadPoolExecutor.submit(new MyCallable("/home/tyx/words2.txt", "T2"));
//        threadPoolExecutor.submit(new MyCallable("/home/tyx/words3.txt", "T3"));
//        threadPoolExecutor.submit(new MyCallable("/home/tyx/words4.txt", "T4"));

//        threadPoolExecutor.submit(new MyCallable("/home/tyx/words5.txt", "T5"));
//        threadPoolExecutor.submit(new MyCallable("/home/tyx/words6.txt", "T6"));
//        threadPoolExecutor.submit(new MyCallable("/home/tyx/words7.txt", "T7"));
//        threadPoolExecutor.submit(new MyCallable("/home/tyx/words8.txt", "T8"));
//        threadPoolExecutor.submit(new MyCallable("/home/tyx/words9.txt", "T9"));

        threadPoolExecutor.shutdown();
    }

    static class MyCallable implements Callable {
        public String path;
        public String name;
        public MyCallable(String path, String name){
            this.path = path;
            this.name = name;
        }
        @Override
        public Object call()  {
            try {
            Thread.currentThread().setName(name);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path+"_0")));
            String line = "";
            long l = System.currentTimeMillis();
//            for (int i=0;i<10;i++){
                while (true){
                        if (!((line = bufferedReader.readLine())!=null)) break;
//                    System.out.println(line);
                    bufferedWriter.write(line);
                }
//            }
            bufferedReader.close();
            bufferedWriter.close();
            System.out.println("=======finally..."+(System.currentTimeMillis() - l)+"==="+Thread.currentThread().getName());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return name;
        }
    }

}

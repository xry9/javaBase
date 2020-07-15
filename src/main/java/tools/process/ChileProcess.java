package tools.process;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by garfield on 2016/11/1.
 */
public class ChileProcess {
    public static void main(String[] args) throws IOException, InterruptedException {
        BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
        String line ;
        StringBuffer all = new StringBuffer();
        while((line = s.readLine()) != null){
            all.append(line);
        }
        System.out.println("---"+all);
        s.close();
        Thread.sleep(5000);
        System.out.println("===child finally===");
    }
}
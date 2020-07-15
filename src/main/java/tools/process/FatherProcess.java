package tools.process;

import java.io.*;

/**
 * Created by garfield on 2016/11/9.
 */
public class FatherProcess {
    public static void main(String[] args) throws IOException, InterruptedException {
        Runtime run = Runtime.getRuntime();
        String java = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
        String cp = "\"" + System.getProperty("java.class.path");
        cp += File.pathSeparator + ClassLoader.getSystemResource("").getPath() + "\"";
        String cmd = java + " -cp " + cp + " tmp.ChileProcess";
        Process p = run.exec(cmd);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));
        bw.write("999999");
        bw.flush();
        bw.close();

        BufferedInputStream in = new BufferedInputStream(p.getInputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String s;
        while ((s = br.readLine()) != null){
            System.out.println("==="+s);
        }
        Thread.sleep(5000);
        System.out.println("===father finally===");
    }
}
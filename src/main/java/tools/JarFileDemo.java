package tools;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class JarFileDemo {
    public static void main(String[] args) throws IOException {

        File file = new File(args[0]);
        loop(file, args[1]);
    }

    public static void loop(File file, String type) throws IOException {
        if (file.isDirectory()){
            for (File file1 : file.listFiles()){
                loop(file1, type);
            }
        }else {
            Map<String, Integer> map = doJarFile(file);
            if ("list".equals(type)){
                printClass(map, file);
            }else {
                findClass(map, type, file);
            }
        }
    }

    public static Map<String, Integer> doJarFile(File file) throws IOException {
        Map<String, Integer> map = new HashMap<>();
        if (!file.getPath().endsWith("jar")){
            return map;
        }
//        System.out.println("==="+file.getPath()+"===");
        JarFile jarFile = new JarFile(file);
        Enumeration<JarEntry> files = jarFile.entries();
        while (files.hasMoreElements()) {
            JarEntry entry = (JarEntry) files.nextElement();
            if (entry.getName().endsWith(".class")) {
                if (map.containsKey(entry.getName())){
                    map.put(entry.getName(), map.get(entry.getName()) + 1);
                }else {
                    map.put(entry.getName().split("/")[entry.getName().split("/").length-1].replace(".class", ""), 1);
                }

            }
        }
        return map;
    }
    public static void findClass(Map<String, Integer> map, String className, File file){
        if (map.containsKey(className)){
            System.out.println(file+"==="+map.get(className));
        }
    }

    public static void printClass(Map<String, Integer> map, File file){
        System.out.println(file.getPath()+"::::");
        for (Map.Entry<String, Integer> entry : map.entrySet()){
            System.out.println(entry.getKey()+"==="+entry.getValue());
        }
    }

}

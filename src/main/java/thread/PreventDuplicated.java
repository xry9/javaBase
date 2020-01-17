package thread;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

/**
 * kill -9 is invalid
 */
public class PreventDuplicated {

    public static final String LOCK_PATH = "/tmp";

    public static final String LOCK_FILE = ".lock";

    public static final String PERMISSIONS = "rw------";

    public static void main(String[] args) throws IOException {
        // 1. 植入Hook线程，在程序退出是删除lock文件
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("The program received kill SIGNAL.");
            getLockFile().toFile().delete();
        }));

        // 2. 检查是否有 .lock文件
        checkRunning();

        // 3. 业务程序
        for (;;){
            try {
                TimeUnit.MILLISECONDS.sleep(1);
                System.out.println("Program is running.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void checkRunning() throws IOException {
        Path path = getLockFile();

        if (path.toFile().exists()) {
            throw new RuntimeException("The program is already running.");
        }

        //linux
        //Set<PosixFilePermission> perms = PosixFilePermissions.fromString(PERMISSIONS);
        //Files.createFile(path, PosixFilePermissions.asFileAttribute(perms));
        //windows
        Files.createFile(path);
    }

    private static Path getLockFile() {
        return Paths.get(LOCK_PATH, LOCK_FILE);
    }
}

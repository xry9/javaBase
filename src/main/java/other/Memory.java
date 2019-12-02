package other;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Memory {
	public static void main(String[] args) throws IOException {
		long l = System.currentTimeMillis();
		List<BufferedReader> list = new ArrayList<>();
		//不关流也不置null在14/15之间,关流在38/39,置null似乎没有上限
		for (int i = 0; i < 14_0000; i++) {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("d:\\a.txt")));
//			br = null;
//			br.close();
			list.add(br);
		}
		list.clear();
		System.out.println(System.currentTimeMillis()-l);
	}
}

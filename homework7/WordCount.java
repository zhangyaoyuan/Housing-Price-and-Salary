import java.io.*;
import java.util.*;

public class WordCount {
	public static void main(String args[]) {
		File file_a = new File("F:\\a.txt");
		File file_b = new File("F:\\b.txt");
		String temp = null;
		//记录文件A,B的单词总数（不去重）
		int count_a = 0 , count_b = 0;
		try (BufferedReader br1 = new BufferedReader(new FileReader(file_a));
				BufferedReader br2 = new BufferedReader(new FileReader(file_b));
				FileWriter fw1 = new FileWriter("F:\\c.txt");
				FileWriter fw2 = new FileWriter("F:\\d.txt")) {
			//用hashset集合来记录文件A,B的并集
			HashSet<String> hs = new HashSet<>();
			//用hashmap集合来记录文件A,B的交集
			HashMap<String, Boolean> hm = new HashMap<>();
			while ((temp = br1.readLine()) != null) {
				String[] words = temp.split(" ");
				for (String word : words) {
					//去重
					if (!hs.contains(word)) {
						hs.add(word);
						hm.put(word, false);
					}
					++count_a;
				}
			}
			while ((temp = br2.readLine()) != null) {
				String[] words = temp.split(" ");
				for (String word : words) {
					if (!hs.contains(word)) {
						hs.add(word);
					} else {
						hm.put(word, true);
					}
					++count_b;
				}
			}
			Iterator<String> it1 = hs.iterator();
			//i，j分别记录并集，交集的单词个数
			int i=0,j=0;
			while (it1.hasNext()) {
				fw1.write(it1.next() + " ");
				++i;
			}
			System.out.println("并集文件输出成功！一共" + i + "个单词");
			Iterator<String> it2 = hm.keySet().iterator();
			while (it2.hasNext()) {
				if (hm.get(temp = it2.next())) {
					fw2.write(temp + " ");
					++j;
				}
			}
			System.out.println("交集文件输出成功！一共" + j + "个单词");
			System.out.println("文件A有" + count_a + "个单词。文件B有" + count_b +"个单词");
			System.out.println("文件A的特有单词有"+ (count_a - j) + "个，文件B的特有单词有" + (count_b-j)+"个");
		} catch (IOException ioe) {
			ioe.printStackTrace();

		}
	}
}
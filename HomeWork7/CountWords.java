import java.util.Map;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.io.File;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.BufferedReader;

public class CountWords {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String strFile = "E:\\1.txt";
		String strFile2 = "E:\\2.txt";
		double nWordsInFile1Counts = 0.0;
		double nWordsInFile2Counts = 0.0;
		
		Map<String, Integer> map1 = readTxtFile(strFile);
		Map<String, Integer> map2 = readTxtFile(strFile2);
		
		System.out.println("文件1中的单词有：");
		for(Map.Entry<String, Integer> entry:map1.entrySet())
		{
			System.out.println(entry.getKey()+" ");
			nWordsInFile1Counts += entry.getValue();
		}
		System.out.println("文件1中的单词总个数：" + nWordsInFile1Counts);
		System.out.println("文件2中的单词有：");
		for(Map.Entry<String, Integer> entry:map2.entrySet())
		{
			System.out.println(entry.getKey()+" ");
			nWordsInFile2Counts += entry.getValue();
		}
		System.out.println("文件2中的单词总个数：" + nWordsInFile2Counts);
		
		System.out.println("同时出现在两个文件中的单词有：");
		for(Map.Entry<String, Integer> entry1 : map1.entrySet())
		{
			if(map2.containsKey(entry1.getKey()) )
			{
				System.out.println(entry1.getKey() + " 在文件1中所占比重为：" + 
						entry1.getValue()/nWordsInFile1Counts );
				System.out.println("在文件2中所占比重为：" +
						map2.get(entry1.getKey()).intValue()/nWordsInFile2Counts);
			}
		}
	}
	
	public static Map<String, Integer> readTxtFile(String strFilePath)
	{
		Map<String, Integer> map = new HashMap<String, Integer>();
		try{
			String encoding = "GBK";
			File file = new File(strFilePath);
			if(file.isFile() && file.exists())  //判断文件是否存在
			{
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file), encoding);
				BufferedReader bufferedReader = new BufferedReader(read);
				StringBuffer strbuffer = new StringBuffer();
				
				String str = null;
				while((str = bufferedReader.readLine()) != null)
				{
					strbuffer.append(str);
				}
				StringTokenizer strToken = new StringTokenizer(strbuffer.toString(), " ");
				while(strToken.hasMoreTokens())
				{
					String strLetter = strToken.nextToken();
					int count;
					if(map.get(strLetter) == null)
					{
						count = 1;
					}
					else
					{
						count = map.get(strLetter).intValue() + 1;
					}
					map.put(strLetter, count);
				}
			}
			else
			{
				System.out.println("文件不存在！");
			}
		}catch(Exception e){
			System.out.println("读取文件内容出错！");
			e.printStackTrace();
		}
		return map;
	}

}

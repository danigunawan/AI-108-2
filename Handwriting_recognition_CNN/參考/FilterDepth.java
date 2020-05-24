import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class FilterDepth {

	static  int  Filte_size;
 static String Name = "Filetxt.txt";

	public static ArrayList Filter( int size,int  depth) throws IOException   {
		Filtertext (size,depth);
		ArrayList<String> check_x= new ArrayList<String>();
		check_x=Filter_read();
		return check_x ;
	}
	public static  void  Filtertext( int size,int depth) throws IOException {
		Filte_size=size;
		/* 寫入Txt檔案 */
		File writename = new File(Name); // 相對路徑，如果沒有則要建立一個新的output。txt檔案
		writename.createNewFile(); // 建立新檔案
		BufferedWriter out = new BufferedWriter(new FileWriter(writename));
		Random ran = new Random();
	for(int j =0;j<depth;j++)
	{
		 int RAN[] = new int [size];
	        String int_String [] =new String [size];
			for(int i=0;i<RAN.length;i++)
			{
				int File=ran.nextInt(2)+1-1;
				if(File==0) {File=-1;};
				RAN[i]=File;
				System.out.print("次數"+i+"____________"+RAN[i]+"  size 數 "+size+"目前深度"+j+"\n");
			 int_String[i] = String.valueOf(RAN[i]);
			}
			for(int i = 0 ;i<RAN.length;i++)
			{
				System.out.print(int_String[i]);
				out.write(int_String[i]+","); // \r\n即為換行
			}
			out.write("\n");
	}
		out.flush(); // 把快取區內容壓入檔案
		out.close(); // 最後記得關閉檔案
		System.out.print("隨機賽入Filter\n");
	//---------資料選寫OK----------------//
	}
    public static ArrayList   Filter_read() throws IOException
	{
		 // 絕對路徑或相對路徑都可以，這裡是絕對路徑，寫入檔案時演示相對路徑
		File filename = new File(Name); // 要讀取以上路徑的input。txt檔案
		InputStreamReader reader = new InputStreamReader(
		new FileInputStream(filename)); // 建立一個輸入流物件reader
		BufferedReader br = new BufferedReader(reader); // 建立一個物件，它把檔案內容轉成計算機能讀懂的語言
		String line = "";
		ArrayList<String> check_x= new ArrayList<String>();
		while (line != null) {
		line = br.readLine(); // 一次讀入一行資料
		System.out.print(line+"//");
		check_x.add(line);
		}
		for(int i=0;i<check_x.size()-1;i++)
		{
			System.out.print("\n"+check_x.get(i)+"目前  size"+check_x.size());
		}
		return check_x;
	}
    
	public  static  int  Filte_size()
	{
		return Filte_size;
	}
}

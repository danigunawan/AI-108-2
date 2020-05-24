import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.xml.ws.Response;

public class Convolutions {

	static String Name ="Sizedata";
	public static  void main() throws Exception
	{
		System.out.println("\n\n"+"//--------------------Convolutions端----------------//");
		System.out.println("\n\n"+"//--------------------1.Filter端----------------//");
		ArrayList<String> check_x= new ArrayList<String>();
		check_x = FilterDepth.Filter_read();
		System.out.println("\n\n"+"//--------------------2.Readimage端----------------//");
		BufferedImage[] Readimage=readimage();
		Convolutions(Readimage[0],check_x);
	}
	//-------------------------------------------------------------------//
	public static  void Convolutions (BufferedImage bi,ArrayList check_x ) throws Exception
	{
	int  Cnt =	FilterDepth.Filte_size();
	System.out.print("\n");
	System.out.print(" size "+Cnt+" Filter  數   "+(check_x.size()-1));
	System.out.print("\n");
	System.out.print("\n");
	System.out.print("//-------------Filter-------------//\n");
	float [][] CNT_Filter = new  float[check_x.size()-1][Cnt];
    //-------------------------------------------------------------------//
	for(int j=0;j< check_x.size()-1;j++)
	{
		String A = (String) check_x.get(j);
		String[] strs=A.split(",");
		for(int i=0;i<strs.length;i++)
		{
		CNT_Filter[j][i] = Float.parseFloat(strs[i]);
		System.out.print(CNT_Filter[j][i]+"         ");
		}
		System.out.print("\n");
	}
	System.out.print("//-----------數字---OK--------------//\n");
   //--------------------------------------------------------------------//
        int x,y;
        int i,j;
		int H=bi.getHeight(), W=bi.getWidth(); 
	    int a1 ,a2,a3,a4,a5,a6,a7,a8;	
			float Pixel[][]=new float[ H ][W ];
			int gv = 0;
			for( y=0; y<W; y++)
			{	for(x=0; x<H; x++)
				{
				int rgb=bi.getRGB(x,y);
				int r=(rgb&0x00ff0000)>>16; //  取得紅色的資料
				int g=(rgb&0x0000ff00)>>8; //  取得綠色資料
				int b=rgb&0x000000ff; //  取得藍色資料
			     gv=(r+g+b)/3; //  計算灰階值
			     if(gv==255) {gv=1;}else {gv=0;};
			     Pixel[x][y]=gv;
				System.out.print("["+x+"]"+"["+y+"] "+gv+" pixel ");
				}
			    System.out.print("\n");
			}
	System.out.print("\n");
	System.out.print("//--------------------------總共"+H*W+"----------------------------//");
	System.out.print("\n");
			  int root= (int) Math.sqrt(CNT_Filter[0].length);
				 float [][] Filter = new  float[root][root];
				 Filter=CNN_filter_size.filter_to_size(CNT_Filter,0); 
				//------第幾個濾波器----------//
				 int cnt=1;
				 Pixel=CNN_filter_size.filter(Pixel, Filter,cnt);
				 /*
				    *  //------------Convolutions--------------//
					 * cnt = 1  k=9  3*3,
					 * cnt =2   k=25  5*5
					 * cnt =3   k= 49  7*7 
					 * 
					 */
				 Pixel=CNN_filter_size.Rectified_Linear_Unit(Pixel);
				 Pixel= CNN_filter_size.MAX(Pixel,-1,2,2);
				 /*   cnt -1   2*2
				  *    cnt  -2  3*3 
				  *    cnt -3    4*4 
				  */	 
	             System.out.print("//-----------存取區--------------//\n");	
	              //-----二維資料轉成一維---------//
	             String name = "test";
	             String name2 = "three";
                   	File_text( pixe_one(Pixel),name,name2) ;
                  System.out.print("資料存檔至 " +name + ".txt"+ " 資料類別 "+name2 );
                   	
	}
	//-------------------------主要---------------------------------//
	
	public static String[] pixe_one (float  Pixel [][])
	{
		String [] pixe_one = new String [Pixel.length+Pixel[0].length ]; 
		int M=0;
		for(int i = 0 ;i <Pixel.length;i++)
		{
			for(int j = 0 ;j <Pixel[0].length;j++)
			{
			
				pixe_one[M] =String.valueOf(Pixel[i][j]) ;
				M++;
			}
		}
		
		
		return pixe_one;
	}
	public static  float File_text (String  Pixel [] , String File_name ,String Name) throws IOException
	{
	
		File writename = new File(File_name+".txt"); // 相對路徑，如果沒有則要建立一個新的output。txt檔案
		writename.createNewFile(); // 建立新檔案
		BufferedWriter out = new BufferedWriter(new FileWriter(writename));
		for(int i =0 ; i<Pixel.length;i++)
		out.write(Pixel[i]+","); // \r\n即為換行
		out.write(Name);
		out.flush(); // 把快取區內容壓入檔案
		out.close(); // 最後記得關閉檔案
		return 0;
	}
	
	
	
public static Double   A (double[] b2 )
{
	System.out.println("\n----------方法1：-----------");
	Double max=b2[0];//預設第一個最大
	Double min=b2[0];//預設第一個最小
	for(int i=1;i<b2.length;i++)
	{
		if(b2[i]>max)
		{
			max=b2[i];//如果有比max大的數就讓max記錄下大的數
		}
		if(b2[i]<min)
		{
			min=b2[i];//如果有比min小的數就讓min記錄下小的數
		}
		
	}
	int maxlocation=0;//預設第一個最大
	int minlocation=0;//預設第一個
	for(int i=0;i<b2.length;i++)
	{
		if(b2[i]>b2[maxlocation])
		{
			maxlocation=i;//如果有比max大的數就讓max記錄下大的數
		}
		if(b2[i]<b2[minlocation])
		{
			minlocation=i;//如果有比min小的數就讓min記錄下小的數
		}
	}
	double  B=b2[maxlocation];
	return B;
}
	//---------------------------------------------------------------//
	public static BufferedImage [] readimage( ) throws IOException
	{
		File folder1 = new File(Name);
		String[] list1 = folder1.list();
		for (int i = 0; i < list1.length; i++) {   
			list1[i] =Name+"/"+list1[i];
		System.out.println("\n"+list1[i]);		
		}
		BufferedImage [] image = new BufferedImage[list1.length];
		for(int i =0 ;i<list1.length;i++)
		{
			image[i]=image (list1[i]);
		}
	    System.out.println("//-------------------3.讀取OK-----------------//");
		return image;
	}
	//-------------------------讀取檔案---------------------------------//
	public static BufferedImage image (String name) throws IOException
	{
		BufferedImage image;
	    image=ImageIO.read(new File(name));//讀取檔案       
		return image;
	}
    //-----------------------------------------------------------------//
	
	
	
	
	
}

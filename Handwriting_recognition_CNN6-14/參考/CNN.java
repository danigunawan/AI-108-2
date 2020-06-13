import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class CNN {
    //---------------data--size-------------------------//
	static String data_name="data";
	static String size_out_data_name="Sizedata"; 
	static int W=6; 
	static int H=6; 
	//-------Filter---------//
	static int size =9; //3 * 3 
    static int depth=1;
        //-------------------------------------------------//
		public static void main(String[] args) throws Exception {
		    //---------------data--size-------------------------//
		 data_size(data_name,size_out_data_name,W,H);
			//-------Filter---------//
		 FilterDepth.Filter(size,depth);		
	        //-------Convolutions----//
		 Convolutions.main();
		}
		//------------------建立調整main檔案----------------//
		public static  void data_size(String data_name , String size_out_data_name,int W,int H) throws IOException
		{
			String [] Name =data_name(data_name);
			for(int i =0; i<Name.length;i++)
			{
				BufferedImage image =LoadFile(Name[i]);	
				image=sizeImage(image,W,H);
				 Image(image,i,size_out_data_name);
			}
			System.out.print("-----------------建立檔案大小OK------------------------------\n\n\n\n");
		}
		//-------------------讀取檔案名稱-------------------//
		public static String [] data_name(String data) {
			File folder1 = new File(data);
			String[] list1 = folder1.list();
			for (int i = 0; i < list1.length; i++) {   
				list1[i] =data+"/"+list1[i];
				System.out.println(list1[i]);
			}
			//-----------------------------------//
			System.out.println("讀取OK");
			return list1;
		}
		//-------------------調整大小-----------------------//
		public static BufferedImage sizeImage(BufferedImage originalImage ,int W , int H){;
            
			      Image image = originalImage.getScaledInstance(W, H, 
			          Image.SCALE_SMOOTH); 
			      BufferedImage outputImage = new BufferedImage(W, H, 
			          BufferedImage.TYPE_INT_RGB); 
			      Graphics graphics = outputImage.getGraphics(); 
			      graphics.drawImage(image, 0, 0, null); 
			      graphics.dispose(); 
			      System.out.println("調整大小OK");
			    return outputImage;
				
		}
		//-------------------讀取data檔案-------------------//
	    public static  BufferedImage LoadFile(  String Filename ) throws IOException
	    {       BufferedImage image;
	             image=ImageIO.read(new File(Filename));//讀取檔案       
	             System.out.println("讀取初始化OK");
				
		return image;
	    }
	    //-------------------存取圖片-----------------------//
	     public static void  Image(BufferedImage Image,int i,String name) throws IOException {
	    	 int w= Image.getWidth();
	    	 int h=Image.getHeight();
	    	  BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_3BYTE_BGR);
	    	   Graphics g = bi.getGraphics();
	    	   try {
	               g.drawImage(Image, 0, 0, null);
	               ImageIO.write(bi,"jpg",new File(name+"/"+i+".JPG"));
	           } catch (IOException e) {
	               // TODO Auto-generated catch block
	        	   ImageIO.write(bi,"jpg",new File(name+"/"+0+".JPG"));
	               e.printStackTrace();
	           } 
	    	    System.out.println("存取OK");
	     }

	}


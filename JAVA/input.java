import java.awt.AWTException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.awt.image.Kernel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;

public class input {

	
	static  String   FileName = "Handwriting_recognition.jpg";
	static  String   size_FileName = "Handwriting_recognition_size.jpg";
	public static void main(String[] args) throws IOException, AWTException {
		BufferedImage Image,input_Image,input_Image2;
		BufferedImage image = red_Image(FileName); 
	    image = sizeImage(image,1000,500);
	    //-------------------------------------//
	    int rows = 10;
	    int cols = 5;
	    int chunks = rows * cols;
	    BufferedImage imgs[] = new BufferedImage[chunks];	
	    BufferedImage image2;
	   int count=0;
	    for(int x=0;x<1000;x=x+100)
	    	for(int y=0;y<500;y=y+100)
	    {
	    		{
	    			imgs[count]=BufferedImage_cut(image,90,90,x,y);		
	    			count++;
	    		}
	    }
	    //------------------------------------------------//
	    for(int i=0;i<count;i++)
	    {
	    	imgs[i] = sizeImage(imgs[i],100,100);//
	    	 imgs[i]=CUT_size(imgs[i],72,72,0,0);
	    	imgs[i] = sizeImage(imgs[i],7,7);
	    	write_Image(imgs[i],"C:\\Users\\user\\Desktop\\Handwriting_recognition_CNN\\image\\" + i );
	    }
	    //------------------------------------------------------------//
	  
		 image = red_Image("C:\\Users\\user\\Desktop\\Handwriting_recognition_CNN\\image\\1.jpg");
	    //----------------------------------------//
	    double [][]  input =  RGB_text(image);
	//-----------------------------------------------------//
	    Convolutional  Conv = new  Convolutional();
	    input =zero(input,0); // 可補也可不賭
	    double []Kernel= Convolutional.Kernel_size(2,2); // 2*2 
	    double  [][]  input_pixe= Convolutional.input_kernel(input); 
	    Kernel=Conv.Quick_sort(Kernel);  // Kernel 翻轉
        Conv.Pixe_txt2D(input);
	    System.out.print("input ="+input_pixe.length);

	   //-----------------------------------------------------------------------//
	    input_pixe=OR_pixe(input_pixe,Kernel);
	    max(input_pixe);
  	  
  	  
	  }
	
	public static void  max(double input_pixe[][])
	{
	
		   double[] myList = {1.9, 2.9, 3.4, 3.5,10,11,15,100,-1,-4.5}; //定義一維陣列
		    double num = myList[0]; //0為第一個陣列下標
		     for (int i = 0; i < myList.length; i++) {  //開始迴圈一維陣列
		       if (myList[i] > num) { //迴圈判斷陣列元素
		         num = myList[i]; } //賦值給num，然後再次迴圈
		     }
		     System.out.println("最大值為" + num); //跳出迴圈，輸出結果
	}
	
	
	
	
	
	
	
	
    public static double [] [] OR_pixe(double input[][],double  Kernel [])
    {
		int n=0;
	      for(int i =0;i<input.length;i++)
	      {
	    	  for(int j =0;j<input[0].length;j++)
	          {
	    		  input[i][j]=input[i][j]*Kernel[j];
	  			System.out.print(input[i][j]+"  ");
	  			n++;
	          }
	    	  System.out.print(" \n ");
	      }
	  	  System.out.print("總共 = "+n+" 筆");
    	return input;
    }
	
	
	
	
	
	
	
	
	
	
	public static  double sigmoid (double n)
	{
		double  out1 = (double) (1/(1+Math.exp(-1*n))); 
		return out1;
	}
	
	
public static  double[][] zero  (double pixe [][] ,int zero)
{
	int P=zero;
	double[][]  pixe_zero = new  double[pixe.length+ P][pixe[0].length+ P];
	for(int i= P;i<pixe.length;i++)
	{
		for( int j= P;j<pixe[0].length;j++)
		{
			
			pixe_zero[i][j] = pixe[i][j];

		}
		
	}


return pixe_zero;
}
	
	
	


	public static  double  degree(int degree)
	{
		int  degree_= 180;
		double AM =degree_/degree;
		return degree;
	}
public static   BufferedImage CUT_size (BufferedImage  input,int endX ,int endY , int startX,int startY)
	{
		 BufferedImage result = new BufferedImage(endX, endY, input.getType());
		  for (int y = startY; y < endY+startY; y++) {
		         for (int x = startX; x < endX+startX; x++) {
		            int rgb = input.getRGB(x, y);
		            result.setRGB(x- startX, y - startY, rgb);
		         }
		      }
		return result;
	}
public static  double[][] RGB_text(BufferedImage input) throws IOException
	{
	int pixe=0;
	double   [][] input_size = new double [input.getWidth()][input.getHeight()];
		for(int i=0; i<input.getWidth();i++)
		{
			for(int j=0;j<input.getHeight();j++)
			{
				 int rgb = input.getRGB(i, j);
				int r = (rgb & 0x00ff0000) >> 16; // 
				int g = (rgb & 0x0000ff00) >> 8; //
				int b = rgb & 0x000000ff; // 
				int gv = (r + g + b) / 3; //
				input_size[i][j]= gv/255;
			  	pixe++;
			}
			  System.out.print("\n");
		}		
		System.out.print("pixe = "+pixe);
		return input_size;
	}

public static BufferedImage sizeImage(BufferedImage input ,int W , int H){;
    BufferedImage outputImage = new BufferedImage(W,H, input.getType());
    Graphics2D g2d = outputImage.createGraphics();
    g2d.drawImage(input, 0, 0, W, H, null);
    g2d.dispose();; 
	return outputImage;
}
public static   BufferedImage BufferedImage_cut(BufferedImage image,int endX ,int endY ,int startX,int startY)
	{
	
		 BufferedImage result = new BufferedImage(endX, endY, image.getType());
	      for (int y = startY+15; y < endY+startY-10; y++) {
	         for (int x = startX+15; x < endX+startX-10; x++) {
	            int rgb = image.getRGB(x, y);
	            result.setRGB(x- startX-15, y - startY-15, rgb);
	         }
	      }
		 return result;
		
	}
public static void Image () throws IOException
{
	FileInputStream fis = new FileInputStream(FileName);
    BufferedImage image = ImageIO.read(fis);
    int rows = 5;
    int cols = 10;
    int chunks = rows * cols;
    int chunkWidth = image.getWidth() / cols;
    int chunkHeight = image.getHeight()/ rows;
    int count = 0;
    BufferedImage imgs[] = new BufferedImage[chunks];
    for (int x = 0; x < rows; x++) {
        for (int y = 0; y < cols; y++) {
            imgs[count] = new BufferedImage(chunkWidth, chunkHeight, image.getType());
            Graphics2D gr = imgs[count++].createGraphics();
            int cnt=chunkWidth * y;
            if(cnt!=0)
            {
            	cnt=chunkWidth * y;
            }
            gr.drawImage(image, 0, 0,
                    chunkWidth, chunkHeight,
                    cnt, chunkHeight * x,
                    chunkWidth * y + chunkWidth,
                    chunkHeight * x + chunkHeight, null);
            gr.dispose();
        }
    }

    // 鏉堟挸鍤亸蹇撴禈
    for (int i = 0; i < imgs.length; i++) {
        ImageIO.write(imgs[i], "jpg", new File("H:\\Handwriting_recognition_CNN\\image\\" + i + ".jpg"));
    }
}
public static BufferedImage cut(BufferedImage image,int x,int y ,int W ,int H) throws IOException
{
	 image = image.getSubimage(x, y, W, H);
	 return image;
}

public static  BufferedImage  red_Image(  String Filename ) throws IOException
    {       BufferedImage image;
             image=ImageIO.read(new File(Filename));     
	return image;
    }
public static void  write_Image(BufferedImage Image,String name) throws IOException {
	    	 int w= Image.getWidth();
	    	 int h=Image.getHeight();
	    	  BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_3BYTE_BGR);
	    	   Graphics g = bi.getGraphics();
	    	   try {
	               g.drawImage(Image, 0, 0, null);
	               ImageIO.write(bi,"jpg",new File(name+".jpg"));
	           } catch (IOException e) {
	               // TODO Auto-generated catch block
	        	   ImageIO.write(bi,"jpg",new File(name+".jpg"));
	               e.printStackTrace();
	           } 
	  }
	  //------------------------------------------------------------------------//   }

	  
	  
}

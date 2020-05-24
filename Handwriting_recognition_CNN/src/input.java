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
		//--影像裁切----OK ---//5-14
		BufferedImage image = red_Image(FileName); 
	    image = sizeImage(image,1000,500);
	    //---------------資料處理----------------------//
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
	    System.out.print("多少張="+count);
	    //------------------------------------------------//
	    for(int i=0;i<count;i++)
	    {
	    	imgs[i] = sizeImage(imgs[i],100,100);//
	    	 imgs[i]=CUT_size(imgs[i],72,72,0,0);
	    	imgs[i] = sizeImage(imgs[i],6,6);//784
	    	write_Image(imgs[i],"H:\\Handwriting_recognition_CNN\\image\\" + i );
	    }
	    //------------------------------------------------------------//
		 image = red_Image("H:\\Handwriting_recognition_CNN\\image\\1.jpg");
		 System.out.println("完成分割！");
	    //5-22 size 28*28 OK
	    //----------------------------------------//
	    double [][]  input =  RGB_text(image); //   784 ( pixe /3/255)灰階的圖片數值為0~255之間，我們將它縮放到0~1之間
	//-----------------------------------------------------//
	    double  [] kernel_size= Convolutional.Kernel_size(3,3);// 初始化權重 ANN
	    
	    double  [][]  input_pixe= Convolutional.input_kernel(input);
	 
	
	   
	   
	   
	    
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
				int gv = (r + g + b) / 3/255; //
				if(gv==1)
				{
					gv=0;
					
				}
				else
				{
					gv=1;	
				}
				input_size[i][j]= gv;
				rgb=(0xff000000|(gv<<16)|(gv<<8)|gv); 
				input.setRGB(i, j, rgb);
			    System.out.print(gv+"  ");
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
	System.out.println("調整大小OK");
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
            //设置小图的大小和类型
            imgs[count] = new BufferedImage(chunkWidth, chunkHeight, image.getType());
            //写入图像内容
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

    // 输出小图
    for (int i = 0; i < imgs.length; i++) {
        //ImageIO.write(imgs[i], "jpg", new File("C:\\img\\split\\img" + i + ".jpg"));
        ImageIO.write(imgs[i], "jpg", new File("H:\\Handwriting_recognition_CNN\\image\\" + i + ".jpg"));
    }
    System.out.println("完成分割！");
}
public static BufferedImage cut(BufferedImage image,int x,int y ,int W ,int H) throws IOException
{
	 image = image.getSubimage(x, y, W, H);
	//前两个值是答坐标位版置X、Y，后两个是长权和宽
	 return image;
}

public static  BufferedImage  red_Image(  String Filename ) throws IOException
    {       BufferedImage image;
             image=ImageIO.read(new File(Filename));//讀取檔案       
             System.out.println("讀取初始化OK");
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
	    	    System.out.println("存取OK");
	  }
	  //------------------------------------------------------------------------//   }

	  
	  
}

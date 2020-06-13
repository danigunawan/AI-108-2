import java.awt.List;
import java.util.ArrayList;

public class CNN_filter_size {

	
	
	
	public static  float [][] filter_to_size  (float [][]CNT_Filter,int cnt)
	{
		
	    int root= (int) Math.sqrt(CNT_Filter[0].length);
		 float [][] Filter = new  float[root][root];
    int pixel =0;

	System.out.print(" \n" );
	for(int i =0;i<root;i++)
	{
		for(int j =0;j<root;j++)
		{
			Filter[i][j]=CNT_Filter[cnt][pixel];
			System.out.print("filter_pixe["+i+"]"+"["+j+"]= "+Filter[i][j]+"   pixel = "+pixel+"   ");
          pixel++;
		}	
		System.out.print(" OK \n" );
	}
		return Filter;
	}
	public static float [][]  filter(float[][] Pixel,float [][]CNT_Filter , int cnt)
	{
		System.out.print("  \n" );
		//---------------k=9-------------//
		/*
		 * cnt = 1  k=9  3*3
		 * cnt =2   k=25  5*5
		 * cnt =3   k= 49  7*7 
		 * 
		 */
		float[][] pixel     = new  float [Pixel.length][Pixel[0].length] ;
        int Convolution_Kernel=0;
		int i,j;
		for( i=cnt;i<Pixel.length-cnt;i++)
		{
			for(j=cnt;j<Pixel[0].length-cnt;j++)
			{
				int cnt_Remake  = cnt*-1;
				System.out.print("\n");
				for (int x =cnt_Remake , Kernel_w=0;x<=cnt;x++,Kernel_w++)
				{
					for(int y =cnt_Remake,Kernel_h=0;y<=cnt;y++,Kernel_h++)
					{  
						
						
					//	System.out.print("   x="+x+"  y="+y +" pixel = "+Pixel[j+y][i+x] + "  Kernel_w = "+Kernel_w + " Kernel_h = "+Kernel_h );		
					//	System.out.print("   x="+x+"  y="+y +" pixel = "+Pixel[j+y][i+x]);
					System.out.print(" pixel = "+Pixel[j+y][i+x]);
						//System.out.print(" pixel = "+(int) Pixel[j+y][i+x] * CNT_Filter[Kernel_w][Kernel_h]); 
						Convolution_Kernel =Convolution_Kernel+(int) (Pixel[j+y][i+x] * CNT_Filter[Kernel_w][Kernel_h]);
						
					}
					   System.out.print("\n");
				} 
				 System.out.print("Convolution_Kernel = " +Convolution_Kernel );
				 pixel [i][j]=Convolution_Kernel;
				 Convolution_Kernel=0;
				 System.out.print("\n");
				
			}
			   System.out.print("\n");
			}
		System.out.print("//--------------------Convolutions--OK-------------------------//\n");
		for( i=cnt;i<pixel.length-cnt;i++)
		{
		for( j=cnt;j<pixel[0].length-cnt;j++)
		{
		System.out.print(" pixel = "+pixel[i][j] +"  ");
		}
	    System.out.print("\n");
		}
		
		return pixel;

	}
	
	public static  float [][]   Rectified_Linear_Unit (float [][] Pixel)
	{
		 System.out.print("\n");
		System.out.print("//----------------------Rectified_Linear_Unit-------------------------------//");
		 System.out.print("\n");
		int i,j;
		for( i=1;i<Pixel.length-1;i++)
		{
		for( j=1;j<Pixel[0].length-1;j++)
		{
			
			if(Pixel[i][j] <=0 )
			{
				Pixel[i][j]=0;
			}
			 System.out.print(" pixel = "+Pixel[i][j]);
		}
		 System.out.print("\n");
		}
		return Pixel;
	}
	public static float [][] MAX (float [][] Pixel , int cnt,int Kernel_size,int stride)
	{
		 System.out.print("\n");
		System.out.print("//----------------------Pooling Layer-------------------------------//");
		 System.out.print("\n");
		 //---------------cny-------------------//
		 
		 /*   cnt -1   2*2
		  *    cnt  -2  3*3 
		  *    cnt -3    4*4 
		  */
		 
int i;
int j;
float  [] Kernel = new  float[Kernel_size*Kernel_size];
int Cnt =0;
float Pooling=0;
  float  [] [] Step_count = new  float[(Pixel.length/stride)-1] [(Pixel[0].length/stride)-1] ;
   int cnt_W;
   int cnt_H;
		for( i=2,cnt_W=0;i<Pixel.length-1;i=i+2,cnt_W++)
		{
		for( j=2, cnt_H=0;j<Pixel[0].length-1;j=j+2,cnt_H++)
		{
			 for(int x =cnt;x<=0;x++)
			 {
				 for(int y =cnt;y<=0;y++)
				 {
					 
				//	System.out.print("   x="+x+"  y="+y +" pixel = "+Pixel[i+x][j+y]+"   Cnt =  "+Cnt);
					 System.out.print(" pixel = "+Pixel[i+x][j+y]);
					 Kernel[Cnt] = Pixel[i+x][j+y];
					 Cnt++;
				 }
				 System.out.print("\n");
			 }
			 Cnt=0;
			 Pooling=Kernel_max(Kernel);
			 System.out.print("\n"+"  Pooling  = "+Pooling+"\n\n" );		 
			 Step_count [cnt_W][cnt_H]= Pooling;
			} 
		 System.out.print("\n");
			
		}
		 System.out.print("\n");
		 Show_Pixel(Step_count);
		 //------------------------------------------------------------//
		return Step_count;
	}
	public static  void  Show_Pixel(float [][] Pixel)
	{
		 System.out.print("//---------Show_Pixel--------------//\n");
		for(int i =0 ; i<Pixel [0].length;i++)
		{
			for(int j =0 ; j<Pixel .length;j++)
				{
					
				 System.out.print("  Pixel  " + " ["+i+"]"+"["+j+"] = "+ Pixel[i][j]  );		 
			
				}
			 System.out.print("\n");
			}
		
	}
	public static  float  Kernel_max(float  [] a)
	{
		int max_value = 0;
		int max_ptr = 0;
		for (int i = 0 ; i < a.length ; i++) {
		  if (a[i] > max_value) {
		    max_value = (int) a[i];
		    max_ptr = i;
		  }
		}
		return max_value;
	}
	
}

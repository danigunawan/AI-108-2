import java.util.Random;

public class Convolutional { 
	//---------------------------------//
	static    double  ker_W;
	static    double  ker_H;
	public static double[] Kernel_size (int H,int W )
	{
		int size= H*W;
		ker_W=W;
		ker_H=H;
		double[]  kernelsize = new  double[size] ;
		int  [] kernelrandom= new int[size];
	    for(int i=0;i<kernelsize.length;i++)
	    {
	    	kernelrandom[i]=(int)(Math.random()*2);
	    	if(kernelrandom[i]==1)
	    	{
	    		kernelsize[i]= (double)(Math.random()*1/10);
	    		
	    	}
	    	else
	    	{	kernelsize[i]= (double)(Math.random()*-1/10);
	    		
	    	}
	    }
		return kernelsize;
	}
	//-------------------------------------------------------------//
    public static   double [][]input_kernel(double [][]input  )
    {
		int inputsize = input.length*input[0].length;
		//------------------------------------------------------------------//
		System.out.print(" \n \n");
		int CNT=0;	
		int   input_x=input.length-1;
		int  input_y =input[0].length-1;                                        
		double  [] [] size  = new  double  [inputsize-(input_x+input_x+input_y+input_y)]  [(int) (ker_W*ker_H)] ;
		System.out.print( "\n  size=" + size.length+"   "+size[0].length+"\n");
		int cnt;
		int W =-1;
	    int H =0;
		for(int i=1;i<input.length-1;i++)
		{
			for(int j=1;j<input[0].length-1;j++)
			{
				CNT++;
				cnt=0;
				for(int x=W;x<=H ;x++)
				{
					for(int y=W;y<=H ;y++)
					{
						cnt++;
					//	System.out.print("cnt = "+cnt );
						System.out.print(" pixel = "+input[i+x][j+y]);
						size[CNT-1][cnt-1]=input[i+x][j+y];
					}
					System.out.print("\n");
				}
				System.out.print("\n");
				
			}
		}
return size;
    }
    //------------------------------------------------------//
    //------------------------------------------------------------------------//
    
   public static  double [] array_pixe_1 (double [][] size,int x)
   {
	double []pixe= new double [size[0].length];
		for(int j=0;j<size[0].length;j++)
		{
			pixe[j]=size[x][j];
		}
	   return pixe;
	   
   }
 
    public static  double[]   Quick_sort(double [] input )
    {    
    double   [] SC =new double[input.length] ; 
    for( int i=0,j=0;i<input.length;i++,j++) {
    	SC [j] =input[input.length-1-i];
    	}
	return SC;   
    }
    
    
    
    
    
    
 public static void  Pixe_text1D(double  []array )
    {
		System.out.print("\n");
    	for(int i=0;i<array.length;i++)
    	{
    		System.out.print("  text =" +(double)array [i]);
    	}
    	System.out.print("\n");
    }
	public static void Pixe_txt2D(double [][]array)
	{
		System.out.print("\n");
		for(int i=0;i<array.length;i++)
		{
			int pixe =0;
			for(int j=0;j<array[0].length;j++)
			{
			
				System.out.print(" "+(int)array[i][j]);
				pixe++;
			}
			System.out.print("\n");
			}
		System.out.print("\n");
	}
	
	
	
	
}

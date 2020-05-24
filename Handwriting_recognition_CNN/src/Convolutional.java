import java.util.Random;

public class Convolutional { 
	
	
	public static double[] Kernel_size (int H,int W )
	{
		int size= H*W;
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
	static    double []  ker_array_K  ;
	
    public static   double [][]input_kernel(double [][]input )
    {
		int inputsize = input.length*input[0].length;
		System.out.print(" \n 確認 ="+inputsize+"\n");
		//------------------------------------------------------------------//
		System.out.print(" \n 濾波器 轉一維矩陣 \n");
		int CNT=0;	
		int   input_x=input.length-1;
		int  input_y =input[0].length-1;
		double  [] size  = new  double  [inputsize-(input_x+input_x+input_y+input_y)] ;
		System.out.print( "\n  size=" + size.length+"\n");
		 //---------------------------------------//
		for(int i=1;i<input.length-1;i++)
		{
			for(int j=1;j<input[0].length-1;j++)
			{
				CNT++;
				System.out.print("CNT = "+CNT +"\n");
				for(int x=-1;x<=1;x++)
				{
					for(int y=-1;y<=1;y++)
					{
						System.out.print(" pixel = "+input[i+x][j+y]);
						size[CNT-1]=input[i+x][j+y];
					}
					System.out.print("\n");
				}
				System.out.print("\n");
				
			}
		}
		//--------------------------------------------------------------//
	
		
		//---------------------------------------------------------//
		
		
		return input;
		
    }
	

	
	public static void Pixe_txt(double [][]array)
	{
		int pixe =0;
		for(int i=0;i<array.length;i++)
		{
			for(int j=0;j<array[0].length;j++)
			{
			
				System.out.print(" "+(int)array[i][j]);
				pixe++;
			}
			System.out.print("\n");
			}
		System.out.print("測試 = "+pixe);
	}
	
	
	
	
}

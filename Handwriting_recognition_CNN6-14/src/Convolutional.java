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
		for(int i=1;i<input.length-1;i++)
		{
			for(int j=1;j<input[0].length-1;j++)
			{
				CNT++;
				int cnt=0;
				for(int x=-1;x<=1;x++)
				{
					for(int y=-1;y<=1;y++)
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
 
    public static void DFT(String[] args) {

		//¹«Ê½    X[k]=(1/N)*series n=0N-1 x[n]*exp(-j*2p*n*k/N), k=0, ..., N-1
		 final double PI=3.14;
		 final double N=8000;
		double [] dft_out_re = new double [8000];
		double [] dft_out_im = new double [8000];
		double [] dft_one_re = new double [8000];
		double [] dft_one_im = new double [8000];
		double [] amp = new double [8000];
		// 輸入訊號 sin 0.6 100t/2    0.6 sin  1000t/2    
		int n,k;
		double s;
		for(k=0;k<N;k++) {
			for(n=0;n<N;n++) {			
				s=0.6*Math.sin(n*PI*50)+0.6*Math.sin(n*PI*500);
				dft_one_re[n]=s*Math.cos(2*PI/N*n*k);
				dft_one_im[n]=s*Math.sin(2*PI/N*n*k);
				dft_out_re[k]+=dft_one_re[n];
				dft_out_im[k]+=dft_one_im[n];
			}
			amp [k]= Math.sqrt(dft_out_re[k]*dft_out_re[k]+dft_out_im[k]*dft_out_im[k]);
			System.out.print(k+"  ");
			System.out.println(amp [k]);
    }
    }
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
    double   [] SC =new double[input.length] ; //一維排序 時間 O(1) 原地排序
    for( int i=0,j=0;i<input.length;i++,j++) {SC [j] =input[input.length-1-i]=input[i];}
	return SC;   
    }
 public static void  Pixe_text1D(double  []array )
    {
    	for(int i=0;i<array.length;i++)
    	{
    		System.out.print("  text =" +(double)array [i]);
    	}
    	System.out.print("\n");
    }
	public static void Pixe_txt2D(double [][]array)
	{
	
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

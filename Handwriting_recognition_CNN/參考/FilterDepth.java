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
		/* �g�JTxt�ɮ� */
		File writename = new File(Name); // �۹���|�A�p�G�S���h�n�إߤ@�ӷs��output�Ctxt�ɮ�
		writename.createNewFile(); // �إ߷s�ɮ�
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
				System.out.print("����"+i+"____________"+RAN[i]+"  size �� "+size+"�ثe�`��"+j+"\n");
			 int_String[i] = String.valueOf(RAN[i]);
			}
			for(int i = 0 ;i<RAN.length;i++)
			{
				System.out.print(int_String[i]);
				out.write(int_String[i]+","); // \r\n�Y������
			}
			out.write("\n");
	}
		out.flush(); // ��֨��Ϥ��e���J�ɮ�
		out.close(); // �̫�O�o�����ɮ�
		System.out.print("�H���ɤJFilter\n");
	//---------��ƿ�gOK----------------//
	}
    public static ArrayList   Filter_read() throws IOException
	{
		 // ������|�ά۹���|���i�H�A�o�̬O������|�A�g�J�ɮ׮ɺt�ܬ۹���|
		File filename = new File(Name); // �nŪ���H�W���|��input�Ctxt�ɮ�
		InputStreamReader reader = new InputStreamReader(
		new FileInputStream(filename)); // �إߤ@�ӿ�J�y����reader
		BufferedReader br = new BufferedReader(reader); // �إߤ@�Ӫ���A�����ɮפ��e�ন�p�����Ū�����y��
		String line = "";
		ArrayList<String> check_x= new ArrayList<String>();
		while (line != null) {
		line = br.readLine(); // �@��Ū�J�@����
		System.out.print(line+"//");
		check_x.add(line);
		}
		for(int i=0;i<check_x.size()-1;i++)
		{
			System.out.print("\n"+check_x.get(i)+"�ثe  size"+check_x.size());
		}
		return check_x;
	}
    
	public  static  int  Filte_size()
	{
		return Filte_size;
	}
}

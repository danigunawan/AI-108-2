import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class DataUtil {
    private ArrayList<ArrayList<Double>> alllist = new ArrayList<ArrayList<Double>>(); // 存放所有資料
    private ArrayList<String> outlist = new ArrayList<String>(); // 存放輸出資料，索引對應每個everylist的輸出
    private ArrayList<String> checklist = new ArrayList<String>();  //存放測試集的真實輸出字串
    private int in_num = 0;
    private int out_num = 0; // 輸入輸出資料的個數
    private int type_num = 0; // 輸出的類型數量
    private double[][] nom_data; //歸一化輸入資料中的最大值和最小值
    private int in_data_num = 0; //提前獲得輸入資料的個數
    // 獲取輸出類型的個數
    public int GetTypeNum() {
        return type_num;
    }
    // 設置輸出類型的個數
    public void SetTypeNum(int type_num) {
        this.type_num = type_num;
    }
    // 獲取輸入資料的個數
    public int GetInNum() {
        return in_num;
    }
    // 獲取輸出資料的個數
    public int GetOutNum() {
        return out_num;
    }
    // 獲取所有資料的陣列
    public ArrayList<ArrayList<Double>> GetList() {
        return alllist;
    }
    // 獲取輸出為字串形式的資料
    public ArrayList<String> GetOutList() {
        return outlist;
    }
    // 獲取輸出為字串形式的資料
    public ArrayList<String> GetCheckList() {
        return checklist;
    }

    //返回歸一化資料所需最大最小值
    public double[][] GetMaxMin(){

        return nom_data;
    }

    // 讀取檔初始化資料
    public void ReadFile(String filepath, String sep, int flag)
            throws Exception {

        ArrayList<Double> everylist = new ArrayList<Double>(); // 存放每一組輸入輸出資料
        int readflag = flag; // flag=0,train;flag=1,test
        String encoding = "GBK";
        File file = new File(filepath);
        if (file.isFile() && file.exists()) { // 判斷檔是否存在
            InputStreamReader read = new InputStreamReader(new FileInputStream(
                    file), encoding);// 考慮到編碼格式
            BufferedReader bufferedReader = new BufferedReader(read);
            String lineTxt = null;
            while ((lineTxt = bufferedReader.readLine()) != null) {
                int in_number = 0;
                String splits[] = lineTxt.split(sep); // 按','截取字串
                if (readflag == 0) {
                    for (int i = 0; i < splits.length; i++)
                        try {
                            everylist.add(Normalize(Double.valueOf(splits[i]),nom_data[i][0],nom_data[i][1]));
                            in_number++;
                        } catch (Exception e) {
                            if (!outlist.contains(splits[i]))
                                outlist.add(splits[i]); // 存放字串形式的輸出資料
                            for (int k = 0; k < type_num; k++) {
                                everylist.add(0.0);
                            }

                            everylist
                                    .set(in_number + outlist.indexOf(splits[i]),
                                            1.0);
                        }
                } else if (readflag == 1) {
                    for (int i = 0; i < splits.length; i++)
                        try {
                            everylist.add(Normalize(Double.valueOf(splits[i]),nom_data[i][0],nom_data[i][1]));
                            in_number++;
                        } catch (Exception e) {
                            checklist.add(splits[i]); // 存放字串形式的輸出資料
                        }
                }
                alllist.add(everylist); // 存放所有資料
                in_num = in_number;
                out_num = type_num;
                everylist = new ArrayList<Double>();
                everylist.clear();

            }
            bufferedReader.close();
        }
    }

    //向檔寫入分類結果
    public void WriteFile(String filepath, ArrayList<ArrayList<Double>> list, int in_number,  ArrayList<String> resultlist) throws IOException{
        File file = new File(filepath);
        FileWriter fw = null;
        BufferedWriter writer = null;
        try {
            fw = new FileWriter(file);
            writer = new BufferedWriter(fw);
            for(int i=0;i<list.size();i++){
                for(int j=0;j<in_number;j++)
                    writer.write(list.get(i).get(j)+",");
                writer.write(resultlist.get(i));
                writer.newLine();
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            writer.close();
            fw.close();
        }
    }


    //學習樣本歸一化,找到輸入樣本資料的最大值和最小值
    public void NormalizeData(String filepath) throws IOException{
        //提前獲得輸入資料的個數 
        GetBeforIn(filepath);
        int flag=1;
        nom_data = new double[in_data_num][2];
        String encoding = "GBK";
        File file = new File(filepath);
        if (file.isFile() && file.exists()) { // 判斷檔是否存在
            InputStreamReader read = new InputStreamReader(new FileInputStream(
                    file), encoding);// 考慮到編碼格式
            BufferedReader bufferedReader = new BufferedReader(read);
            String lineTxt = null;
            while ((lineTxt = bufferedReader.readLine()) != null) {
                String splits[] = lineTxt.split(","); // 按','截取字串
                for (int i = 0; i < splits.length-1; i++){
                    if(flag==1){
                        nom_data[i][0]=Double.valueOf(splits[i]);
                        nom_data[i][1]=Double.valueOf(splits[i]);
                    }
                    else{
                        if(Double.valueOf(splits[i])>nom_data[i][0])
                            nom_data[i][0]=Double.valueOf(splits[i]);
                        if(Double.valueOf(splits[i])<nom_data[i][1])
                            nom_data[i][1]=Double.valueOf(splits[i]);
                    }
                }
                flag=0;
            }
            bufferedReader.close();
        }
    }

    //歸一化前獲得輸入資料的個數
    public void GetBeforIn(String filepath) throws IOException{
        String encoding = "GBK";
        File file = new File(filepath);
        if (file.isFile() && file.exists()) { // 判斷檔是否存在
            InputStreamReader read = new InputStreamReader(new FileInputStream(
                    file), encoding);// 考慮到編碼格式
            //提前獲得輸入資料的個數
            BufferedReader beforeReader = new BufferedReader(read);
            String beforetext = beforeReader.readLine();
            String splits[] = beforetext.split(",");
            in_data_num = splits.length-1;
            beforeReader.close();
        }
    }

    //歸一化公式
    public double Normalize(double x, double max, double min){
        double y = 0.1+0.8*(x-min)/(max-min);
        return y;
    }
}

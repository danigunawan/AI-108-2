import java.io.IOException;
import java.util.ArrayList;

public class Test {

	public static void main() throws Exception {
        ArrayList<ArrayList<Double>> alllist = new ArrayList<ArrayList<Double>>(); // 存放所有資料
        ArrayList<String> outlist = new ArrayList<String>(); // 存放分類的字串
        int in_num = 0, out_num = 0; // 輸入輸出資料的個數
        DataUtil dataUtil = new DataUtil(); // 初始化數據
        dataUtil.NormalizeData("train.txt");
        dataUtil.SetTypeNum(4); // 設置輸出類型的數量
        dataUtil.ReadFile("train.txt", ",", 0);
        in_num = dataUtil.GetInNum(); // 獲得輸入資料的個數
        out_num = dataUtil.GetOutNum(); // 獲得輸出資料的個數(個數代表類型個數)
        alllist = dataUtil.GetList(); // 獲得初始化後的資料
        outlist = dataUtil.GetOutList();
        System.out.print("分類的類型：");
        for(int i =0 ;i<outlist.size();i++)
        System.out.print(outlist.get(i)+" ");
        System.out.println();
        System.out.println("訓練集的數量："+alllist.size());
        BPNN bpnn = new BPNN();
        // 訓練
        bpnn.Train(in_num, out_num, alllist);
        // 測試
        DataUtil testUtil = new DataUtil();
        testUtil.NormalizeData("test.txt");
        testUtil.SetTypeNum(3); // 設置輸出類型的數量
        testUtil.ReadFile("test.txt", ",", 1);
        ArrayList<ArrayList<Double>> testList = new ArrayList<ArrayList<Double>>();
        ArrayList<ArrayList<Double>> resultList = new ArrayList<ArrayList<Double>>();
        ArrayList<String> normallist = new ArrayList<String>(); // 存放測試集標準的輸出字串
        ArrayList<String> resultlist = new ArrayList<String>(); // 存放測試集計算後的輸出字串
        double right = 0; // 分類正確的數量
        int type_num = 0; // 類型的數量
        double all_num = 0; //測試集的數量
        type_num = outlist.size();
        testList = testUtil.GetList(); // 獲取測試資料
        normallist = testUtil.GetCheckList(); 
        int errorcount=0; // 分類錯誤的數量
        resultList = bpnn.ForeCast(testList); // 測試
        all_num=resultList.size();
        for (int i = 0; i < resultList.size(); i++) {
            String checkString = "unknow";
            for (int j = 0; j < type_num; j++) {
                if(resultList.get(i).get(j)==1.0){
                    checkString = outlist.get(j);
                    resultlist.add(checkString);
                }
                else{
                    resultlist.add(checkString);
                }
            }
            if(checkString.equals("unknow"))
                errorcount++;
            if(checkString.equals(normallist.get(i)))
                right++;
        }
        testUtil.WriteFile("result.txt",testList,in_num,resultlist);
        System.out.println("測試集的數量："+ (new Double(all_num)).intValue());
        System.out.println("分類正確的數量："+(new Double(right)).intValue());
        System.out.println("演算法的分類正確率為："+right/all_num);
        System.out.println("分類結果存儲在：result.txt");      
    }

}

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class BPNN {
	 // private static int LAYER = 3; // 銝惜蟡�雯頝�
    private static int NodeNum = 10;
    private static final int ADJUST = 5; 
    private static final int MaxTrain = 2000; 
    private static final double ACCU = 0.015; 
    private double ETA_W = 0.5; 
    private double ETA_T = 0.5;
    private double accu;

    private int in_num; 
    private int hd_num; 
    private int out_num;
    private ArrayList<ArrayList<Double>> list = new ArrayList<>(); 

    private double[][] in_hd_weight; // BP蝬脰楝in-hidden蝒孛甈��
    private double[][] hd_out_weight; // BP蝬脰楝hidden_out蝒孛甈��
    private double[] in_hd_th; // BP蝬脰楝in-hidden���
    private double[] hd_out_th; // BP蝬脰楝hidden-out���

    private double[][] out; // 瘥������潛����頧���撓��潘�撓�撅文停�����
    private double[][] delta; // delta摮貊���葉����
    public int GetMaxNum() {
        return Math.max(Math.max(in_num, hd_num), out_num);
    }

    // 閮剔蔭甈�澆飛蝧��
    public void SetEtaW() {
        ETA_W = 0.5;
    }

    // 閮剔蔭��澆飛蝧��
    public void SetEtaT() {
        ETA_T = 0.5;
    }

    // BPNN閮毀
    public void Train(int in_number, int out_number,
            ArrayList<ArrayList<Double>> arraylist) throws IOException {
        list = arraylist;
        in_num = in_number;
        out_num = out_number;

        GetNums(in_num, out_num); // ���撓�撅扎�撅扎�撓�撅斤��暺
        // SetEtaW(); // 閮剔蔭摮貊���
        // SetEtaT();

        InitNetWork(); 
        int datanum = list.size(); 
        int createsize = GetMaxNum(); 
        out = new double[3][createsize];

        for (int iter = 0; iter < MaxTrain; iter++) {
            for (int cnd = 0; cnd < datanum; cnd++) {
                for (int i = 0; i < in_num; i++) {
                    out[0][i] = list.get(cnd).get(i); 
                }
                Forward();
                Backward(cnd);

            }
            System.out.println("This is the " + (iter + 1) + " th trainning NetWork !");
            accu = GetAccu();
            System.out.println("All Samples Accuracy is " + accu);
            if (accu < ACCU)
                break;
        }
    }
    public void GetNums(int in_number, int out_number) {
        in_num = in_number;
        out_num = out_number;
        hd_num = (int) Math.sqrt(in_num + out_num) + ADJUST;
        if (hd_num > NodeNum)
            hd_num = NodeNum; // �撅斤�暺銝憭扳��憭抒�暺
    }

    // ����雯頝舐���澆���
    public void InitNetWork() {
        // ������甈⊥��潮��,蝭�-0.5-0.5銋��
        //in_hd_last = new double[in_num][hd_num];
        //hd_out_last = new double[hd_num][out_num];

        in_hd_weight = new double[in_num][hd_num];
        for (int i = 0; i < in_num; i++)
            for (int j = 0; j < hd_num; j++) {
                int flag = 1; // 蝚西������(-1����1)
                if ((new Random().nextInt(2)) == 1)
                    flag = 1;
                else
                    flag = -1;
                in_hd_weight[i][j] = (new Random().nextDouble() / 2) * flag; // ����n-hidden�����
            }

        hd_out_weight = new double[hd_num][out_num];
        for (int i = 0; i < hd_num; i++)
            for (int j = 0; j < out_num; j++) {
                int flag = 1; 
                if ((new Random().nextInt(2)) == 1)
                    flag = 1;
                else
                    flag = -1;
                hd_out_weight[i][j] = (new Random().nextDouble() / 2) * flag; 
            }
        in_hd_th = new double[hd_num];
        for (int k = 0; k < hd_num; k++)
            in_hd_th[k] = 0;

        hd_out_th = new double[out_num];
        for (int k = 0; k < out_num; k++)
            hd_out_th[k] = 0;
    }
    public double GetError(int cnd) {
        double ans = 0;
        for (int i = 0; i < out_num; i++)
            ans += 0.5 * (out[2][i] - list.get(cnd).get(in_num + i))
                    * (out[2][i] - list.get(cnd).get(in_num + i));
        return ans;
    }
    public double GetAccu() {
        double ans = 0;
        int num = list.size();
        for (int i = 0; i < num; i++) {
            int m = in_num;
            for (int j = 0; j < m; j++)
                out[0][j] = list.get(i).get(j);
            Forward();
            int n = out_num;
            for (int k = 0; k < n; k++)
                ans += 0.5 * (list.get(i).get(in_num + k) - out[2][k])
                        * (list.get(i).get(in_num + k) - out[2][k]);
        }
        return ans / num;
    }
    public void Forward() {
        for (int j = 0; j < hd_num; j++) {
            double v = 0;
            for (int i = 0; i < in_num; i++)
                v += in_hd_weight[i][j] * out[0][i];
            v += in_hd_th[j];
            out[1][j] = Sigmoid(v);
        }
        for (int j = 0; j < out_num; j++) {
            double v = 0;
            for (int i = 0; i < hd_num; i++)
                v += hd_out_weight[i][j] * out[1][i];
            v += hd_out_th[j];
            out[2][j] = Sigmoid(v);
        }
    }
    public void Backward(int cnd) {
        CalcDelta(cnd);
        UpdateNetWork();
    }
    public void CalcDelta(int cnd) {

        int createsize = GetMaxNum(); 
        delta = new double[3][createsize];
        for (int i = 0; i < out_num; i++) {
            delta[2][i] = (list.get(cnd).get(in_num + i) - out[2][i])
                    * SigmoidDerivative(out[2][i]);
        }
        for (int i = 0; i < hd_num; i++) {
            double t = 0;
            for (int j = 0; j < out_num; j++)
                t += hd_out_weight[i][j] * delta[2][j];
            delta[1][i] = t * SigmoidDerivative(out[1][i]);
        }
    }
    public void UpdateNetWork() {

        for (int i = 0; i < hd_num; i++) {
            for (int j = 0; j < out_num; j++) {
                hd_out_weight[i][j] += ETA_W * delta[2][j] * out[1][i]; // �����澆����
                /* ����� * hd_out_weight[i][j] += (ETA_A * hd_out_last[i][j] + ETA_W * delta[2][j] * out[1][i]); hd_out_last[i][j] = ETA_A * * hd_out_last[i][j] + ETA_W delta[2][j] * out[1][i]; */
            }

        }
        for (int i = 0; i < out_num; i++)
            hd_out_th[i] += ETA_T * delta[2][i];

        // 頛詨撅文��撅支����澆��潸矽�
        for (int i = 0; i < in_num; i++) {
            for (int j = 0; j < hd_num; j++) {
                in_hd_weight[i][j] += ETA_W * delta[1][j] * out[0][i]; // �����澆����
                /* ����� * in_hd_weight[i][j] += (ETA_A * in_hd_last[i][j] + ETA_W * delta[1][j] * out[0][i]); in_hd_last[i][j] = ETA_A * * in_hd_last[i][j] + ETA_W delta[1][j] * out[0][i]; */
            }
        }
        for (int i = 0; i < hd_num; i++)
            in_hd_th[i] += ETA_T * delta[1][i];
    }

    public int Sign(double x) {
        if (x > 0)
            return 1;
        else if (x < 0)
            return -1;
        else
            return 0;
    }


    public double Maximum(double x, double y) {
        if (x >= y)
            return x;
        else
            return y;
    }
    public double Minimum(double x, double y) {
        if (x <= y)
            return x;
        else
            return y;
    }
    public double Sigmoid(double x) {
        return (double) (1 / (1 + Math.exp(-x)));
    }
    public double SigmoidDerivative(double y) {
        return (double) (y * (1 - y));
    }

    public double TSigmoid(double x) {
        return (double) ((1 - Math.exp(-x)) / (1 + Math.exp(-x)));
    }
    public double TSigmoidDerivative(double y) {
        return (double) (1 - (y * y));
    }
    public ArrayList<ArrayList<Double>> ForeCast(
            ArrayList<ArrayList<Double>> arraylist) {

        ArrayList<ArrayList<Double>> alloutlist = new ArrayList<>();
        ArrayList<Double> outlist = new ArrayList<Double>();
        int datanum = arraylist.size();
        for (int cnd = 0; cnd < datanum; cnd++) {
            for (int i = 0; i < in_num; i++)
                out[0][i] = arraylist.get(cnd).get(i); // �頛詨蝭�暺釵��
            Forward();
            for (int i = 0; i < out_num; i++) {
                if (out[2][i] > 0 && out[2][i] < 0.5)
                    out[2][i] = 0;
                else if (out[2][i] > 0.5 && out[2][i] < 1) {
                    out[2][i] = 1;
                }
                outlist.add(out[2][i]);
            }
            alloutlist.add(outlist);
            outlist = new ArrayList<Double>();
            outlist.clear();
        }
        return alloutlist;
    }


	
}

package com.cxgc.sql_statistic;


import com.cxgc.sql_statistic.ByteCompile;

import java.util.Base64;

import static java.lang.StrictMath.atan;
import static java.lang.StrictMath.exp;
import static java.lang.StrictMath.log;
import static java.lang.StrictMath.tan;

public class Miller_projection {

    class calcMaterial {
        public double xStart;
        public double yStart;
        public double xEnd;
        public double yEnd;
        public double longitudeA;
        public double longitudeB;
        public double latitudeA;
        public double latitudeB;
    }

    public static double M_PI = 3.14159265358979323846;
    double a, b;
    double[] test = {0.0, 0.0};
    double[] tet = {0.0, 0.0};

    public static double[] MillierConvertion(double lon, double lat)//如：116.40741300000002（longitude)
    // ,39.904214(latitude)
    {
        double L = 6381372 * M_PI * 2;//地球周长
        double W = L;// 平面展开后，x轴等于周长
        double H = L / 2;// y轴约等于周长一半
        double mill = 2.3;// 米勒投影中的一个常数，范围大约在正负2.3之间
        double x = lon * M_PI / 180;// 将经度从度数转换为弧度
        double y = lat * M_PI / 180;// 将纬度从度数转换为弧度
        y = 1.25 * log(tan(0.25 * M_PI + 0.4 * y));// 米勒投影的转换
        // 弧度转为实际距离
        x = (W / 2) + (W / (2 * M_PI)) * x;
        y = (H / 2) - (H / (2 * mill)) * y * (-1);
        double[] result = new double[2];
        result[0] = x;
        result[1] = y;
        return result;
    }

    public double[] MillierConvertion1(double x, double y) {
        double L = 6381372 * M_PI * 2;//地球周长
        double W = L;// 平面展开后，x轴等于周长
        double H = L / 2;// y轴约等于周长一半
        double mill = 2.3;// 米勒投影中的一个常数，范围大约在正负2.3之间
        double lat;
        lat = ((H / 2 - y) * 2 * mill) / (1.25 * H);
        lat = ((atan(exp(lat)) - 0.25 * M_PI) * 180) / (0.4 * M_PI);
        double lon;
        lon = (x - W / 2) * 360 / W;
        double[] result = new double[2];
        result[0] = lon;
        result[1] = lat;
        return result;
    }

    public static int latlng2Angle(String positionStart, String positionStop) {

        String haha = "LLFOkVV/7oM=";//结束点
        byte[] gaga1 = Base64.getDecoder().decode(positionStart);//初始点经纬度
        byte[] haha1 = Base64.getDecoder().decode(positionStop);//结束点经纬度
        String gaga = "LLFOjVV/7q8=";//初始点


        double lat1 = ByteCompile.byte2Int(haha1[0], haha1[1], haha1[2], haha1[3]
        ) * 90.0 / Integer.MAX_VALUE;
        double long1 = ByteCompile.byte2Int(haha1[4], haha1[5], haha1[6], haha1[7]
        ) * 180.0 / Integer.MAX_VALUE;

        double lat2 = ByteCompile.byte2Int(gaga1[0], gaga1[1], gaga1[2], gaga1[3]
        ) * 90.0 / Integer.MAX_VALUE;
        double long2 = ByteCompile.byte2Int(gaga1[4], gaga1[5], gaga1[6], gaga1[7]
        ) * 180.0 / Integer.MAX_VALUE;

        return 0;

    }

    public static void main(String args[]) {

      /*  a = 0;
        b = 0;
        tet = MillierConvertion(a, b);
        cout << tet[0] << endl << tet[1];
        cout << endl;
        test = MillierConvertion1(tet[0], tet[1]);
        cout << test[0] << endl << test[1];*/


        String haha = "LLFOsVV/5KQ=";//结束点
        byte[] haha1 = Base64.getDecoder().decode(haha);
        String gaga = "LLFOJlV/368=";//初始点
        byte[] gaga1 = Base64.getDecoder().decode(gaga);


        double lat1 = ByteCompile.byte2Int(haha1[0], haha1[1], haha1[2], haha1[3]
        ) * 90.0 / Integer.MAX_VALUE;
        double long1 = ByteCompile.byte2Int(haha1[4], haha1[5], haha1[6], haha1[7]
        ) * 180.0 / Integer.MAX_VALUE;

        double lat2 = ByteCompile.byte2Int(gaga1[0], gaga1[1], gaga1[2], gaga1[3]
        ) * 90.0 / Integer.MAX_VALUE;
        double long2 = ByteCompile.byte2Int(gaga1[4], gaga1[5], gaga1[6], gaga1[7]
        ) * 180.0 / Integer.MAX_VALUE;


        //120.37904,31.342478起始点，左侧
        //120.378697,31.342487重点，右侧
        double[] materialA = MillierConvertion(long1, lat1);//结束点
        double[] materialB = MillierConvertion(long2, lat2);
/*        double[] materialB = MillierConvertion(120.619246,31.302163);//起点
        double[] materialA = MillierConvertion(123.619246,41.38225);//终点*/
        double deltaY = materialA[1] - materialB[1];
        double deltaX = materialA[0] - materialB[0];
        double deltaDistance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);



        //360*Math.atan2(diff_y, diff_x)/(2*Math.PI)+90;//不该加90
        double angle = 180 * Math.atan2(deltaY, deltaX) / Math.PI - 90;//加90是为了是角度0值从正北轴算起
        int resultangle = (int) angle;
    }

    /**
     *
     * @param GPSStart 起始点经纬度
     * @param GPSStop 终止点经纬度
     * @return
     */
    public static float getDeltaDistance(String GPSStart, String GPSStop){
        //String GPSStop = "LLFVV1V/6t4=";//结束点
        byte[] GPSStop1 = Base64.getDecoder().decode(GPSStop);
        //String GPSStart = "LLFM6FV/6oM=";//初始点
        byte[] GPSStart1 = Base64.getDecoder().decode(GPSStart);


        double lat1 = ByteCompile.byte2Int(GPSStop1[0], GPSStop1[1], GPSStop1[2], GPSStop1[3]
        ) * 90.0 / Integer.MAX_VALUE;
        double long1 = ByteCompile.byte2Int(GPSStop1[4], GPSStop1[5], GPSStop1[6], GPSStop1[7]
        ) * 180.0 / Integer.MAX_VALUE;

        double lat2 = ByteCompile.byte2Int(GPSStart1[0], GPSStart1[1], GPSStart1[2], GPSStart1[3]
        ) * 90.0 / Integer.MAX_VALUE;
        double long2 = ByteCompile.byte2Int(GPSStart1[4], GPSStart1[5], GPSStart1[6], GPSStart1[7]
        ) * 180.0 / Integer.MAX_VALUE;


        //120.37904,31.342478起始点，左侧
        //120.378697,31.342487重点，右侧
        double[] materialA = MillierConvertion(long1, lat1);//结束点
        double[] materialB = MillierConvertion(long2, lat2);

        double deltaY = materialA[1] - materialB[1];
        double deltaX = materialA[0] - materialB[0];
        float deltaDistance = (float)Math.sqrt(deltaX * deltaX + deltaY * deltaY);

        return deltaDistance;
    }

    public static int convert(String GPSStart, String GPSEnd){
        String gaga = "LLFOjVV/7q8=";//初始点
        byte[] gaga1 = Base64.getDecoder().decode(GPSStart);
        String haha = "LLFOkVV/7oM=";//结束点
        byte[] haha1 = Base64.getDecoder().decode(GPSEnd);



        double lat1 = ByteCompile.byte2Int(haha1[0], haha1[1], haha1[2], haha1[3]
        ) * 90.0 / Integer.MAX_VALUE;
        double long1 = ByteCompile.byte2Int(haha1[4], haha1[5], haha1[6], haha1[7]
        ) * 180.0 / Integer.MAX_VALUE;

        double lat2 = ByteCompile.byte2Int(gaga1[0], gaga1[1], gaga1[2], gaga1[3]
        ) * 90.0 / Integer.MAX_VALUE;
        double long2 = ByteCompile.byte2Int(gaga1[4], gaga1[5], gaga1[6], gaga1[7]
        ) * 180.0 / Integer.MAX_VALUE;


        //120.37904,31.342478起始点，左侧
        //120.378697,31.342487重点，右侧
        double[] materialA = MillierConvertion(long1, lat1);//结束点
        double[] materialB = MillierConvertion(long2, lat2);
/*        double[] materialB = MillierConvertion(120.619246,31.302163);//起点
        double[] materialA = MillierConvertion(123.619246,41.38225);//终点*/
        double deltaY = materialA[1] - materialB[1];
        double deltaX = materialA[0] - materialB[0];
        //360*Math.atan2(diff_y, diff_x)/(2*Math.PI)+90;//不该加90
        double angle = 180 * Math.atan2(deltaY, deltaX) / Math.PI - 90;//加90是为了是角度0值从正北轴算起
        int resultangle = (int) angle;

        return resultangle;
    }
}

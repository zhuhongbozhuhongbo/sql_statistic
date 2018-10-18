package com.cxgc.sql_statistic;

import com.cxgc.database.dao.RIDao;
import com.cxgc.database.model.RailInformation;

import java.util.ArrayList;
import java.util.List;

public class RailHandler {
    public static void main(String[] args){

        List point = new ArrayList<PositionLatLng>();
          /*  point.add(new PositionLatLng(120.302053, 31.583121));//无锡
            point.add(new PositionLatLng(118.78594, 32.054551));//南京
               point.add(new PositionLatLng(118.511281, 31.667314));//马鞍山
               point.add(new PositionLatLng(118.359122, 31.321643));//芜湖
               point.add(new PositionLatLng(117.798819, 30.917071));//铜陵
               point.add(new PositionLatLng(118.721671, 30.95491));//宣城
               point.add(new PositionLatLng(120.182853, 30.25518));//杭州
               point.add(new PositionLatLng(120.072989, 30.879507));//湖州
               point.add(new PositionLatLng(120.611319, 31.331027));//苏州*/
        point.add(new PositionLatLng(134.651356,46.680174));
          point.add(new PositionLatLng(118.831043,45.213599));
          point.add(new PositionLatLng(95.276356,27.059879));


        for(int i = 0; i < point.size(); i++){
            RailInformation railInformation = new RailInformation(3, "太湖隧道项目",  6 - i,
                    ( (PositionLatLng)point.get(i) ).getLongitude(), ( (PositionLatLng)point.get(i) ).getLatitude()
            );
            try {
                new RIDao().add(railInformation);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        point.clear();
        System.out.println("point size:" + point.size());
        List<RailInformation> rail = new ArrayList<RailInformation>();

        try {
            rail = new RIDao().findByRailId(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    /*    for(int i = 0; i < rail.size(); i++){
            if(i == rail.get(i).getOrderNum())
            PositionLatLng positionLatLng = new PositionLatLng(rail.get(i).getLng(), rail.get(i).getLat());
            point.add(positionLatLng);
            System.out.println("orderNum: " + rail.get(i).getOrderNum());
        }*/

    }

}

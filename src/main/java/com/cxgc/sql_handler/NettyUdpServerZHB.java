package com.cxgc.sql_handler;

/*
import com.cxgc.database.dao.DDIDao;
import com.cxgc.database.dao.DSIDao;
import com.cxgc.database.dao.DaoUtil;
import com.cxgc.database.dao.IIDao;
import com.cxgc.database.dao.SIDao;
import com.cxgc.database.model.DeviceDynamicInformation;
import com.cxgc.database.model.InspectorInformation;
import com.cxgc.utils.Miller_projection;
import com.cxgc.utils.checkInterval;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.util.CharsetUtil;
*/

public class NettyUdpServerZHB {
}
//    //private static Map<String,DataSummarizing> dataSummarizing4MapMap = new HashMap();
//    private static Map<String, DataSummarizeZHB> dataSummarizeZHBMap = new HashMap<>();
//    private static Map<String, DeviceDynamicInformation> cacheDevDynaInfo = new HashMap<>();
//    //private static int t = 0;
//
//
//    public static void main(String args[]) {
//
//        //以下为重定向输出到日志
//        try {
//            PrintStream mytxt = new PrintStream("D:/log.txt");
//            PrintStream out = System.out;
//
//            System.setOut(mytxt);//将输出重定向为./log.txt
//
//        }catch(FileNotFoundException e){
//            e.printStackTrace();
//        }
//
//        Bootstrap bootstrap = new Bootstrap();
//        bootstrap.group(new NioEventLoopGroup()).channel(NioDatagramChannel.class).handler(
//                new SimpleChannelInboundHandler<DatagramPacket>() {
//                    @Override
//                    public void channelRead0(ChannelHandlerContext ctx, DatagramPacket packet) throws Exception {
//                        byte[] command = new byte[1024];
//                        packet.content().getBytes(0, command);
//
//                        ctx.writeAndFlush(new DatagramPacket(Unpooled.
//                                copiedBuffer(new String(new byte[]{command[iotConstant.messageIdInitialPosition]}), CharsetUtil.UTF_8), packet.sender()));
//                        //System.out.println("收到数据");
//                        getDDInfo(command);
//
//
//                    }
//                }
//        );
//
//        ChannelFuture future = bootstrap.bind(new InetSocketAddress(8802));
//        future.addListener(new ChannelFutureListener() {
//            @Override
//            public void operationComplete(ChannelFuture future) throws Exception {
//                if (future.isSuccess()) {
//                    System.out.println("Channel bound");
//                } else {
//                    System.out.println("Bind attempt failed");
//                    future.cause().printStackTrace();
//                }
//            }
//        });
//    }
//
//    private static void getDDInfo(byte[] command) {
//
///*
//        System.out.println("data: " + new Date());
//        for (int i = 0; i < 100000; i++) {
//            for (int j = 0; j < 100000; j++) {
//                for (int k = 0; k < 100000; k++) {
//                    //for(int m = 0; m < 10; m++){}
//                }
//            }
//        }
//        System.out.println("data: " + new Date());
//*/
//
//
//        //judgingFuel用于存储6个containingFuel,之后输入到 函数判断，若均为0.0，代表下位机传感器故障
//        float[] judgingFuel = new float[6];
//        float latestFuel = 0.0f;
//
//
//        Date basicDate = DaoUtil.dateCombination(DaoUtil.strToDate(ByteCompile.byte2Date(command[iotConstant.yearInitialPosition],
//                command[iotConstant.monthInitialPosition], command[iotConstant.dayInitialPosition])),
//                DaoUtil.strToTime(ByteCompile.byte2Time(command[iotConstant.hourInitialPosition], command[iotConstant.minuteInitialPosition],
//                        command[iotConstant.secondInitialPosition])));
//
//        String deviceId = ByteCompile.byte2Id(command[iotConstant.iotDeviceIdInitialPosition], command[iotConstant.iotDeviceIdInitialPosition + 1],
//                command[iotConstant.iotDeviceIdInitialPosition + 2], command[iotConstant.iotDeviceIdInitialPosition + 3],
//                command[iotConstant.iotDeviceIdInitialPosition + 4], command[iotConstant.iotDeviceIdInitialPosition + 5]
//        );
//
//        try {
//            /**优化空间**/
//            List<DeviceDynamicInformation> ddiList = new DDIDao().findLatest(deviceId);
//
//            if (!ddiList.isEmpty()) {
//                latestFuel = ddiList.get(0).getContainingFuel();
//            } else {
//                //    deltaDistanceSum = 0;
//
//                //若数据库无ddi数据，将数据包第一条数据作为latestFuel
//                latestFuel = ((float) (command[iotConstant.containingFuelInitialPosition] & 0xff)) / 255;
//                latestFuel = Float.valueOf(CommonUtils.df.format(latestFuel));
//            }
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//
//        String GPSInformation = "";
//        String GPSInfoStart = "";
//        String GPSInfoEnd = "";//用不到，执行完下面的for循环后GPSInformation就是GPSInfoEnd
//
//
//        for (int i = 0; i < CommonUtils.packageContainingElement; i++) {
//            float deltaFuel = 0.0f;
//
//            float containingFuel = ((float) (command[iotConstant.containingFuelInitialPosition + i] & 0xff)) / 255;
//            containingFuel = Float.valueOf(CommonUtils.df.format(containingFuel));
//            judgingFuel[i] = containingFuel;
//
//
//            deltaFuel = containingFuel - latestFuel;
//            float containingElectricity = ((float) (command[iotConstant.containingElectricityInitialPosition + i] & 0xff)) / 255;
//
//            //除以10得到单位是m,再除以10得到单位是km
//            float deltaDistance = (float) (((command[iotConstant.deltaDistanceInitialPosition + i * 2] & 0xff) << 8) |
//                    ((command[iotConstant.deltaDistanceInitialPosition + 1 + i * 2] & 0xff) << 0)) / 10 / 1000;
//            System.out.println("id: " + deviceId);
//            System.out.println("deltaDistance: " + deltaDistance);
//            System.out.println("GPSInformation: " + GPSInformation);
//            System.out.println("");
//
//
//            int rotationOrientation = ByteCompile.getRotationOrientation(command[iotConstant.rotationOrientationInitialPosition + i]);
//
//            boolean workingFlag;
//            if ((float) command[iotConstant.currentSpeedInitialPosition + i] > iotConstant.speedLimit) {
//                workingFlag = true;
//            } else {
//                workingFlag = false;
//            }
//
//            GPSInformation = ByteCompile.byte2GPS(
//                    command[iotConstant.GPSInformationInitialPosition + 0 + i * 8], command[iotConstant.GPSInformationInitialPosition + 1 + i * 8],
//                    command[iotConstant.GPSInformationInitialPosition + 2 + i * 8], command[iotConstant.GPSInformationInitialPosition + 3 + i * 8],
//                    command[iotConstant.GPSInformationInitialPosition + 4 + i * 8], command[iotConstant.GPSInformationInitialPosition + 5 + i * 8],
//                    command[iotConstant.GPSInformationInitialPosition + 6 + i * 8], command[iotConstant.GPSInformationInitialPosition + 7 + i * 8]
//            );
//
//
//
//
//            //DaoUtil.strToDate(new java.sql.Date(basicDate.getTime()).toString());
//            //DaoUtil.strToTime(new java.sql.Time(basicDate.getTime()).toString());
//
//            Calendar calendar = Calendar.getInstance();
//            calendar.setTime(basicDate);
//
//            //检查时间间隔
//            calendar.add(Calendar.SECOND, i * checkInterval.checkIntervalByID(deviceId));
//            Date ddiDate = calendar.getTime();
//            Time st = new Time(ddiDate.getTime());
//            java.sql.Date sd = new java.sql.Date(ddiDate.getTime());
//
//            addData(command[iotConstant.angleXInitialPosition + i], command[iotConstant.angleYInitialPosition + i], command[iotConstant.angleZInitialPosition + i],
//                    command[iotConstant.angularSpeedXInitialPosition + i], command[iotConstant.angularSpeedYInitialPosition + i], command[iotConstant.angularSpeedZInitialPosition + i],
//                    command[iotConstant.currentSpeedInitialPosition + i], command[iotConstant.xAccelerationInitialPosition + i], command[iotConstant.yAccelerationInitialPosition + i],
//                    command[iotConstant.zAccelerationInitialPosition + i], containingFuel, containingElectricity,
//                    sd, st, rotationOrientation, workingFlag, deviceId, GPSInformation, deltaDistance, deltaFuel
//            );
//
//            mapCheck(deviceId);
//            //目前使用每个数据包的六条数据触发六次updateDataSummarizingMaterials，导致六次插入数据库，后期考虑下是否有优化的可能
//            DataMaterialZHB dataMaterialZHB = new DataMaterialZHB(sd, st, deviceId, workingFlag, deltaDistance, deltaFuel, GPSInformation);
//            dataSummarizeZHBMap.get(deviceId).updateSI(dataMaterialZHB);
//
//            //t++;
//            latestFuel = containingFuel;
//            //判断是否油量传感器数据是否为0
//            judgingFuelSensor0(containingFuel, dataMaterialZHB);
//
//            //这里逻辑很烂，暂时没时间改进了,计算当前方向
//            if(i == 0){
//                GPSInfoStart = GPSInformation;
//            }
//
//            if(i == 5){
//                try {
//                    new SIDao().updateDirection(deviceId,
//                            new java.sql.Date(dataMaterialZHB.getDate().getTime()),
//                            Miller_projection.convert(GPSInfoStart, GPSInformation) + "");
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }
//
//
//
//        HashMap<String, Object> map = new HashMap<>();
//        GPSInformation = "\"" + GPSInformation + "\"";
//        map.put("field", "GPSInformation");
//        map.put("val", GPSInformation);
//        map.put("iotDeviceId", deviceId);
//
//        try {
//            new DSIDao().update(map);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//    }
//
//    /*
//        如果一个数据包中连续六个containingFuel为0.0，代表油耗传感器故障
//     */
//    private static int judgingFuelSensor(float[] judgingFuel, DataMaterialZHB dataMaterialZHB) {
//        for (int i = 0; i < CommonUtils.packageContainingElement; i++) {
//            if (judgingFuel[i] == 0.0f) {
//                return 0;
//            }
//        }
//
//        try {
//            new IIDao().add(new InspectorInformation(dataMaterialZHB.getIotDeviceId(), dataMaterialZHB.getDate(),
//                    dataMaterialZHB.getTime(), dataMaterialZHB.getGPSInformation(), "0"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return 1;
//
//    }
//
//    private static int judgingFuelSensor0(float judgingFuel, DataMaterialZHB dataMaterialZHB) {
//
//
//        if (judgingFuel == 0.0f) {
//            try {
//                //System.out.println(dataMaterialZHB.getIotDeviceId() + " " + dataMaterialZHB.getDate());
//                IIDao iiDao = new IIDao();
//                List<InspectorInformation> querylist = iiDao.findByIdAndParticularDate(dataMaterialZHB.getIotDeviceId(), dataMaterialZHB.getDate());
//
//                //System.out.println("size:    " + querylist.size());
//                boolean judgeExistence = false;//false为当天II表中不存在该数据
//                if(!querylist.isEmpty()){
//                    for(int i = 0; i < querylist.size(); i++){
//                        if(querylist.get(i).getWarningType().equals("1001")){
//                            judgeExistence = true;
//                        }
//                    }
//                }
//
//                if(judgeExistence == false){
//                    new IIDao().add(new InspectorInformation(dataMaterialZHB.getIotDeviceId(), dataMaterialZHB.getDate(),
//                            dataMaterialZHB.getTime(), dataMaterialZHB.getGPSInformation(), "0"));//1001代表油量为0的异常
//                }
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return 1;
//
//        } else {
//            return 0;
//        }
//
//    }
//
//
//    private static void addData(byte angleX, byte angleY, byte angleZ, byte angularXSpeed, byte angularYSpeed,
//                                byte angularZSpeed, byte currentSpeed, byte xAcceleration, byte yAcceleration,
//                                byte zAcceleration, float containingFuel, float containingElectricity, java.sql.Date informationDate,
//                                Time informationTime, int rotationOrientation, boolean workingFlag,
//                                String iotDeviceId, String GPSInformation, float deltaDistance, float deltaFuel) {
//
//        containingFuel = Float.valueOf(CommonUtils.df.format(containingFuel));
//        containingElectricity = Float.valueOf(CommonUtils.df.format(containingElectricity));
//        deltaDistance = Float.valueOf(CommonUtils.df.format(deltaDistance));
//        deltaFuel = Float.valueOf(CommonUtils.df.format(deltaFuel));
//
//        DeviceDynamicInformation deviceDynamicInfo = new DeviceDynamicInformation(
//                (float) angleX, (float) (angleY), (float) (angleZ),
//                (float) (angularXSpeed), (float) (angularYSpeed), (float) (angularZSpeed),
//                (float) (currentSpeed), (float) (xAcceleration),
//                (float) (yAcceleration), (float) (zAcceleration),
//                containingFuel, containingElectricity, informationDate, informationTime,
//                rotationOrientation, workingFlag, iotDeviceId, GPSInformation, deltaDistance, deltaFuel
//        );
//        //checkResult(deviceDynamicInfo);
//
//        DDIDao ddiDao = new DDIDao();
//
//        try {
//            ddiDao.add(deviceDynamicInfo);
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//    }
//
//
//    private static void mapCheck(String deviceId) {
//
//        if (!dataSummarizeZHBMap.containsKey(deviceId)) {
//            dataSummarizeZHBMap.put(deviceId, new DataSummarizeZHB());
//        } else {
//
//        }
//    }
//
//
//}

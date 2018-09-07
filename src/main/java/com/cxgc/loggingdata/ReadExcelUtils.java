package com.cxgc.loggingdata;


import com.cxgc.database.dao.DSIDao;
import com.cxgc.database.model.DeviceStaticInformation;
import com.cxgc.database.model.StaticInformation;

import java.io.FileInputStream;
        import java.io.FileNotFoundException;
        import java.io.IOException;
        import java.io.InputStream;
        import java.util.Date;
        import java.util.HashMap;
        import java.util.Map;

        import org.apache.poi.hssf.usermodel.HSSFWorkbook;
        import org.apache.poi.ss.usermodel.Cell;
        import org.apache.poi.ss.usermodel.DateUtil;
        import org.apache.poi.ss.usermodel.Row;
        import org.apache.poi.ss.usermodel.Sheet;
        import org.apache.poi.ss.usermodel.Workbook;
        import org.apache.poi.xssf.usermodel.XSSFWorkbook;
       /* import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;*/

/**
 * 读取Excel
 *
 * @author zengwendong
 */


public class ReadExcelUtils {
    //private Logger logger = LoggerFactory.getLogger(ReadExcelUtils.class);
    private Workbook wb;
    private Sheet sheet;
    private Row row;

    public ReadExcelUtils(String filepath) {
        if(filepath==null){
            return;
        }
        String ext = filepath.substring(filepath.lastIndexOf("."));
        try {
            InputStream is = new FileInputStream(filepath);
            if(".xls".equals(ext)){
                wb = new HSSFWorkbook(is);
            }else if(".xlsx".equals(ext)){
                wb = new XSSFWorkbook(is);
            }else{
                wb=null;
            }
        } catch (FileNotFoundException e) {
            //logger.error("FileNotFoundException", e);
        } catch (IOException e) {
            //logger.error("IOException", e);
        }
    }

    /**
     * 读取Excel表格表头的内容
     *
     * @param InputStream
     * @return String 表头内容的数组
     * @author zengwendong
     */
    public String[] readExcelTitle() throws Exception{
        if(wb==null){
            throw new Exception("Workbook对象为空！");
        }
        sheet = wb.getSheetAt(0);
        row = sheet.getRow(0);
        // 标题总列数
        int colNum = row.getPhysicalNumberOfCells();
        System.out.println("colNum:" + colNum);
        String[] title = new String[colNum];
        for (int i = 0; i < colNum; i++) {
            // title[i] = getStringCellValue(row.getCell((short) i));
            title[i] = row.getCell(i).getCellFormula();
        }
        return title;
    }

    /**
     * 读取Excel数据内容
     *
     * @param InputStream
     * @return Map 包含单元格数据内容的Map对象
     * @author zengwendong
     */
    public Map<Integer, Map<Integer,Object>> readExcelContent() throws Exception{
        if(wb==null){
            throw new Exception("workbook对象为空");
        }
        Map<Integer, Map<Integer,Object>> content = new HashMap<Integer, Map<Integer,Object>>();

        sheet = wb.getSheetAt(0);
        // 得到总行数
        int rowNum = sheet.getLastRowNum();
        row = sheet.getRow(0);
        int colNum = row.getPhysicalNumberOfCells();
        // 正文内容应该从第二行开始,第一行为表头的标题
        for (int i = 1; i <= rowNum; i++) {
            row = sheet.getRow(i);
            int j = 0;
                Map<Integer,Object> cellValue = new HashMap<Integer, Object>();
                while (j < colNum) {
                Object obj = getCellFormatValue(row.getCell(j));
                cellValue.put(j, obj);
                j++;
            }
            content.put(i, cellValue);
        }
        return content;
    }

    /**
     *
     * 根据Cell类型设置数据
     *
     * @param cell
     * @return
     * @author zengwendong
     */

    /*
    CellType	类型	值
    CELL_TYPE_NUMERIC	数值型	0
    CELL_TYPE_STRING	字符串型	1
    CELL_TYPE_FORMULA	公式型	2
    CELL_TYPE_BLANK	空值	3
    CELL_TYPE_BOOLEAN	布尔型	4
    CELL_TYPE_ERROR	错误	5
     */
    private Object getCellFormatValue(Cell cell) {
        Object cellvalue = "";

        //注：excel中设置单元格格式为文本后，再将
        if (cell != null) {
            // 判断当前Cell的Type
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC:// 如果当前Cell的Type为NUMERIC{
                    System.out.println("haha");
                    double i = cell.getNumericCellValue();//从这里拿到double，但因为我的目标是向数据库插入整形数，故可以在这里
                    int i0 = (int)i;
                    // 将其改成整数，但这样会导致无法传小数，所以最终决定在插入数据库的参数那里执行强制类型转换

                case Cell.CELL_TYPE_FORMULA: {
                    // 判断当前的cell是否为Date
                    if (DateUtil.isCellDateFormatted(cell)) {
                        // 如果是Date类型则，转化为Data格式
                        // data格式是带时分秒的：2013-7-10 0:00:00
                        // cellvalue = cell.getDateCellValue().toLocaleString();
                        // data格式是不带带时分秒的：2013-7-10
                        Date date = cell.getDateCellValue();
                        cellvalue = date;
                    } else {// 如果是纯数字

                        // 取得当前Cell的数值
                        cellvalue = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                }
                case Cell.CELL_TYPE_STRING:// 如果当前Cell的Type为STRING
                    // 取得当前的Cell字符串
                    cellvalue = cell.getRichStringCellValue().getString();
                    break;
                default:// 默认的Cell值
                    cellvalue = "";
            }
        } else {
            cellvalue = "";
        }
        return cellvalue;
    }

    public static void main(String[] args) {
        try {
            String filepath = "D:/tempdata/test.xlsx";
            ReadExcelUtils excelReader = new ReadExcelUtils(filepath);
            // 对读取Excel表格标题测试
//			String[] title = excelReader.readExcelTitle();
//			System.out.println("获得Excel表格的标题:");
//			for (String s : title) {
//				System.out.print(s + " ");
//			}

            // 对读取Excel表格内容测试
            Map<Integer, Map<Integer,Object>> map = excelReader.readExcelContent();
            System.out.println("获得Excel表格的内容:");
            for (int i = 1; i <= map.size(); i++) {
                System.out.println(map.get(i));
                Map<Integer,Object> tempMap = map.get(i);

                Boolean inControl = true;
                java.sql.Date startDate = DateUtils.strToSqlDate((String)tempMap.get(7));//DateUtils.strToSqlDate("2018-1-18");
                double fuelTank = Double.valueOf((String)tempMap.get(9));
                double rail_id = Double.valueOf((String)tempMap.get(11));
                DeviceStaticInformation deviceStaticInformation = new DeviceStaticInformation((String)tempMap.get(0), (String)tempMap.get(1),
                        (String)tempMap.get(2), (String)tempMap.get(3), (String)tempMap.get(4), (String)tempMap.get(5), inControl,
                        startDate, (String)tempMap.get(8), fuelTank, (String)tempMap.get(10),
                        (int)rail_id, (String)tempMap.get(12), (String)tempMap.get(13),
                        (String)tempMap.get(14), (String)tempMap.get(15));
                new DSIDao().add(deviceStaticInformation);
            }
        } catch (FileNotFoundException e) {
            System.out.println("未找到指定路径的文件!");
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}

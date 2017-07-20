package com.stock.util;

import java.io.*;

import java.lang.reflect.*;
import java.util.*;
import java.text.SimpleDateFormat;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
/** 
 * 动态生成模版并导出excel文档 
 * 
 * 本类的简要描述： 
 * 	利用开源组件poi生成
 * 	jar包：poi-3.8-20120326.jar
 *  
 */ 
public class EETemplate<T> {

    public void callExportExcel(String title, String[] headers, String[] fields, Collection<T> dataset, OutputStream out, String pattern) throws Exception {
        exportExcel(title, headers, fields, dataset, out, pattern);
    }
    
    //末尾添加一行总计
    /**
     * 
     * @param title
     * @param headers
     * @param fields
     * @param dataset
     * @param out
     * @param pattern
     * @param total  总金额
     * @param cou  第几列
     * @throws Exception
     */
    public void callExportExcel(String title, String[] headers, String[] fields, Collection<T> dataset, OutputStream out, String pattern,String total,Integer cou) throws Exception {
        exportExcelTotal(title, headers, fields, dataset, out, pattern, total,cou);
    }
    
    public void callExportExcel(String title, String[] headers, String[] fields, Collection<T> dataset, OutputStream out) throws Exception {
        exportExcel(title, headers, fields, dataset, out, "yyyy-MM-dd");
    }

    /**
     * 利用了java的反射机制将放置在集合中并且符合一定条件的数据以excel的形式输出到指定io设备
     * 
     * @param title 表格标题名
     * @param headers 表格列名数组
     * @param fields 表格列名对应属性名数组
     * @param dataset 需要显示的数据集合,集合中一定要放置符合javabean风格的类的对象
     * @param out 与输出设备关联的流对象，可以将excel文档导出到服务器本地或者网络
     * @param pattern 如果有时间数据，设定输出格式(默认为yyy-MM-dd)
     * @throws Exception 
     */
    public void exportExcel(String title, String[] headers, String[] fields, Collection<T> dataset, OutputStream out, String pattern) throws Exception {
        //声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        
        //生成一个表格
        HSSFSheet sheet = workbook.createSheet(title);
        
        //设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth(15);    
        
        //生成并设置一个字体
        HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.VIOLET.index);
        font.setFontHeightInPoints((short) 12);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        
        //生成并设置一个样式
        HSSFCellStyle style = workbook.createCellStyle();       
        style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);        
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        
        //把字体应用到当前的样式
        style.setFont(font);
        
        //生成并设置另一个字体
        HSSFFont font2 = workbook.createFont();        
        font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        
        //生成并设置另一个样式
        HSSFCellStyle style2 = workbook.createCellStyle();
        style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);        
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
              
        //把字体应用到当前的样式
        style2.setFont(font2);
        
        //生成并设置另一个字体
        HSSFFont font3 = workbook.createFont();
        font3.setColor(HSSFColor.BLUE.index);

        //产生表格标题行
        HSSFRow row = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(style);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }

        //遍历集合数据并产生数据行
        Iterator<T> it = dataset.iterator();
        int index = 0;
        while (it.hasNext()) {
            index++;
            row = sheet.createRow(index);
            T t = (T) it.next();
            for (int i = 0; i < fields.length; i++) {
                HSSFCell cell = row.createCell(i);
                cell.setCellStyle(style2);
                String fieldName = fields[i];
                String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);              
                Method getMethod = t.getClass().getMethod(getMethodName,new Class[] {});
                Object value = getMethod.invoke(t, new Object[] {});
                if(value != null){
                	//判断值的类型后进行强制类型转换
                    String textValue = "";
                    if (value instanceof Date) {
                        Date date = (Date) value;
                        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                        textValue = sdf.format(date);                                                    
                    } else {
                        // 其它数据类型都当作字符串简单处理
                        textValue = value.toString();                                                                       
                    }      
                    HSSFRichTextString richString = new HSSFRichTextString(textValue);
                    richString.applyFont(font3);
                    cell.setCellValue(richString);                                              
                }                
            }
        }
        workbook.write(out);       
    }
    
    
    
    public void exportExcelTotal(String title, String[] headers, String[] fields, Collection<T> dataset, OutputStream out, String pattern,String total,Integer  cou) throws Exception {
        //声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        
        //生成一个表格
        HSSFSheet sheet = workbook.createSheet(title);
        
        //设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth(15);    
        
        //生成并设置一个字体
        HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.VIOLET.index);
        font.setFontHeightInPoints((short) 12);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        
        //生成并设置一个样式
        HSSFCellStyle style = workbook.createCellStyle();       
        style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);        
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        
        //把字体应用到当前的样式
        style.setFont(font);
        
        //生成并设置另一个字体
        HSSFFont font2 = workbook.createFont();        
        font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        
        //生成并设置另一个样式
        HSSFCellStyle style2 = workbook.createCellStyle();
        style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);        
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
              
        //把字体应用到当前的样式
        style2.setFont(font2);
        
        //生成并设置另一个字体
        HSSFFont font3 = workbook.createFont();
        font3.setColor(HSSFColor.BLUE.index);

        //产生表格标题行
        HSSFRow row = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(style);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }

        //遍历集合数据并产生数据行
        Iterator<T> it = dataset.iterator();
        int index = 0;
        while (it.hasNext()) {
            index++;
            row = sheet.createRow(index);
            T t = (T) it.next();
            for (int i = 0; i < fields.length; i++) {
                HSSFCell cell = row.createCell(i);
                cell.setCellStyle(style2);
                String fieldName = fields[i];
                String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);              
                Method getMethod = t.getClass().getMethod(getMethodName,new Class[] {});
                Object value = getMethod.invoke(t, new Object[] {});
                if(value != null){
                	//判断值的类型后进行强制类型转换
                    String textValue = "";
                    if (value instanceof Date) {
                        Date date = (Date) value;
                        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                        textValue = sdf.format(date);                                                    
                    } else {
                        // 其它数据类型都当作字符串简单处理
                        textValue = value.toString();                                                                       
                    }      
                    HSSFRichTextString richString = new HSSFRichTextString(textValue);
                    richString.applyFont(font3);
                    cell.setCellValue(richString);                                              
                }                
            }
        }
        if(!it.hasNext()){
        	 row = sheet.createRow(index+1);
             HSSFCell  cell = row.createCell(cou);
             cell.setCellValue(total);
             cell.setCellStyle(style2);
        }
        workbook.write(out);       
    }
    
    

    
}
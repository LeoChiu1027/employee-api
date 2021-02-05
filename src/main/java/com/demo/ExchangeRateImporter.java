package com.ctbc.smart.tool;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class ExchangeRateImporter {

    public static String excelPath = "C:\\Users\\lunay\\Documents\\project_documents\\CTBC\\smart_charlie\\各幣別折合美金每日匯率.xlsx";

    public static void main(String args[]) throws IOException {
        FileInputStream inputstream = new FileInputStream(excelPath);
        Workbook workbook = WorkbookFactory.create(inputstream);
        Sheet sheet = workbook.getSheetAt(0);
        int rowCount = sheet.getPhysicalNumberOfRows();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        for(int  i=3; i < rowCount; i++){
            Row row = sheet.getRow(i);
            if(row.getCell(0).getDateCellValue() != null){
//                System.out.println("row["+row.getRowNum()+"]"+sdf.format(row.getCell(0).getDateCellValue()));
                DecimalFormat df = new DecimalFormat("##.0000");
                DecimalFormat df1 = new DecimalFormat("##.00");
                System.out.println("('"+sdf.format(row.getCell(0).getDateCellValue())+"', 'EUR/TWD', '"+ Double.parseDouble(df.format(row.getCell(1).getNumericCellValue()))+"'),");
                System.out.println("('"+sdf.format(row.getCell(0).getDateCellValue())+"', 'JPY/TWD', '"+ Double.parseDouble(df1.format(row.getCell(2).getNumericCellValue()))+"'),");
                System.out.println("('"+sdf.format(row.getCell(0).getDateCellValue())+"', 'CNH/TWD', '"+ Double.parseDouble(df.format(row.getCell(3).getNumericCellValue()))+"'),");
            }
        }
    }

}

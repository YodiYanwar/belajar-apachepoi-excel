package com.yodiyanwar;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

/**
 *
 * @author Yodi Yanwar
 */
public class Operasi {
    
    HSSFWorkbook workbook = null;
    HSSFSheet sheet = null;
    
    public void buatBaru(){
        workbook = new HSSFWorkbook();
        sheet = workbook.createSheet("My Sheet");
        
        Map<String, Object[]> data = new HashMap<String, Object[]>();
        data.put("1", new Object[] {"NPM", "Nama", "Usia"});
        data.put("2", new Object[] {1221d, "Yodi", 19d});
        data.put("3", new Object[] {1222d, "Sugih", 15d});
        data.put("4", new Object[] {1223d, "Aldi", 22d});

        Set<String> keyset = data.keySet();
        int rownum = 0;
        for (String key : keyset) {
            Row row = sheet.createRow(rownum++);
            Object [] objArr = data.get(key);
            int cellnum = 0;
            for (Object obj : objArr) {
                Cell cell = row.createCell(cellnum++);
                if(obj instanceof Date) 
                    cell.setCellValue((Date)obj);
                else if(obj instanceof Boolean)
                    cell.setCellValue((Boolean)obj);
                else if(obj instanceof String)
                    cell.setCellValue((String)obj);
                else if(obj instanceof Double)
                    cell.setCellValue((Double)obj);
            }
        }

        try {
            FileOutputStream out = 
                    new FileOutputStream(new File("E:\\test.xls"));
            workbook.write(out);
            out.close();
            System.out.println("Excel written successfully..");


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
    public void bacaFile(){
        try {
     
            FileInputStream file = new FileInputStream(new File("E:\\test.xls"));

            //buat instance workbook untuk tampung file
            HSSFWorkbook workbook = new HSSFWorkbook(file);

            //ambil sheet pertama
            HSSFSheet sheet = workbook.getSheetAt(0);

            //iterasikan lewat masing2 row
            Iterator<Row> rowIterator = sheet.iterator();
            while(rowIterator.hasNext()) {
                Row row = rowIterator.next();

                //iterasikan lewat masing2 colom
                Iterator<Cell> cellIterator = row.cellIterator();
                while(cellIterator.hasNext()) {

                    Cell cell = cellIterator.next();

                    switch(cell.getCellType()) {
                        case Cell.CELL_TYPE_BOOLEAN:
                            System.out.print(cell.getBooleanCellValue() + "\t\t");
                            break;
                        case Cell.CELL_TYPE_NUMERIC:
                            System.out.print(cell.getNumericCellValue() + "\t\t");
                            break;
                        case Cell.CELL_TYPE_STRING:
                            System.out.print(cell.getStringCellValue() + "\t\t");
                            break;
                    }
                }
                System.out.println("");
            }
            file.close();
            FileOutputStream out = 
                new FileOutputStream(new File("E:\\test.xls"));
            workbook.write(out);
            out.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

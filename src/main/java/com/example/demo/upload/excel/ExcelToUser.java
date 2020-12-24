package com.example.demo.upload.excel;

import com.example.demo.model.entity.UserEntity;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelToUser {
    public static List<UserEntity> parseExcelFile(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();
            List<UserEntity> userEntities = new ArrayList<>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();
                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();
                UserEntity userEntity = new UserEntity();

                int cellIndex = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();
                     if (cellIndex == 0) { // Name
                        userEntity.setName(currentCell.getStringCellValue());
                    } else if (cellIndex == 1) { // Address
                        userEntity.setAddress(currentCell.getStringCellValue());
                    } else if (cellIndex == 2) { // Age
                        userEntity.setAge((int) currentCell.getNumericCellValue());
                    }
                    cellIndex++;
                }
                userEntities.add(userEntity);
            }
            workbook.close();
            return userEntities;
        } catch (IOException e) {
            throw new RuntimeException("Thất bại! -> message = " + e.getMessage());
        }
    }
}

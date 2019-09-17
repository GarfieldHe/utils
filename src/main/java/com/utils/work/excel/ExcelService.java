package com.utils.work.excel;

import com.utils.work.http.GlobalException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class ExcelService {
    List<String[]> xlsxToList(MultipartFile mf, int size) throws IOException {
        if (!"xlsx".equals(judgeFile(mf))) {
            throw new GlobalException("-1","文件出错");
        }
        try {
            List<String[]> stringList = XLSXCovertCSVReader.readerExcel(mf.getInputStream(),"1", size);
            for (String[] strings : stringList) {
                for (int i = 0; i < strings.length; i++) {
                    if (strings[i] != null && ("\"").equals(String.valueOf(strings[i].charAt(0)))) {
                        strings[i] = strings[i].substring(1, strings[i].length() - 1);
                    }
                }
            }
            return stringList;
        } catch (Exception e) {
            throw new GlobalException("-1","XLSXCovertCSV 失败");
        }
    }
    String xlsxToObject(MultipartFile mf) throws IOException {
        String fileType = judgeFile(mf);
        if ("xlsx".equals(fileType)) {
            InputStream xlsxFile = mf.getInputStream();
            Workbook workbook = new XSSFWorkbook(xlsxFile);
            Sheet sheet = workbook.getSheetAt(0);
            System.out.println(sheet.getRow(0).getCell(0).toString());
            return "xlsx";
        }else if ("xls".equals(fileType)) {
            InputStream xlsFile = mf.getInputStream();
            Workbook workbook = new HSSFWorkbook(xlsFile);
            Sheet sheet = workbook.getSheetAt(0);
            System.out.println(sheet.getRow(0).getCell(5).toString());
            return "xls";
        }else {
            throw new GlobalException("-1", "不是 excel 文件");
        }
    }

    private String judgeFile(MultipartFile mf) {
        if (mf.isEmpty()) {
            return "error";
        }
        String fileName = mf.getOriginalFilename();
        if (fileName == null || "".equals(fileName)) {
            return "null";
        }
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}

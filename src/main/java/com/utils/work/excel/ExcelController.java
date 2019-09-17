package com.utils.work.excel;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.ws.spi.WebServiceFeatureAnnotation;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/utils/excel")
public class ExcelController {

    @Autowired
    private ExcelService excelService;

    @ApiOperation(value="单个.xlsx文件解析，一个表头多行格式，最终获得list")
    @PostMapping(value = "/xlsx/list")
    public List<String[]> xlsxToList(@RequestParam MultipartFile mf, int size) throws Exception {
        return excelService.xlsxToList(mf, size);
    }

    @ApiOperation(value="解析excel，返回对象")
    @PostMapping(value = "/xlsx/object")
    public String xlsxToObject(@RequestParam MultipartFile mf) throws IOException {
        return excelService.xlsxToObject(mf);
    }
}

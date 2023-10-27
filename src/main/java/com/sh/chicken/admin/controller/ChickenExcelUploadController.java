package com.sh.chicken.admin.controller;

import com.sh.chicken.admin.controller.dto.ChickenBrandUploadDto;
import com.sh.chicken.admin.controller.dto.ChickenMenuImgUploadDto;
import com.sh.chicken.admin.controller.dto.ChickenMenuUploadDto;
import com.sh.chicken.admin.application.chicken.ChickenBrandUploadService;
import com.sh.chicken.admin.application.chicken.ChickenMenuUploadService;
import com.sh.chicken.global.aop.log.LogTrace;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class ChickenExcelUploadController {

    private final ChickenBrandUploadService chickenBrandUploadService;
    private final ChickenMenuUploadService chickenMenuUploadService;

    @LogTrace
    @GetMapping("/excel/upload")
    public String uploadExcel(){
        return "/admin/excel-upload";
    }

    @LogTrace
    @PostMapping("/excel/brand/upload")
    public String brandUploadExcel(@RequestParam("file") MultipartFile file, Model model) throws IOException {

        List<ChickenBrandUploadDto> dataList = new ArrayList<>();

        String extension = FilenameUtils.getExtension(file.getOriginalFilename());

        if (!extension.equals("xlsx") && !extension.equals("xls")) {
            throw new IOException("엑셀파일만 업로드 해주세요.");
        }

        Workbook workbook = null;

        if (extension.equals("xlsx")) {
            workbook = new XSSFWorkbook(file.getInputStream());
        } else if (extension.equals("xls")) {
            workbook = new HSSFWorkbook(file.getInputStream());
        }

        Sheet worksheet = workbook.getSheetAt(0);

        String originalFilename = file.getOriginalFilename();
        log.info("[originalFilename] : {}", originalFilename);

        for (int i = 2; i < worksheet.getPhysicalNumberOfRows(); i++) { // 1행부터

            Row row = worksheet.getRow(i);

            ChickenBrandUploadDto data = new ChickenBrandUploadDto();

            data.setBrandName(row.getCell(0).getStringCellValue());

            dataList.add(data);
        }

        model.addAttribute("datas", dataList); // 이제 여기 db에 넣기
        chickenBrandUploadService.saveBrand(dataList);

        return "/admin/excel-list";

    }

    @PostMapping("/excel/menu/upload")
    public String menuUploadExcel(@RequestParam("file") MultipartFile file, Model model) throws IOException {

        List<ChickenMenuUploadDto> dataList = new ArrayList<>();

        String extension = FilenameUtils.getExtension(file.getOriginalFilename());

        if (!extension.equals("xlsx") && !extension.equals("xls")) {
            throw new IOException("엑셀파일만 업로드 해주세요.");
        }

        Workbook workbook = null;

        if (extension.equals("xlsx")) {
            workbook = new XSSFWorkbook(file.getInputStream());
        } else if (extension.equals("xls")) {
            workbook = new HSSFWorkbook(file.getInputStream());
        }

        Sheet worksheet = workbook.getSheetAt(0);

        String originalFilename = file.getOriginalFilename();
        log.info("[originalFilename] : {}", originalFilename);

        for (int i = 2; i < worksheet.getPhysicalNumberOfRows(); i++) { // 1행부터

            Row row = worksheet.getRow(i);


            // 4개임 수정하기
            String brandName = row.getCell(0).getStringCellValue(); // 브랜드이름
            String menuName = row.getCell(1).getStringCellValue(); // 치킨메뉴
            int price = (int) row.getCell(2).getNumericCellValue();// 가격
            String contents = row.getCell(3).getStringCellValue(); // 설명
            ChickenMenuUploadDto data = new ChickenMenuUploadDto(brandName, menuName, price, contents);

            dataList.add(data);
        }

        model.addAttribute("datas", dataList); // 이제 여기 db에 넣기

        chickenMenuUploadService.saveMenu(dataList);

        return "/admin/excel-list2";

    }



    @PostMapping("/excel/menu/upload/img")
    public String imgUploadExcel(@RequestParam("file") MultipartFile file, Model model) throws IOException {

        List<ChickenMenuImgUploadDto> dataList = new ArrayList<>();

        String extension = FilenameUtils.getExtension(file.getOriginalFilename());

        if (!extension.equals("xlsx") && !extension.equals("xls")) {
            throw new IOException("엑셀파일만 업로드 해주세요.");
        }

        Workbook workbook = null;

        if (extension.equals("xlsx")) {
            workbook = new XSSFWorkbook(file.getInputStream());
        } else if (extension.equals("xls")) {
            workbook = new HSSFWorkbook(file.getInputStream());
        }

        Sheet worksheet = workbook.getSheetAt(0);

        String originalFilename = file.getOriginalFilename();
        log.info("[originalFilename] : {}", originalFilename);

        for (int i = 2; i < worksheet.getPhysicalNumberOfRows(); i++) { // 1행부터

            Row row = worksheet.getRow(i);


            // 4개임 수정하기
            String brandName = row.getCell(0).getStringCellValue(); // 브랜드이름
            String menuName = row.getCell(1).getStringCellValue(); // 치킨메뉴
            int price = (int) row.getCell(2).getNumericCellValue();// 가격
            String contents = row.getCell(3).getStringCellValue(); // 설명
            String img = row.getCell(4).getStringCellValue(); // 이미지
            ChickenMenuImgUploadDto data = new ChickenMenuImgUploadDto(brandName, menuName, price, contents, img);

            dataList.add(data);
        }

        model.addAttribute("datas", dataList); // 이제 여기 db에 넣기

//        chickenMenuUploadService.saveMenu(dataList);

        return "/admin/excel-list3";

    }


    private boolean isAllowedMIMEType(String mimeType) {
        if (mimeType.equals("application/x-tika-ooxml"))
            return true;
        return false;
    }


}

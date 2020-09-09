package com.ranyk.ssv.common.utils;


import org.apache.poi.ss.usermodel.Workbook;

import java.io.*;

/**
 * ClassName:PoiUtils
 * Description:POI相关操作
 *
 * @author ranyi
 * @date 2019-12-19 22:35
 * Version: V1.0
 */
public class PoiUtils {
    /**
     * 生成Excel文件
     * @param workbook
     * @param fileName
     * @return
     */
    public static File createExcelFile(Workbook workbook, String fileName) {
        OutputStream stream = null;
        File file = null;
        try {
            file = File.createTempFile(fileName, ".xlsx");
            stream = new FileOutputStream(file.getAbsoluteFile());
            workbook.write(stream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(workbook);
            IOUtils.closeQuietly(stream);
        }
        return file;
    }
}

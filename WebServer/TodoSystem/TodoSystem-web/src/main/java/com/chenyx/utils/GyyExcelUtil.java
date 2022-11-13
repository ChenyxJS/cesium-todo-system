package com.chenyx.utils;

import com.chenyx.entity.query.QueryParam;
import org.apache.poi.util.DefaultTempFileCreationStrategy;
import org.apache.poi.util.TempFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * excel 导入导出帮助类
 *
 * @author gyy
 * @date 2021/9/10
 **/
public class GyyExcelUtil {
    /**
     * 构建Excel导出类（可以加前缀后缀）
     *
     * @param queryParam 继承QueryParam的类
     * @param prefix     导出Excel文件名前缀 没有传null
     * @param suffix     导出Excel文件名后缀 没有传null
     */
    public static void buildExcelPathForExport(QueryParam queryParam, String prefix, String suffix) {
        StringBuilder exportPath = new StringBuilder();
        StringBuilder exportPathTemp = new StringBuilder();
        StringBuilder returnPath = new StringBuilder();
        //导出时间
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd(HH-mm-ss)");
        String dateStr = format.format(new Date(System.currentTimeMillis()));

        exportPath.append(queryParam.getWebappPath()).append(queryParam.getSavePath());
        exportPathTemp.append(queryParam.getWebappPath()).append(queryParam.getSavePath());
        returnPath.append("/").append(queryParam.getSavePath());
        if (!new File(exportPath.toString()).exists()) {
            //创建目录
            new File(exportPath.toString()).mkdirs();
        }
        if (prefix != null) {
            exportPath.append(prefix);
            exportPathTemp.append(prefix);
            returnPath.append(prefix);
        }
        exportPath
                .append(dateStr)
                .append(".xlsx");
        exportPathTemp
                .append(dateStr)
                .append("temp")
                .append(".xlsx");
        returnPath
                .append(dateStr)
                .append(".xlsx");
        if (suffix != null) {
            exportPath.append(suffix);
            exportPathTemp.append(suffix);
            returnPath.append(suffix);
        }
        queryParam.setExcelReturnPath(returnPath.toString());
        queryParam.setExcelSavePath(exportPath.toString());
        queryParam.setExcelSavePathTemp(exportPathTemp.toString());

        // 临时文件设置
        queryParam.setTempDir(new File(queryParam.getExcelSavePathTemp()));
        if (!queryParam.getTempDir().exists()) {
            queryParam.getTempDir().mkdirs();
        }
        TempFile.setTempFileCreationStrategy(new DefaultTempFileCreationStrategy(queryParam.getTempDir()));
    }
}

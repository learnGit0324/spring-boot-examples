package com.neo.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class TestServiceImpl implements TestService{
    @Override
    public void doDownload(HttpServletResponse response) {
        //设置响应头
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        //中文文件名编码
        String fileName = null;
        try {
            //中文文件名编码要用URLEncoder.encode编码
            fileName = URLEncoder.encode("模板", "UTF-8").replaceAll("\\+", "%20");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setHeader("Content-Disposition", "attachment;fileName=" + fileName + ".xlsx");
        try {
            //主标题和副标题在excel中分别是是第0和第1行
            List<Integer> columnIndexes = Arrays.asList(0,1);
            //自定义标题和内容策略(具体定义在下文)
            CellStyleStrategy cellStyleStrategy =
                    new CellStyleStrategy(columnIndexes,new WriteCellStyle(), new WriteCellStyle());

            ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream())
                    .registerWriteHandler(new CellRowHeightStyleStrategy())   //设置行高的策略
                    .registerWriteHandler(cellStyleStrategy)        //设置表头和内容的策略
                    .build();

            //填入数据
            writeData(excelWriter);
            // 千万别忘记关闭流
            excelWriter.finish();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    /**
     * 写入数据
     * @param excelWriter excelWriter
     */
    private void writeData(ExcelWriter excelWriter){

        WriteSheet writeSheet = new WriteSheet();
        //设置写到第几个sheet
        writeSheet.setSheetNo(0);
        writeSheet.setSheetName("测试");
        //造数据
        List<List<Object>> list = ListUtils.newArrayList();
        for (int i = 0; i < 10; i++) {
            List<Object> data = ListUtils.newArrayList();
            data.add("字符串" + i);
            data.add(new Date());
            data.add(0.56);
            list.add(data);
        }
        //设置表头
        List<List<String>> headList = new ArrayList<>();
        String name = "********表";
        headList.add(Arrays.asList(name,"序号"));
        headList.add(Arrays.asList(name,"名称"));
        for (int i = 1; i <3 ; i++) {
            headList.add(Arrays.asList(name,"单位"+i));
        }
        writeSheet.setHead(headList);

        //（设置数据）
        //第一列序号从1开始增加
        AtomicInteger orderNumber = new AtomicInteger(1);
        ArrayList<List<Object>> dataList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            List<Object> data = ListUtils.newArrayList();
            data.add(String.valueOf(orderNumber.getAndIncrement()));
            data.add("名称" + i);
            data.add("单元"+i);
            data.add(0.56);
            dataList.add(data);
        }
        excelWriter.write(list, writeSheet);
    }
}

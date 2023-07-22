package com.neo.excel;

import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.write.handler.context.CellWriteHandlerContext;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.IndexedColors;

import java.util.List;

public class CellStyleStrategy extends HorizontalCellStyleStrategy {
    private final WriteCellStyle headWriteCellStyle;
    private final WriteCellStyle contentWriteCellStyle;

    /**
     * 操作列
     */
    private final List<Integer> columnIndexes;

    public CellStyleStrategy(List<Integer> columnIndexes,WriteCellStyle headWriteCellStyle, WriteCellStyle contentWriteCellStyle) {
        this.columnIndexes = columnIndexes;
        this.headWriteCellStyle = headWriteCellStyle;
        this.contentWriteCellStyle = contentWriteCellStyle;
    }

    //设置头样式
    @Override
    protected void setHeadCellStyle( CellWriteHandlerContext context) {
        // 获取字体实例
        WriteFont headWriteFont = new WriteFont();
        headWriteFont.setFontName("宋体");
        if (columnIndexes.get(0).equals(context.getRowIndex())) {
            headWriteCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
            headWriteFont.setFontHeightInPoints((short) 14);
            headWriteFont.setBold(true);
        }else{
            headWriteCellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
            headWriteFont.setFontHeightInPoints((short) 11);
            headWriteFont.setBold(false);
        }
        headWriteCellStyle.setWriteFont(headWriteFont);
        if (stopProcessing(context)) {
            return;
        }
        WriteCellData<?> cellData = context.getFirstCellData();
        WriteCellStyle.merge(headWriteCellStyle, cellData.getOrCreateStyle());
    }

    //设置填充数据样式
    @Override
    protected void setContentCellStyle(CellWriteHandlerContext context) {
        WriteFont contentWriteFont = new WriteFont();
        contentWriteFont.setFontName("宋体");
        contentWriteFont.setFontHeightInPoints((short) 11);
        //设置数据填充后的实线边框
        contentWriteCellStyle.setWriteFont(contentWriteFont);
        contentWriteCellStyle.setBorderLeft(BorderStyle.THIN);
        contentWriteCellStyle.setBorderTop(BorderStyle.THIN);
        contentWriteCellStyle.setBorderRight(BorderStyle.THIN);
        contentWriteCellStyle.setBorderBottom(BorderStyle.THIN);
        WriteCellData<?> cellData = context.getFirstCellData();
        WriteCellStyle.merge(contentWriteCellStyle, cellData.getOrCreateStyle());
    }
}

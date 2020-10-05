package com.example.demo.excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

public class TestEasyExcel {
    public static void main(String[] args) {
//        String fileName = "C:\\Users\\csl\\Desktop\\testEasyExcel.xlsx";
//        //调用easyExcel方法，实现写操作
//        EasyExcel.write(fileName, DemoData.class).sheet("学生列表").doWrite(getData());


        //实现excel读操作
        String filename = "C:\\\\Users\\\\csl\\\\Desktop\\\\testEasyExcel.xlsx";
        EasyExcel.read(filename,DemoData.class,new ExcelListener()).sheet().doRead();
    }

    private static List<DemoData> getData() {
        List<DemoData> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DemoData data = new DemoData();
            data.setSname("长大三" + i);
            data.setSno(i);
            list.add(data);
        }
        return list;
    }
}

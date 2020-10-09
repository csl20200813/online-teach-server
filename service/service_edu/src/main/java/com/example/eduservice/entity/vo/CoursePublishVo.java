package com.example.eduservice.entity.vo;

import lombok.Data;

/**
 * @author: csl
 * @description: 查询课程所有信息的Vo对象，使用到了mybatis的sql和左外连接
 * @date: 2020-10-06 10:17
 */

@Data
public class CoursePublishVo {
    private String id;
    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    private String price;//只用于显示
}
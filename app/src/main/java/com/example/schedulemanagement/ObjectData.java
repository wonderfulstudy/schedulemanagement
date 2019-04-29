package com.example.schedulemanagement;

import java.util.Date;

public class ObjectData {
    private String theme;//事务主题
    private String remark;//事务备注
    private Date time;//事务时间

    //获取事务主题
    public String getTheme(){ return this.theme; }

    //获取事务时间
    public Date getTime(){ return this.time;}

    //获取事务备注
    public String getRemark(){ return this.remark;}

    //设置事务主题
    public void setTheme(String theme){this.theme=theme;}
    //设置事务时间
    public void setTime(Date time){ this.time=time;}
    //设置事务备注
    public void setRemark(String remark){this.remark=remark;}

    public ObjectData(String theme,Date time,String remark){
        super();
        this.theme=theme;
        this.time=time;
        this.remark=remark;
    }
}

package com.example.schedulemanagement;

public class GroupData {
    private String groupName;
    private String administratorId;

    //获取群组名称

    public String getGroupName(){
        return this.groupName;
    }

    //获取管理员id

    public String getAdministratorId() {
        return this.administratorId;
    }

    //设置群组名称

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    //设置管理员id

    public void setAdministratorId(String administratorId) {
        this.administratorId = administratorId;
    }
}

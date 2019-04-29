package com.example.schedulemanagement;

public class UserData {
    private String  userName;    //用户名
    private String  userPwd;     //用户密码
    private int     userGroupId;

    //获取用户名
    public String getUserName() {             //获取用户名
        return userName;
    }
    //获取用户密码
    public String getUserPwd() {                //获取用户密码
        return userPwd;
    }
    //获取用户群组名
    public int getgroupId() {
        return userGroupId;
    }

    //设置用户名
    public void setUserName(String userName) {  //输入用户名
        this.userName = userName;
    }
    //设置用户群组名

    public void setgroupId(int groupId) {
        this.userGroupId = groupId;
    }

    //设置用户密码
    public void setUserPwd(String userPwd) {     //输入用户密码
        this.userPwd = userPwd;
    }

    public UserData(String userName,String userPwd,int userGroupId){//这里初始化用户名、秘密、群组id
        super();
        this.userName=userName;
        this.userPwd=userPwd;
        this.userGroupId=userGroupId;
    }
}

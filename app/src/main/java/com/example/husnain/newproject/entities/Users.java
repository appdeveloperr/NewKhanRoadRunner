package com.example.husnain.newproject.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Users")
public class Users {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private int User_Id;
    private String Full_Name;
    private String User_Name;
    private String Password;
    private boolean IsSuperAdmin;
    private boolean IsAdmin;
    private String LastLogin;
    private boolean Is_Active;
    private int Created_By;
    private String Created_On;
    private int Changed_By;
    private String Changed_On;
    private String UserType;
    private String Emp_Code;

    public Users(int User_Id, String Full_Name, String User_Name, String Password, boolean IsSuperAdmin, boolean IsAdmin, String LastLogin, boolean Is_Active, int Created_By, String Created_On, int Changed_By, String Changed_On, String UserType, String Emp_Code) {
        this.User_Id = User_Id;
        this.Full_Name = Full_Name;
        this.User_Name = User_Name;
        this.Password = Password;
        this.IsSuperAdmin = IsSuperAdmin;
        this.IsAdmin = IsAdmin;
        this.LastLogin = LastLogin;
        this.Is_Active = Is_Active;
        this.Created_By = Created_By;
        this.Created_On = Created_On;
        this.Changed_By = Changed_By;
        this.Changed_On = Changed_On;
        this.UserType = UserType;
        this.Emp_Code = Emp_Code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser_Id(int user_Id) {
        User_Id = user_Id;
    }

    public void setFull_Name(String full_Name) {
        Full_Name = full_Name;
    }

    public void setUser_Name(String user_Name) {
        User_Name = user_Name;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setSuperAdmin(boolean superAdmin) {
        IsSuperAdmin = superAdmin;
    }

    public void setAdmin(boolean admin) {
        IsAdmin = admin;
    }

    public void setLastLogin(String lastLogin) {
        LastLogin = lastLogin;
    }

    public void setIs_Active(boolean is_Active) {
        Is_Active = is_Active;
    }

    public void setCreated_By(int created_By) {
        Created_By = created_By;
    }

    public void setCreated_On(String created_On) {
        Created_On = created_On;
    }

    public void setChanged_By(int changed_By) {
        Changed_By = changed_By;
    }

    public void setChanged_On(String changed_On) {
        Changed_On = changed_On;
    }

    public void setUserType(String userType) {
        UserType = userType;
    }

    public void setEmp_Code(String emp_Code) {
        Emp_Code = emp_Code;
    }

    public int getUser_Id() {
        return User_Id;
    }

    public String getFull_Name() {
        return Full_Name;
    }

    public String getUser_Name() {
        return User_Name;
    }

    public String getPassword() {
        return Password;
    }

    public boolean getIsSuperAdmin() {
        return IsSuperAdmin;
    }

    public boolean getIsAdmin() {
        return IsAdmin;
    }

    public String getLastLogin() {
        return LastLogin;
    }

    public boolean getIs_Active() {
        return Is_Active;
    }

    public int getCreated_By() {
        return Created_By;
    }

    public String getCreated_On() {
        return Created_On;
    }

    public int getChanged_By() {
        return Changed_By;
    }

    public String getChanged_On() {
        return Changed_On;
    }

    public String getUserType() {
        return UserType;
    }

    public String getEmp_Code() {
        return Emp_Code;
    }
}

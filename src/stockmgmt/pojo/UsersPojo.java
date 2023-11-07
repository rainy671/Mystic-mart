/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockmgmt.pojo;

/**
 *
 * @author dell
 */
public class UsersPojo {

    @Override
    public String toString() {
        return "UsersPojo{" + "userid=" + userid + ", empid=" + empid + ", username=" + username + ", password=" + password + ", usertype=" + usertype + '}';
    }
    private String userid;
    private String empid;
    private String username;
    private String password;
    private String usertype;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }
            
            
}

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
public class UserProfile {
    private static String username;
    private static String userid;
    private static String password;

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
       UserProfile.username = username;
    }

    public static String getUserid() {
        return userid;
    }

    public static void setUserid(String userid) {
       UserProfile.userid = userid;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
       UserProfile.password = password;
    }
}

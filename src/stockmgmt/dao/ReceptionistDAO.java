/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockmgmt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import stockmgmt.dbutil.DBConnection;
import stockmgmt.pojo.ReceptionistPojo;
import stockmgmt.pojo.UsersPojo;

/**
 *
 * @author dell
 */
public class ReceptionistDAO {
    public static Map<String,String> getNonRegisteredReceptionists()throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        Statement stmt=conn.createStatement();
        ResultSet rs=stmt.executeQuery("Select empid, empname from employees where job='Receptionist' and empid not in ( Select empid from users where usertype='Receptionist')");
        HashMap<String,String> receptionistList= new HashMap<>();
        while(rs.next())
        {
            String id=rs.getString(1);
            String name=rs.getString(2);
            receptionistList.put(id,name);
            
        }
        return receptionistList;
    }
    public static boolean addReceptionist(UsersPojo user)throws SQLException
    {
         Connection conn=DBConnection.getConnection();
         PreparedStatement ps=conn.prepareStatement("Insert into users values(?,?,?,?,?)");
         ps.setString(1,user.getUserid());
          ps.setString(2,user.getEmpid());
          ps.setString(3,user.getUsername());
          ps.setString(4,user.getPassword());
          ps.setString(5,user.getUsertype());
          int result=ps.executeUpdate();
          return result==1;
    }
    
    public static List<ReceptionistPojo> getAllReceptionistDetails()throws SQLException
    {
       Connection conn=DBConnection.getConnection();
        Statement stmt=conn.createStatement();
        ResultSet rs=stmt.executeQuery("Select users.empid,empname,userid,job,salary from users, employees where usertype='Receptionist' and users.empid=employees.empid");
        ArrayList<ReceptionistPojo> al=new ArrayList<>();
        while(rs.next())
        {
            ReceptionistPojo recep=new ReceptionistPojo();
            recep.setEmpid(rs.getString(1));
            recep.setEmpname(rs.getString(2));
            recep.setUserid(rs.getString(3));
            recep.setJob(rs.getString(4));
            recep.setSalary(rs.getDouble(5));
            al.add(recep);
            
        }
        return al;
    }
     public static Map<String,String> getAllReceptionistId()throws SQLException{
         Connection conn=DBConnection.getConnection();
        Statement stmt=conn.createStatement();
        ResultSet rs=stmt.executeQuery("Select userid,username from users where usertype='Receptionist' order by userid");
       
        HashMap<String,String> receptionistList= new HashMap<>();
        while(rs.next())
        {
            String id=rs.getString(1);
            String name=rs.getString(2);
            receptionistList.put(id,name);
            
        }
        return receptionistList;
     }
     
     public static boolean updatePassword(String userid,String password)throws SQLException
     {
         Connection conn=DBConnection.getConnection();
          PreparedStatement ps=conn.prepareStatement("Update users set password=? where userid=?");
          ps.setString(1,password);
          ps.setString(2,userid);
          return ps.executeUpdate()==1;
     }
     
     
     
     public static boolean deleteReceptionist(String userid)throws SQLException
     {
          Connection conn=DBConnection.getConnection();
          PreparedStatement ps=conn.prepareStatement("Delete from users where userid=?");
          ps.setString(1,userid);
          return ps.executeUpdate()==1;
     }
     
    public static List<String> getAllReceptionistUserId()throws SQLException{
         Connection conn=DBConnection.getConnection();
        Statement stmt=conn.createStatement();
        ResultSet rs=stmt.executeQuery("Select userid from users where usertype='Receptionist' order by userid");
       List<String> receptionistList=new ArrayList<>();
        while(rs.next())
        {
          String id=rs.getString(1);
          receptionistList.add(id);
        }
        return receptionistList;
    }
}

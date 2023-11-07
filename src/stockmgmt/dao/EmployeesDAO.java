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
import java.util.List;
import stockmgmt.dbutil.DBConnection;
import stockmgmt.pojo.EmployeesPojo;
import stockmgmt.pojo.UsersPojo;


public class EmployeesDAO {
    public static String getNextEmpId()throws SQLException
    {
       Connection conn=DBConnection.getConnection();
       Statement stmt=conn.createStatement();
       ResultSet rs=stmt.executeQuery("Select max(empid) from employees ");
       rs.next();
       String empid=rs.getString(1);
       int empno=Integer.parseInt(empid.substring(1));
       empno=empno+1;
       return "E"+empno;
    }
    public static boolean addEmployee(EmployeesPojo e)throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("Insert into employees values(?,?,?,?)");
        String empid=e.getEmpid();
        String empname=e.getEmpname();
        String job=e.getJob();
        double salary=e.getSalary();
        ps.setString(1,empid);
        ps.setString(2,empname);
        ps.setString(3,job);
        ps.setDouble(4, salary);
        int result=ps.executeUpdate();
        return result==1;
        
    }
    public static List<EmployeesPojo> getAllEmployees()throws SQLException
    {
       Connection conn=DBConnection.getConnection();
        Statement stmt=conn.createStatement();
       ResultSet rs=stmt.executeQuery("Select * from employees order by empid ");
       ArrayList <EmployeesPojo> emplist=new ArrayList<>();
       while(rs.next())
       {
           EmployeesPojo emp=new EmployeesPojo();
           emp.setEmpid(rs.getString(1));
           emp.setEmpname(rs.getString(2));
           emp.setJob(rs.getString(3));
           emp.setSalary(rs.getDouble(4));
           emplist.add(emp);
       }
                  return emplist;

    }
    public static List<String> getAllEmpid()throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        Statement stmt=conn.createStatement();
       ResultSet rs=stmt.executeQuery("Select empid from employees order by empid ");
        ArrayList <String> allidlist=new ArrayList<String>();
       while(rs.next())
       {
           allidlist.add(rs.getString(1));
    }
    return allidlist;
}
     public static EmployeesPojo findEmployeeById(String eid)throws SQLException
     {
          Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("Select * from employees where empid=?");
       
        
        ps.setString(1,eid);
       
        ResultSet rs=ps.executeQuery();
        rs.next();
        EmployeesPojo e=new EmployeesPojo();
        e.setEmpid(rs.getString(1));
         e.setEmpname(rs.getString(2));
          e.setJob(rs.getString(3));
           e.setSalary(rs.getDouble(4));
        return e;
     }
     public static boolean removeEmployee(String eid)throws SQLException
     {
          Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("delete from employees where empid=?");
        ps.setString(1, eid);
     int x=ps.executeUpdate();
     return x==1;
     }
     public static boolean updateEmployee(EmployeesPojo e)throws SQLException
     {
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("Update Employees set empname=?,job=?,salary=? where empid=?");  
        ps.setString(1, e.getEmpname());
        ps.setString(2, e.getJob());
        ps.setDouble(3, e.getSalary());
        ps.setString(4, e.getEmpid());
        int y=ps.executeUpdate();
        if(y==0)
            return false;
        else{
            boolean result=UsersDAO.isUserPresent(e.getEmpid());
            if (result==false)
                return true;
            
                  ps=conn.prepareStatement("Update Users set username=?,usertype=? where empid=?");  
                  ps.setString(1, e.getEmpname());
                  ps.setString(2, e.getJob());
                  ps.setString(3, e.getEmpid());
                  int z=ps.executeUpdate();
                  return z==1;
                  
            
            
        }
     }
}

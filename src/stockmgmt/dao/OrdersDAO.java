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
import stockmgmt.pojo.ProductsPojo;
import stockmgmt.pojo.UserProfile;

/**
 *
 * @author dell
 */
public class OrdersDAO {
    public static String getOrderId()throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("select max(order_id) from orders");
        rs.next();
        String oid=rs.getString(1);
        if(oid==null)
        {
            return "O-101";
        }
        int order=Integer.parseInt(oid.substring(2));
        order++;
        return "O-"+order;
    }
    public static boolean addOrder(ArrayList<ProductsPojo> al, String oid)throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("Insert into orders values(?,?,?,?)");
        int count=0;
        for(ProductsPojo p:al){
        ps.setString(1, oid);
        ps.setString(2, p.getProductId());
        ps.setInt(3, p.getQuantity());
        ps.setString(4, UserProfile.getUserid());
        count=count+ps.executeUpdate();
    }
        return count==al.size();
    } 
    public static List<String> getAllOrderIDs()throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("Select distinct(order_id) from orders order by order_id");
        ArrayList<String> oid=new ArrayList<String>();
        while(rs.next())
        {
            oid.add(rs.getString(1));
        }
        return oid;
    }
    public static ArrayList<ProductsPojo> getOrderDetails(String order_id)throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("Select orders.p_id, p_name, p_companyname, p_price, our_price,p_tax, orders.quantity from products, orders where products.p_id=orders.p_id and order_id=? order by p_id");
        ps.setString(1, order_id);
        ResultSet rs=ps.executeQuery();
        
        ArrayList<ProductsPojo> pl=new ArrayList<>();
        while(rs.next())
        {
            ProductsPojo p=new ProductsPojo();
            p.setProductId(rs.getString(1));
            p.setProductName(rs.getString(2));
            p.setProductCompany(rs.getString(3));
            p.setProductPrice(rs.getDouble(4));
            p.setOurPrice(rs.getDouble(5));
            p.setTax(rs.getInt(6));
            p.setQuantity(rs.getInt(7));
            pl.add(p);
        }
        return pl;
    }
       public static List<String> getReceptionistOrderIDs()throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("Select distinct(order_id) from orders where userid=? order by order_id");
        ps.setString(1,UserProfile.getUserid());
        ResultSet rs=ps.executeQuery();
        ArrayList<String> oid=new ArrayList<String>();
        while(rs.next())
        {
            oid.add(rs.getString(1));
        }
        return oid;
    }
}

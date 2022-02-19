
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
public class Connect {
    public static Connection conn = null ;
    public static String UserID = "";
    public static String name = "";
    public static String getConnection(){
        String conString = "jdbc:sqlserver://DESKTOP-LQRS80Q\\SQLEXPRESS;databaseName=GAMETTT;user=hiep;password=123";
        String kq = "";
        try {
                conn = DriverManager.getConnection(conString);
            } catch (SQLException ex) {
                kq = "Lỗi kết nối CSDL: "+ex;
            }
        return kq;
    }
    public static boolean setQuery(String cauLenh){
        if(conn != null){
            try{
                PreparedStatement p = conn.prepareStatement(cauLenh);
                int ketQua = p.executeUpdate();
                return ketQua >= 0;
            }catch(SQLException ex){
                ex.printStackTrace();
                return false;
            }
        }else
            return false;
    }
    public static String MD5(String str){
        String kq;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            BigInteger BI = new BigInteger(1,md.digest());
            kq = BI.toString(16);
        } catch (NoSuchAlgorithmException ex) {
            kq = "";
        }
        return kq;
    }
    
}

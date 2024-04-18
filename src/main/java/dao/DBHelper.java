package dao;

import java.sql.*;
import java.util.*;
import java.io.*;

	// C:/oracle_wallet/Wallet_goodeeShop
public class DBHelper {
	public static Connection getConnection() throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = null;
		
		// local에서 설정 파일 불러오기
		String dbUrl = "jdbc:oracle:thin:@goodeeshop_high?TNS_ADMIN=c:/oracle_wallet/Wallet_goodeeShop";
		String dbUser = "admin";
		
		FileReader fr = new FileReader("D:\\dev\\auth\\oracle.properties");
		Properties prop = new Properties();
		prop.load(fr);
		String dbPw = prop.getProperty("pw");

		conn = DriverManager.getConnection(dbUrl, dbUser, dbPw);
		
		return conn;
	}
	
	// getConnetcion() 디버깅
	public static void main(String[] args) throws Exception {
		Connection conn = new DBHelper().getConnection();
		System.out.println("conn: " + conn);
	}
	
}

package com.dh.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBOper {
Connection conn=null;
PreparedStatement ps=null;
ResultSet rs=null;
public Connection getconection(String dbname,String username,String password){
	try {
		Class.forName("com.mysql.jdbc.Driver");
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	String url="jdbc:mysql://localhost:3306/"+dbname+"?useUnicode=true&&characterEncoding=utf-8";
	try {
		conn=DriverManager.getConnection(url, username, password);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return conn;
	
	
}
public ResultSet dbQuery(String sql,String[]param){
	try {
		ps=conn.prepareStatement(sql);
		for (int i = 0; i < param.length; i++) {
			ps.setString(i+1, param[i]);
			
		}
		rs=ps.executeQuery();
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return rs;
	
	
}

public boolean dbupdate(String sql,String[]param){
     int n=0;
	try {
		ps=conn.prepareStatement(sql);
		for (int i = 0; i < param.length; i++) {
			ps.setString(i+1, param[i]);
			
		}
		n=ps.executeUpdate();
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	if(n==0){
		return false;
	}else{
		return true;
	}
}

public void closeall(){
	if(rs!=null){
		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	if(ps!=null){
		try {
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	if(conn!=null){
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
}

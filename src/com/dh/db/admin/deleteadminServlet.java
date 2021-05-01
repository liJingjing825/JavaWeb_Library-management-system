package com.dh.db.admin;

import java.io.IOException;
import java.io.PrintWriter;
import  java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/deleteadminServlet")
public class deleteadminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deleteadminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//设置请求和响应的编码方式
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//PrintWriter out=response.getWriter();
		//把页面数据从请求对象中取出来
		String username1=request.getParameter("username");
		
		

		try {
			            Class.forName("com.mysql.jdbc.Driver");//加载数据库驱动
			            //System.out.println("加载数据库驱动成功");
			             String url="jdbc:mysql://localhost:3306/dh";//声明自己的数据库test的url
			             String user="root";//声明自己的数据库账号
			             String password="root";//声明自己的数据库密码
			             //建立数据库连接，获得连接对象conn
			             Connection conn=DriverManager.getConnection(url,user,password);
			             //System.out.println("连接数据库成功");
			             String sql="delete from admin where username=?";
			     		PreparedStatement ps=conn.prepareStatement(sql);
			     			ps.setString(1,username1);
			     			int n=ps.executeUpdate();
			     			//ResultSet rs=ps.executeQuery();
			             //String sql="delete from admin where username=";//生成一条sql语句
			             //Statement stmt=conn.createStatement();//创建Statement对象
			           //stmt.executeUpdate(sql);//执行sql语句
			     			if(n>0){
			     				JOptionPane.showMessageDialog(null,"删除成功");
			     				response.sendRedirect("deleteadmin.html");
			     				conn.close();}
			     			else{
			             
			     				JOptionPane.showMessageDialog(null,"删除失败");
			     				response.sendRedirect("deleteadmin.html");}//关闭数据库的连接
			        } catch (ClassNotFoundException e) {
			            // TODO Auto-generated catch block
			             e.printStackTrace();
			         } catch (SQLException e) {
			             // TODO Auto-generated catch block
			             e.printStackTrace();
			         }
		
	}
		
		
		

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}


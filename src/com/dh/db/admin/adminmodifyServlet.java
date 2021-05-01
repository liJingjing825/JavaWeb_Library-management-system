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
@WebServlet("/adminmodifyServlet")
public class adminmodifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public adminmodifyServlet() {
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
		String pass=request.getParameter("password");
		String ppass=request.getParameter("newpassword");

		try {
						Class.forName("com.mysql.jdbc.Driver");//加载数据库驱动
			            //System.out.println("加载数据库驱动成功");
			            String url="jdbc:mysql://localhost:3306/dh";//声明数据库test的url
			            String user="root";//数据库账号
			            String password="root";//数据库密码
			             //建立数据库连接，获得连接对象conn
			            Connection conn=DriverManager.getConnection(url, user, password);
			            
			            String sql="select * from admin where username=?&&userpass=?";
			     		PreparedStatement ps=conn.prepareStatement(sql);
			     		ps.setString(1,username1);
			     		ps.setString(2,pass);
			     		ResultSet rs=ps.executeQuery();
			     		if(rs!=null&&rs.next()){
			     				String sql1="update admin set userpass='"+ppass+"' where username='"+username1+"'";//生成一条mysql语句
			     				Statement stmt=conn.createStatement();//创建一个Statement对象
			     				stmt.executeUpdate(sql1);//执行SQL语句
			     				JOptionPane.showMessageDialog(null,"修改成功");
			     				response.sendRedirect("adminmodify.html");
			     				
			     				conn.close();
			     			}else{
			     				JOptionPane.showMessageDialog(null,"不存在此管理员或密码错误");
			     				response.sendRedirect("adminmodify.html");}
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


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
@WebServlet("/adminaddServlet")
public class adminaddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public adminaddServlet() {
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
		PrintWriter out=response.getWriter();
		//把页面数据从请求对象中取出来
		String username=request.getParameter("username");
		String pass=request.getParameter("password");
		String ppass=request.getParameter("ppassword");
		//判断用户是否存在 到数据库中进行查询
		//1加载驱动  2得到数据库连接对象  3获取preparedstatement  对象
		//4 执行sql语句 5处理查询结果 6关闭
		if(username.length()>0&&pass.equals(ppass) ){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			String url="jdbc:mysql://localhost:3306/dh?useUnicode=true&&characterEncoding=utf-8";
		Connection conn=(Connection) DriverManager.getConnection(url, "root", "root");
		
		 String sql="select * from admin where username=?&&userpass=?";
		PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1,username);
			ps.setString(2,pass);
			ResultSet rs=ps.executeQuery();
			if(rs!=null&&rs.next()){
				JOptionPane.showMessageDialog(null,"管理员已注册");
				response.sendRedirect("adminadd.html");
				
				
			}else{
				
				//登录失败
				//插入用户
				Date date1=new Date();
				SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				String date=sd.format(date1);
				String sql1="insert into admin (username,userpass,regtime) values(?,?,?)";
				PreparedStatement ps1=conn.prepareStatement(sql1);
				ps1.setString(1, username);
				ps1.setString(2, pass);
				ps1.setString(3, date);
				int n=ps1.executeUpdate();
				if(n>0){
					JOptionPane.showMessageDialog(null,"注册成功");
					response.sendRedirect("adminadd.html");
					
				}else{
					JOptionPane.showMessageDialog(null,"注册失败");
					response.sendRedirect("adminadd.html");
				}
				}
				
				}catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
			
		
		}
		else{
			JOptionPane.showMessageDialog(null,"注册失败");
			response.sendRedirect("adminadd.html");
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


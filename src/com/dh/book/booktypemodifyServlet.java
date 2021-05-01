package com.dh.book;

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
@WebServlet("/booktypemodifyServlet")
public class booktypemodifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public booktypemodifyServlet() {
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
		String typename=request.getParameter("typename");
		String days=request.getParameter("days");
		String fk=request.getParameter("fk");
		
		
		try {
						Class.forName("com.mysql.jdbc.Driver");//加载数据库驱动
			            //System.out.println("加载数据库驱动成功");
			            String url="jdbc:mysql://localhost:3306/dh";//声明数据库test的url
			            String user="root";//数据库账号
			            String password="root";//数据库密码
			             //建立数据库连接，获得连接对象conn
			            Connection conn=DriverManager.getConnection(url, user, password);
			    		
			    		 String sql="select * from booktype where typename=?";
			    		PreparedStatement ps=conn.prepareStatement(sql);
			    			ps.setString(1,typename);
			    			ResultSet rs=ps.executeQuery();
			    			if(rs!=null&&rs.next()){
			                    if(days==""){
			     				String sql1="update booktype set fk='"+fk+"' where typename='"+typename+"'";//生成一条mysql语句
			     				Statement stmt=conn.createStatement();//创建一个Statement对象
			     				stmt.executeUpdate(sql1);//执行SQL语句
			     				JOptionPane.showMessageDialog(null,"修改成功");
			     				response.sendRedirect("booktypemodify.html");
			     				conn.close();
			                    }
			                    else {if(fk==""){
			                    	String sql1="update booktype set days='"+days+"' where typename='"+typename+"'";//生成一条mysql语句
				     				Statement stmt=conn.createStatement();//创建一个Statement对象
				     				stmt.executeUpdate(sql1);//执行SQL语句
				     				JOptionPane.showMessageDialog(null,"修改成功");
				     				response.sendRedirect("booktypemodify.html");
				     				conn.close();
			                    }else{
			                    	String sql2="update booktype set days='"+days+"' where typename='"+typename+"'";//生成一条mysql语句
			                    	String sql1="update booktype set fk='"+fk+"'where typename='"+typename+"'";//生成一条mysql语句
				     				Statement stmt=conn.createStatement();//创建一个Statement对象
				     				stmt.executeUpdate(sql1);//执行SQL语句
				     				stmt.executeUpdate(sql2);//执行SQL语句
				     				JOptionPane.showMessageDialog(null,"修改成功");
				     				response.sendRedirect("booktypemodify.html");
				     				conn.close();
			                    }
			                    }
			     				}
			    			else{
			    				JOptionPane.showMessageDialog(null,"图书信息不正确");
			     				response.sendRedirect("booktypemodify.html");
			    			}
		}
			          catch (ClassNotFoundException e) {
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


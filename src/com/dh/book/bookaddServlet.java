package com.dh.book;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

/**
 * Servlet implementation class Regist
 */
@WebServlet("/bookaddServlet")
public class bookaddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public bookaddServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				//设置请求和响应的编码方式
				request.setCharacterEncoding("utf-8");
				response.setContentType("text/html;charset=utf-8");
				String id=request.getParameter("id");
				String bookname=request.getParameter("bookname");
				String price=request.getParameter("price");
				String publisherID=request.getParameter("publisherID");
				String count=request.getParameter("count");
				String typename=request.getParameter("typename");
				//判断用户是否存在     到数据库中进行查询  
				
				//数据操作   1.加载驱动 2.得到数据库的连接对象 3.获取preparedstatement对象
				//4.执行sql语句  5.处理查询结果 6.关闭
				
				//加载驱动 
				if(bookname.length()>0&&price.length()>0&&publisherID.length()>0&&count.length()>0&&id.length()>0){
				try {
					Class.forName("com.mysql.jdbc.Driver");
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//得到数据库连接对象
				String url="jdbc:mysql://localhost:3306/dh?useUnicode=true&&characterEncoding=utf-8";
				try {
					Connection conn=DriverManager.getConnection(url, "root", "root");
					 String sql3="select * from booktype where typename=?";
						PreparedStatement ps3=conn.prepareStatement(sql3);
							ps3.setString(1,typename);
							
							ResultSet rs3=ps3.executeQuery();
							if(rs3!=null&&rs3.next()){
					
					
					
					 String sql="select * from books where bookName=?&&id=?";
						PreparedStatement ps=conn.prepareStatement(sql);
							ps.setString(1,bookname);
							ps.setString(2,id);
							ResultSet rs=ps.executeQuery();
							if(rs!=null&&rs.next()){
								JOptionPane.showMessageDialog(null,"图书已存在");
								response.sendRedirect("addbook.html");
								
								
							}else{
							
								String sql2="select * from books where bookName!=?&&id=?";
								PreparedStatement ps2=conn.prepareStatement(sql2);
									ps2.setString(1,bookname);
									ps2.setString(2,id);
									ResultSet rs2=ps2.executeQuery();
									if(rs2!=null&&rs2.next()){
										JOptionPane.showMessageDialog(null,"图书编号已使用");
										response.sendRedirect("addbook.html");}
										else{
				
						String sql1="insert into books (id,bookName,publisherID,count,price,typename) values(?,?,?,?,?,?)";
						PreparedStatement ps1=conn.prepareStatement(sql1);
						ps1.setString(1, id);
						ps1.setString(2, bookname);
						ps1.setString(3, publisherID);
						ps1.setString(4, count);
						ps1.setString(5, price);
						ps1.setString(6, typename);
						int n=ps1.executeUpdate();
						if(n>0){
							JOptionPane.showMessageDialog(null,"添加成功");
							response.sendRedirect("addbook.html");
						
							
						}
						else{
							JOptionPane.showMessageDialog(null,"添加失败");
							response.sendRedirect("addbook.html");
							
						}
										}
									
										}
						
							}else{
								JOptionPane.showMessageDialog(null,"无此图书类别");
								response.sendRedirect("addbook.html");
								
							}
					
					
				
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}else{
					JOptionPane.showMessageDialog(null,"每一项都不能为空添加失败");
					response.sendRedirect("addbook.html");
				}
				
			
	}
		
	}



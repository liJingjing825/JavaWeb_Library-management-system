package com.dh.book;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

/**
 * Servlet implementation class Regist
 */
@WebServlet("/bookloanedoutServlet")
public class bookloanedoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public bookloanedoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				//设置请求和响应的编码方式
				request.setCharacterEncoding("utf-8");
				response.setContentType("text/html;charset=utf-8");
				//把页面数据从请求对象中取出来
				PrintWriter out=response.getWriter();
				String readername=request.getParameter("readername");
				String readerid=request.getParameter("readerid");
				String bookname=request.getParameter("bookname");
				String bookid=request.getParameter("bookid");
				//判断用户是否存在     到数据库中进行查询  
				//数据操作   1.加载驱动 2.得到数据库的连接对象 3.获取preparedstatement对象
				//4.执行sql语句  5.处理查询结果 6.关闭
				//加载驱动 
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
					 String sql="select * from reader where id=?&&name=?";
						PreparedStatement ps=conn.prepareStatement(sql);//prepatedstatement可以写带参数的sql语句,继承statement
							ps.setString(1,readerid);
							ps.setString(2,readername);
							ResultSet rs=ps.executeQuery();//处理查询结果集
							if(rs!=null&&rs.next()){
								String sql1="select * from books where id=?&&bookName=?";
								PreparedStatement ps1=conn.prepareStatement(sql1);
									ps1.setString(1,bookid);
									ps1.setString(2,bookname);
									ResultSet rs1=ps1.executeQuery();
									if(rs1!=null&&rs1.next()){
										int count = rs1.getInt("count");
										//JOptionPane.showMessageDialog(null,count);
										count=count-1;
										String sql3="update books set count='"+count+"' where id='"+bookid+"'&&bookName='"+bookname+"'";//生成一条mysql语句
					     				Statement stmt=conn.createStatement();//创建一个Statement对象
					     				stmt.executeUpdate(sql3);//执行SQL语句
										
										
								Date date1=new Date();
								SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
								String date=sd.format(date1);
								String sql2="insert into bookLoaned (readerid,readername,bookid,bookname,time) values(?,?,?,?,?)";
								PreparedStatement ps2=conn.prepareStatement(sql2);
								ps2.setString(1, readerid);
								ps2.setString(2,readername);
								ps2.setString(3, bookid);
								ps2.setString(4, bookname);
								ps2.setString(5, date);
								int n=ps2.executeUpdate();
								if(n>0){
									JOptionPane.showMessageDialog(null,"借阅成功");//弹出消息提示框,null在中间显示
									response.sendRedirect("bookloanedout.html");//重定向,客户端,服务器两次请求,两次响应,客户端浏览器地址栏会变为新地址
									
								}else{
									JOptionPane.showMessageDialog(null,"添加失败");
									
								}
							}else{
								JOptionPane.showMessageDialog(null,"图书信息不正确");
								response.sendRedirect("bookloanedout.html");
						
							}
					
							}else{
								JOptionPane.showMessageDialog(null,"无此读者信息");
								response.sendRedirect("bookloanedout.html");}
				
					
				}
				
				catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		
		
	}



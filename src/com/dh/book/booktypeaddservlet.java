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
@WebServlet("/booktypeaddservlet")
public class booktypeaddservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public booktypeaddservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
				request.setCharacterEncoding("utf-8");
				response.setContentType("text/html;charset=utf-8");
				
				
				String typename=request.getParameter("typename");
				String days=request.getParameter("days");
				String fk=request.getParameter("fk");
				
				
			
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
					 String sql="select * from booktype where typename=?";
						PreparedStatement ps=conn.prepareStatement(sql);
							ps.setString(1,typename);
							ResultSet rs=ps.executeQuery();
							if(rs!=null&&rs.next()){
								JOptionPane.showMessageDialog(null,"图书类别已存在");
								response.sendRedirect("booktypeadd.html");
								
							}else{
				           
						String sql1="insert into booktype (typename,days,fk) values(?,?,?)";
						PreparedStatement ps1=conn.prepareStatement(sql1);
						ps1.setString(1, typename);
						ps1.setString(2, days);
						ps1.setString(3, fk);
						int n=ps1.executeUpdate();
						if(n>0){
							JOptionPane.showMessageDialog(null,"添加成功");
							response.sendRedirect("booktypeadd.html");
							
						}else{
							JOptionPane.showMessageDialog(null,"添加失败");
							
						}
						
							}
					
					
				
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		
		
	}



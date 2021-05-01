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
				//�����������Ӧ�ı��뷽ʽ
				request.setCharacterEncoding("utf-8");
				response.setContentType("text/html;charset=utf-8");
				String id=request.getParameter("id");
				String bookname=request.getParameter("bookname");
				String price=request.getParameter("price");
				String publisherID=request.getParameter("publisherID");
				String count=request.getParameter("count");
				String typename=request.getParameter("typename");
				//�ж��û��Ƿ����     �����ݿ��н��в�ѯ  
				
				//���ݲ���   1.�������� 2.�õ����ݿ�����Ӷ��� 3.��ȡpreparedstatement����
				//4.ִ��sql���  5.�����ѯ��� 6.�ر�
				
				//�������� 
				if(bookname.length()>0&&price.length()>0&&publisherID.length()>0&&count.length()>0&&id.length()>0){
				try {
					Class.forName("com.mysql.jdbc.Driver");
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//�õ����ݿ����Ӷ���
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
								JOptionPane.showMessageDialog(null,"ͼ���Ѵ���");
								response.sendRedirect("addbook.html");
								
								
							}else{
							
								String sql2="select * from books where bookName!=?&&id=?";
								PreparedStatement ps2=conn.prepareStatement(sql2);
									ps2.setString(1,bookname);
									ps2.setString(2,id);
									ResultSet rs2=ps2.executeQuery();
									if(rs2!=null&&rs2.next()){
										JOptionPane.showMessageDialog(null,"ͼ������ʹ��");
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
							JOptionPane.showMessageDialog(null,"��ӳɹ�");
							response.sendRedirect("addbook.html");
						
							
						}
						else{
							JOptionPane.showMessageDialog(null,"���ʧ��");
							response.sendRedirect("addbook.html");
							
						}
										}
									
										}
						
							}else{
								JOptionPane.showMessageDialog(null,"�޴�ͼ�����");
								response.sendRedirect("addbook.html");
								
							}
					
					
				
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}else{
					JOptionPane.showMessageDialog(null,"ÿһ�����Ϊ�����ʧ��");
					response.sendRedirect("addbook.html");
				}
				
			
	}
		
	}



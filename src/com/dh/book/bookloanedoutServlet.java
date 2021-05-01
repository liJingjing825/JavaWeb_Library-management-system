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
				//�����������Ӧ�ı��뷽ʽ
				request.setCharacterEncoding("utf-8");
				response.setContentType("text/html;charset=utf-8");
				//��ҳ�����ݴ����������ȡ����
				PrintWriter out=response.getWriter();
				String readername=request.getParameter("readername");
				String readerid=request.getParameter("readerid");
				String bookname=request.getParameter("bookname");
				String bookid=request.getParameter("bookid");
				//�ж��û��Ƿ����     �����ݿ��н��в�ѯ  
				//���ݲ���   1.�������� 2.�õ����ݿ�����Ӷ��� 3.��ȡpreparedstatement����
				//4.ִ��sql���  5.�����ѯ��� 6.�ر�
				//�������� 
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
					 String sql="select * from reader where id=?&&name=?";
						PreparedStatement ps=conn.prepareStatement(sql);//prepatedstatement����д��������sql���,�̳�statement
							ps.setString(1,readerid);
							ps.setString(2,readername);
							ResultSet rs=ps.executeQuery();//�����ѯ�����
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
										String sql3="update books set count='"+count+"' where id='"+bookid+"'&&bookName='"+bookname+"'";//����һ��mysql���
					     				Statement stmt=conn.createStatement();//����һ��Statement����
					     				stmt.executeUpdate(sql3);//ִ��SQL���
										
										
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
									JOptionPane.showMessageDialog(null,"���ĳɹ�");//������Ϣ��ʾ��,null���м���ʾ
									response.sendRedirect("bookloanedout.html");//�ض���,�ͻ���,��������������,������Ӧ,�ͻ����������ַ�����Ϊ�µ�ַ
									
								}else{
									JOptionPane.showMessageDialog(null,"���ʧ��");
									
								}
							}else{
								JOptionPane.showMessageDialog(null,"ͼ����Ϣ����ȷ");
								response.sendRedirect("bookloanedout.html");
						
							}
					
							}else{
								JOptionPane.showMessageDialog(null,"�޴˶�����Ϣ");
								response.sendRedirect("bookloanedout.html");}
				
					
				}
				
				catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		
		
	}



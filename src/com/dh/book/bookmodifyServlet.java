package com.dh.book;

import java.io.IOException;
import  java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/bookmodifyServlet")
public class bookmodifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public bookmodifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//�����������Ӧ�ı��뷽ʽ
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//PrintWriter out=response.getWriter();
		//��ҳ�����ݴ����������ȡ����
		String id=request.getParameter("id");
		String bookname=request.getParameter("bookname");
		//String publisherID=request.getParameter("publisherID");
		String price=request.getParameter("price");
		String count=request.getParameter("count");
		
		try {
						Class.forName("com.mysql.jdbc.Driver");//�������ݿ�����
			            //System.out.println("�������ݿ������ɹ�");
			            String url="jdbc:mysql://localhost:3306/dh";//�������ݿ�test��url
			            String user="root";//���ݿ��˺�
			            String password="root";//���ݿ�����
			             //�������ݿ����ӣ�������Ӷ���conn
			            Connection conn=DriverManager.getConnection(url, user, password);
			    		
			    		 String sql="select * from books where bookName=?&&id=?";
			    		PreparedStatement ps=conn.prepareStatement(sql);
			    			ps.setString(1,bookname);
			    			ps.setString(2,id);
			    			ResultSet rs=ps.executeQuery();
			    			if(rs!=null&&rs.next()){
			                    if(count==""){
			     				String sql1="update books set price='"+price+"' where bookName='"+bookname+"'&&id='"+id+"'";//����һ��mysql���
			     				Statement stmt=conn.createStatement();//����һ��Statement����
			     				stmt.executeUpdate(sql1);//ִ��SQL���
			     				JOptionPane.showMessageDialog(null,"�޸ĳɹ�");
			     				response.sendRedirect("bookmodify.html");
			     				conn.close();
			                    }
			                    else {if(price==""){
			                    	String sql1="update books set count='"+count+"' where bookName='"+bookname+"'&&id='"+id+"'";//����һ��mysql���
				     				Statement stmt=conn.createStatement();//����һ��Statement����
				     				stmt.executeUpdate(sql1);//ִ��SQL���
				     				JOptionPane.showMessageDialog(null,"�޸ĳɹ�");
				     				response.sendRedirect("bookmodify.html");
				     				conn.close();
			                    }else{
			                    	String sql2="update books set price='"+price+"' where bookName='"+bookname+"'&&id='"+id+"'";//����һ��mysql���
			                    	String sql1="update books set count='"+count+"'where bookName='"+bookname+"'&&id='"+id+"'";//����һ��mysql���
				     				Statement stmt=conn.createStatement();//����һ��Statement����
				     				stmt.executeUpdate(sql1);//ִ��SQL���
				     				stmt.executeUpdate(sql2);//ִ��SQL���
				     				JOptionPane.showMessageDialog(null,"�޸ĳɹ�");
				     				response.sendRedirect("bookmodify.html");
				     				conn.close();
			                    }
			                    }
			     				}
			    			else{
			    				JOptionPane.showMessageDialog(null,"ͼ����Ϣ����ȷ");
			     				response.sendRedirect("bookmodify.html");
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


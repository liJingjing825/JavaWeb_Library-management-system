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
		//�����������Ӧ�ı��뷽ʽ
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		//��ҳ�����ݴ����������ȡ����
		String username=request.getParameter("username");
		String pass=request.getParameter("password");
		String ppass=request.getParameter("ppassword");
		//�ж��û��Ƿ���� �����ݿ��н��в�ѯ
		//1��������  2�õ����ݿ����Ӷ���  3��ȡpreparedstatement  ����
		//4 ִ��sql��� 5�����ѯ��� 6�ر�
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
				JOptionPane.showMessageDialog(null,"����Ա��ע��");
				response.sendRedirect("adminadd.html");
				
				
			}else{
				
				//��¼ʧ��
				//�����û�
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
					JOptionPane.showMessageDialog(null,"ע��ɹ�");
					response.sendRedirect("adminadd.html");
					
				}else{
					JOptionPane.showMessageDialog(null,"ע��ʧ��");
					response.sendRedirect("adminadd.html");
				}
				}
				
				}catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
			
		
		}
		else{
			JOptionPane.showMessageDialog(null,"ע��ʧ��");
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


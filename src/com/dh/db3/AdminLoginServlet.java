package com.dh.db3;

import java.io.IOException;
import java.io.PrintWriter;
import  java.sql.*; 

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLoginServlet() {
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
		
		
		//�ж��û��Ƿ���� �����ݿ��н��в�ѯ
		//1��������  2�õ����ݿ����Ӷ���  3��ȡpreparedstatement  ����
		//4 ִ��sql��� 5�����ѯ��� 6�ر�
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			String url="jdbc:mysql://localhost:3306/dh?useUnicode=true&&characterEncoding=utf-8";
		Connection conn=(Connection) DriverManager.getConnection(url, "root", "root");
		 String sql="select username,userpass from admin where username=?&&userpass=?";
		PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1,username);
			ps.setString(2,pass);
			ResultSet rs=ps.executeQuery();
			if(rs!=null&&rs.next()){
			
				HttpSession session=request.getSession();
				session.setAttribute("adminuser",username);
				response.sendRedirect("adminMain.jsp");
				
			}else{
					JOptionPane.showMessageDialog(null,"����Ϊ����Ա���");
					response.sendRedirect("adminLogin.jsp");
					
				}
				
			
		
		} catch(Exception e){
			e.printStackTrace();
		}
		}
		
		

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}


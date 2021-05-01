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
		//设置请求和响应的编码方式
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		//把页面数据从请求对象中取出来
		String username=request.getParameter("username");
		String pass=request.getParameter("password");
		
		
		//判断用户是否存在 到数据库中进行查询
		//1加载驱动  2得到数据库连接对象  3获取preparedstatement  对象
		//4 执行sql语句 5处理查询结果 6关闭
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
					JOptionPane.showMessageDialog(null,"必须为管理员身份");
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


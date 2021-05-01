package com.dh.book;

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
@WebServlet("/bookreturn1Servlet")
public class bookreturn1Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public bookreturn1Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//获取页面的读者编号
		String id=request.getParameter("id");
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");//加载数据库驱动
            //System.out.println("加载数据库驱动成功");
            String url="jdbc:mysql://localhost:3306/dh";//声明数据库test的urljdbc:mysql://主机名称：连接端口/数据库的名称?参数=值
            String user="root";//数据库账号
            String password="root";//数据库密码
             //建立数据库连接，获得连接对象conn
            Connection conn=DriverManager.getConnection(url, user, password);
            
    	   String sql="select * from reader where id=?";
    		PreparedStatement ps=conn.prepareStatement(sql);//处理带参数的sql语句
    			ps.setString(1,id);
    			ResultSet rs=ps.executeQuery();
    			if(rs!=null&&rs.next()){
    				  //使用request对象的getSession()获取session，如果session不存在则创建一个
						HttpSession session=request.getSession();
						 //将数据存储到session中
						session.setAttribute("id",id);
						response.sendRedirect("bookreturn2.jsp");
				}
    			else{
    				JOptionPane.showMessageDialog(null,"无此读者");
     				response.sendRedirect("bookreturn1.html");
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


    			
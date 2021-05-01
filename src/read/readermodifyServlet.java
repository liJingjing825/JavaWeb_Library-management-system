package read;

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
@WebServlet("/readermodifyServlet")
public class readermodifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public readermodifyServlet() {
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
		//PrintWriter out=response.getWriter();
		//把页面数据从请求对象中取出来
		String name=request.getParameter("name");
		String age=request.getParameter("age");
		String id=request.getParameter("id");
		String idcard=request.getParameter("idcard");
		String tel=request.getParameter("tel");
		
		try {
						Class.forName("com.mysql.jdbc.Driver");//加载数据库驱动
			            //System.out.println("加载数据库驱动成功");
			            String url="jdbc:mysql://localhost:3306/dh";//声明数据库test的url
			            String user="root";//数据库账号
			            String password="root";//数据库密码
			             //建立数据库连接，获得连接对象conn
			            Connection conn=DriverManager.getConnection(url, user, password);
			    		
			    		 String sql="select * from reader where id=?&&name=?&&idcard=?";
			    		PreparedStatement ps=conn.prepareStatement(sql);
			    			ps.setString(1,id);
			    			ps.setString(2,name);
			    			ps.setString(3,idcard);
			    			ResultSet rs=ps.executeQuery();
			    			if(rs!=null&&rs.next()){
			    				Statement stmt=conn.createStatement();//创建一个Statement对象
			    				if(age==""){
			    					 String sql4="update reader set tel='"+tel+"'where id='"+id+"' &&name='"+name+"'&&idcard='"+idcard+"'" ;
			    					 stmt.executeUpdate(sql4);//执行SQL语句
			    				}else{
			    					if(tel==""){
			    						String sql3="update reader set age='"+age+"'where id='"+id+"' &&name='"+name+"'&&idcard='"+idcard+"'" ;
			    						stmt.executeUpdate(sql3);//执行SQL语句
			    					}else{
			    						String sql4="update reader set tel='"+tel+"'where id='"+id+"' &&name='"+name+"'&&idcard='"+idcard+"'" ;
				    					 stmt.executeUpdate(sql4);//执行SQL语句
				    					 String sql3="update reader set age='"+age+"'where id='"+id+"' &&name='"+name+"'&&idcard='"+idcard+"'" ;
				    					stmt.executeUpdate(sql3);//执行SQL语句
			    					}
			    				}
			    				
			    				
				     				JOptionPane.showMessageDialog(null,"修改成功");
				     				response.sendRedirect("readermodify.html");
				     				conn.close();
			                    }else{
			                    	JOptionPane.showMessageDialog(null,"读者信息不正确");
				     				response.sendRedirect("readermodify.html");
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


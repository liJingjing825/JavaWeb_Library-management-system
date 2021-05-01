package read;

import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
@WebServlet("/readeraddServlet")
public class readeraddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public readeraddServlet() {
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
				
				String name=request.getParameter("name");
				String age=request.getParameter("age");
				String id=request.getParameter("id");
				String idcard=request.getParameter("idcard");
				String tel=request.getParameter("tel");
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
					 String sql="select * from reader where id=?";
						PreparedStatement ps=conn.prepareStatement(sql);
							ps.setString(1,id);
							ResultSet rs=ps.executeQuery();
							if(rs!=null&&rs.next()){
								JOptionPane.showMessageDialog(null,"读者编号已使用");
								response.sendRedirect("readeradd.html");
								
								
							}else{
								String sql2="select * from reader where name=?&&idcard=?";
								PreparedStatement ps2=conn.prepareStatement(sql2);
									ps2.setString(1,name);
									ps2.setString(2,idcard);
									ResultSet rs2=ps2.executeQuery();
									if(rs2!=null&&rs2.next()){
										JOptionPane.showMessageDialog(null,"读者已存在");
										response.sendRedirect("readeradd.html");
										
										
									}else{
								
						Date date1=new Date();
						SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
						String date=sd.format(date1);
						String sql1="insert into reader (name,id,idcard,age,tel,time) values(?,?,?,?,?,?)";
						PreparedStatement ps1=conn.prepareStatement(sql1);
						ps1.setString(1, name);
						ps1.setString(2,id);
						ps1.setString(3, idcard);
						ps1.setString(4, age);
						ps1.setString(5, tel);
						ps1.setString(6, date);
						int n=ps1.executeUpdate();
						if(n>0){
							JOptionPane.showMessageDialog(null,"添加成功");
							response.sendRedirect("readeradd.html");
							
						}else{
							JOptionPane.showMessageDialog(null,"添加失败");
							
						}
						
							}
					
					
				
							}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		
		
	}



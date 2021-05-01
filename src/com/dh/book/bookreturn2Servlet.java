package com.dh.book;

import java.io.IOException;
import java.io.PrintWriter;
import  java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import com.sun.glass.ui.Pixels.Format;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/bookreturn2Servlet")
public class bookreturn2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public bookreturn2Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    //计算输入的两个日期之差 即为归还日期与借阅日期两个日期之差
    public static int differentDaysByMillisecond(Date date1,Date date2){
    	//获取当前日期
    	Calendar aCalendar = Calendar.getInstance();
    	
        aCalendar.setTime(date2);
        int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);
        
        aCalendar.setTime(date1);
        int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);//计算date1是一年当中的第几天
        //day2是借阅日期 day1是归还日期
        int days=day1-day2;
    	//返回两日期相差多少天
    	return days;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//设置请求和响应的编码方式
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		//把页面数据从请求对象中取出来
		String readerid=request.getParameter("readerid");
		String readername=request.getParameter("readername");
		String bookid=request.getParameter("bookid");
		String bookname=request.getParameter("bookname");
		try {
            Class.forName("com.mysql.jdbc.Driver");//加载数据库驱动
            //System.out.println("加载数据库驱动成功");
             String url="jdbc:mysql://localhost:3306/dh";//声明自己的数据库test的url
             String user="root";//声明自己的数据库账号
             String password="root";//声明自己的数据库密码
             //建立数据库连接，获得连接对象conn
             Connection conn=DriverManager.getConnection(url,user,password);
             
             String sql2="select * from bookloaned where readerid=?&&bookid=?";
				PreparedStatement ps2=conn.prepareStatement(sql2);
					ps2.setString(1,readerid);
					ps2.setString(2,bookid);
					ResultSet rs2=ps2.executeQuery();
					if(rs2!=null&&rs2.next()){
					//创建日期对象并且获取当前日期
				
					Date date1=new Date();
				//声明一个SimpleDateFormat变量sd，用于将自定义的日期格式（String类型）转换成format可识别的格式
					SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
					 //将date1根据转换后的格式转换回自定义的格式（String类型，再赋值给date（String类型）
					String date=sd.format(date1);
				   Date date2=sd.parse(date);
					
					SimpleDateFormat sd1=new SimpleDateFormat("yyyy-MM-dd");
					String time = rs2.getString("time");
				   Date date3=sd1.parse(time);		
					int time1=differentDaysByMillisecond(date3,date2);//得到了借书的总天数
								String sql3="select * from books where bookName=?&&id=?";
								PreparedStatement ps3=conn.prepareStatement(sql3);
									ps3.setString(1,bookname);
									ps3.setString(2,bookid);
									ResultSet rs3=ps3.executeQuery();
									if(rs3!=null&&rs3.next()){
										
										
										
										String typename = rs3.getString("typename");
										String sql6="select * from booktype where typename=?";
										PreparedStatement ps6=conn.prepareStatement(sql6);
											ps6.setString(1,typename);
											ResultSet rs6=ps6.executeQuery();
											if(rs6!=null&&rs6.next()){
												int days=rs6.getInt("days");
												
												if(time1>days){
										
													JOptionPane.showMessageDialog(null,"借阅时间超过"+(time1-days)+"天");
										
													String sql4="select * from booktype where typename=?";
													PreparedStatement ps4=conn.prepareStatement(sql4);
													ps4.setString(1,typename);
													ResultSet rs4=ps4.executeQuery();
													if(rs4!=null&&rs4.next()){
														int fk = rs4.getInt("fk");
														int fk2=fk*(time1-days);
														
														JOptionPane.showMessageDialog(null,"罚款"+fk2+"角");
							}else{
								JOptionPane.showMessageDialog(null,"没有此图书类别");
			     				response.sendRedirect("bookreturn2.jsp");
							}
								
						}
		
									String sql="delete from bookloaned where bookid=?&&bookname=?&&readerid=?";
						     		PreparedStatement ps=conn.prepareStatement(sql);
						     			ps.setString(1,bookid);
						     			ps.setString(2,bookname);
						     			ps.setString(3,readerid);
						     			
						     			int n=ps.executeUpdate();
						     			if(n>0){
						     				String sql1="select * from books where id=?&&bookName=?";
											PreparedStatement ps1=conn.prepareStatement(sql1);
												ps1.setString(1,bookid);
												ps1.setString(2,bookname);
												ResultSet rs1=ps1.executeQuery();
												if(rs1!=null&&rs1.next()){
													int count = rs1.getInt("count");
													count=count+1;
													String sql5="update books set count='"+count+"' where id='"+bookid+"'&&bookName='"+bookname+"'";//生成一条mysql语句
								     				Statement stmt=conn.createStatement();//创建一个Statement对象
								     				stmt.executeUpdate(sql5);//执行SQL语句
						     				JOptionPane.showMessageDialog(null,"归还成功");
						     				response.sendRedirect("bookreturn2.jsp");
						     			}else{
								             
						     				JOptionPane.showMessageDialog(null,"信息不正确,归还失败");
						     				response.sendRedirect("bookreturn2.jsp");}//关闭数据库的连接
						        }else{
						        	JOptionPane.showMessageDialog(null,"信息不正确，请重新填写");
				     				response.sendRedirect("bookreturn2.jsp");
						        }
								}else{
									JOptionPane.showMessageDialog(null,"没有此图书类别");
				     				response.sendRedirect("bookreturn2.jsp");}
								}else{
									JOptionPane.showMessageDialog(null,"没有此图书信息");
				     				response.sendRedirect("bookreturn2.jsp");
								}
						}else{
							JOptionPane.showMessageDialog(null,"信息不正确，请重新填写");
		     				response.sendRedirect("bookreturn2.jsp");
						}
					}catch (SQLException e) {
			             // TODO Auto-generated catch block
			             e.printStackTrace();
			         } catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			
				
} 

/**
* @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
*/
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//TODO Auto-generated method stub
doGet(request, response);
}

}


					
					
			

		
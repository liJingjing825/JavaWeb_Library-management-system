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
    //�����������������֮�� ��Ϊ�黹���������������������֮��
    public static int differentDaysByMillisecond(Date date1,Date date2){
    	//��ȡ��ǰ����
    	Calendar aCalendar = Calendar.getInstance();
    	
        aCalendar.setTime(date2);
        int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);
        
        aCalendar.setTime(date1);
        int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);//����date1��һ�굱�еĵڼ���
        //day2�ǽ������� day1�ǹ黹����
        int days=day1-day2;
    	//������������������
    	return days;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//�����������Ӧ�ı��뷽ʽ
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		//��ҳ�����ݴ����������ȡ����
		String readerid=request.getParameter("readerid");
		String readername=request.getParameter("readername");
		String bookid=request.getParameter("bookid");
		String bookname=request.getParameter("bookname");
		try {
            Class.forName("com.mysql.jdbc.Driver");//�������ݿ�����
            //System.out.println("�������ݿ������ɹ�");
             String url="jdbc:mysql://localhost:3306/dh";//�����Լ������ݿ�test��url
             String user="root";//�����Լ������ݿ��˺�
             String password="root";//�����Լ������ݿ�����
             //�������ݿ����ӣ�������Ӷ���conn
             Connection conn=DriverManager.getConnection(url,user,password);
             
             String sql2="select * from bookloaned where readerid=?&&bookid=?";
				PreparedStatement ps2=conn.prepareStatement(sql2);
					ps2.setString(1,readerid);
					ps2.setString(2,bookid);
					ResultSet rs2=ps2.executeQuery();
					if(rs2!=null&&rs2.next()){
					//�������ڶ����һ�ȡ��ǰ����
				
					Date date1=new Date();
				//����һ��SimpleDateFormat����sd�����ڽ��Զ�������ڸ�ʽ��String���ͣ�ת����format��ʶ��ĸ�ʽ
					SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
					 //��date1����ת����ĸ�ʽת�����Զ���ĸ�ʽ��String���ͣ��ٸ�ֵ��date��String���ͣ�
					String date=sd.format(date1);
				   Date date2=sd.parse(date);
					
					SimpleDateFormat sd1=new SimpleDateFormat("yyyy-MM-dd");
					String time = rs2.getString("time");
				   Date date3=sd1.parse(time);		
					int time1=differentDaysByMillisecond(date3,date2);//�õ��˽����������
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
										
													JOptionPane.showMessageDialog(null,"����ʱ�䳬��"+(time1-days)+"��");
										
													String sql4="select * from booktype where typename=?";
													PreparedStatement ps4=conn.prepareStatement(sql4);
													ps4.setString(1,typename);
													ResultSet rs4=ps4.executeQuery();
													if(rs4!=null&&rs4.next()){
														int fk = rs4.getInt("fk");
														int fk2=fk*(time1-days);
														
														JOptionPane.showMessageDialog(null,"����"+fk2+"��");
							}else{
								JOptionPane.showMessageDialog(null,"û�д�ͼ�����");
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
													String sql5="update books set count='"+count+"' where id='"+bookid+"'&&bookName='"+bookname+"'";//����һ��mysql���
								     				Statement stmt=conn.createStatement();//����һ��Statement����
								     				stmt.executeUpdate(sql5);//ִ��SQL���
						     				JOptionPane.showMessageDialog(null,"�黹�ɹ�");
						     				response.sendRedirect("bookreturn2.jsp");
						     			}else{
								             
						     				JOptionPane.showMessageDialog(null,"��Ϣ����ȷ,�黹ʧ��");
						     				response.sendRedirect("bookreturn2.jsp");}//�ر����ݿ������
						        }else{
						        	JOptionPane.showMessageDialog(null,"��Ϣ����ȷ����������д");
				     				response.sendRedirect("bookreturn2.jsp");
						        }
								}else{
									JOptionPane.showMessageDialog(null,"û�д�ͼ�����");
				     				response.sendRedirect("bookreturn2.jsp");}
								}else{
									JOptionPane.showMessageDialog(null,"û�д�ͼ����Ϣ");
				     				response.sendRedirect("bookreturn2.jsp");
								}
						}else{
							JOptionPane.showMessageDialog(null,"��Ϣ����ȷ����������д");
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


					
					
			

		
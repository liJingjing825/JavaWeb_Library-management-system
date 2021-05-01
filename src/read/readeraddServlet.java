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
				//�����������Ӧ�ı��뷽ʽ
				request.setCharacterEncoding("utf-8");
				response.setContentType("text/html;charset=utf-8");
				//��ҳ�����ݴ����������ȡ����
				
				String name=request.getParameter("name");
				String age=request.getParameter("age");
				String id=request.getParameter("id");
				String idcard=request.getParameter("idcard");
				String tel=request.getParameter("tel");
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
					 String sql="select * from reader where id=?";
						PreparedStatement ps=conn.prepareStatement(sql);
							ps.setString(1,id);
							ResultSet rs=ps.executeQuery();
							if(rs!=null&&rs.next()){
								JOptionPane.showMessageDialog(null,"���߱����ʹ��");
								response.sendRedirect("readeradd.html");
								
								
							}else{
								String sql2="select * from reader where name=?&&idcard=?";
								PreparedStatement ps2=conn.prepareStatement(sql2);
									ps2.setString(1,name);
									ps2.setString(2,idcard);
									ResultSet rs2=ps2.executeQuery();
									if(rs2!=null&&rs2.next()){
										JOptionPane.showMessageDialog(null,"�����Ѵ���");
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
							JOptionPane.showMessageDialog(null,"��ӳɹ�");
							response.sendRedirect("readeradd.html");
							
						}else{
							JOptionPane.showMessageDialog(null,"���ʧ��");
							
						}
						
							}
					
					
				
							}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		
		
	}



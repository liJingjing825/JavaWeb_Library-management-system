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
		//�����������Ӧ�ı��뷽ʽ
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//PrintWriter out=response.getWriter();
		//��ҳ�����ݴ����������ȡ����
		String name=request.getParameter("name");
		String age=request.getParameter("age");
		String id=request.getParameter("id");
		String idcard=request.getParameter("idcard");
		String tel=request.getParameter("tel");
		
		try {
						Class.forName("com.mysql.jdbc.Driver");//�������ݿ�����
			            //System.out.println("�������ݿ������ɹ�");
			            String url="jdbc:mysql://localhost:3306/dh";//�������ݿ�test��url
			            String user="root";//���ݿ��˺�
			            String password="root";//���ݿ�����
			             //�������ݿ����ӣ�������Ӷ���conn
			            Connection conn=DriverManager.getConnection(url, user, password);
			    		
			    		 String sql="select * from reader where id=?&&name=?&&idcard=?";
			    		PreparedStatement ps=conn.prepareStatement(sql);
			    			ps.setString(1,id);
			    			ps.setString(2,name);
			    			ps.setString(3,idcard);
			    			ResultSet rs=ps.executeQuery();
			    			if(rs!=null&&rs.next()){
			    				Statement stmt=conn.createStatement();//����һ��Statement����
			    				if(age==""){
			    					 String sql4="update reader set tel='"+tel+"'where id='"+id+"' &&name='"+name+"'&&idcard='"+idcard+"'" ;
			    					 stmt.executeUpdate(sql4);//ִ��SQL���
			    				}else{
			    					if(tel==""){
			    						String sql3="update reader set age='"+age+"'where id='"+id+"' &&name='"+name+"'&&idcard='"+idcard+"'" ;
			    						stmt.executeUpdate(sql3);//ִ��SQL���
			    					}else{
			    						String sql4="update reader set tel='"+tel+"'where id='"+id+"' &&name='"+name+"'&&idcard='"+idcard+"'" ;
				    					 stmt.executeUpdate(sql4);//ִ��SQL���
				    					 String sql3="update reader set age='"+age+"'where id='"+id+"' &&name='"+name+"'&&idcard='"+idcard+"'" ;
				    					stmt.executeUpdate(sql3);//ִ��SQL���
			    					}
			    				}
			    				
			    				
				     				JOptionPane.showMessageDialog(null,"�޸ĳɹ�");
				     				response.sendRedirect("readermodify.html");
				     				conn.close();
			                    }else{
			                    	JOptionPane.showMessageDialog(null,"������Ϣ����ȷ");
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


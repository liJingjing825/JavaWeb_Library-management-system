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
@WebServlet("/readerdeleteServlet")
public class readerdeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public readerdeleteServlet() {
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
		String id=request.getParameter("id");
		String name=request.getParameter("name");
		

		try {
			            Class.forName("com.mysql.jdbc.Driver");//�������ݿ�����
			            //System.out.println("�������ݿ������ɹ�");
			             String url="jdbc:mysql://localhost:3306/dh";//�����Լ������ݿ�test��url
			             String user="root";//�����Լ������ݿ��˺�
			             String password="root";//�����Լ������ݿ�����
			             //�������ݿ����ӣ�������Ӷ���conn
			             Connection conn=DriverManager.getConnection(url,user,password);
			             //System.out.println("�������ݿ�ɹ�");
			             String sql="delete from reader where id=?&&name=?";
			     		PreparedStatement ps=conn.prepareStatement(sql);
			     			ps.setString(1,id);
			     			ps.setString(2,name);
			     			int n=ps.executeUpdate();
			     			//ResultSet rs=ps.executeQuery();
			             //String sql="delete from admin where username=";//����һ��sql���
			             //Statement stmt=conn.createStatement();//����Statement����
			           //stmt.executeUpdate(sql);//ִ��sql���
			     			if(n>0){
			     				JOptionPane.showMessageDialog(null,"ɾ���ɹ�");
			     				response.sendRedirect("readerdelete.html");
			     				conn.close();}
			     			else{
			             
			     				JOptionPane.showMessageDialog(null,"������Ϣ����");
			     				response.sendRedirect("readerdelete.html");}//�ر����ݿ������
			        } catch (ClassNotFoundException e) {
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


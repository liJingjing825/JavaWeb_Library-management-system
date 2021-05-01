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
		//��ȡҳ��Ķ��߱��
		String id=request.getParameter("id");
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");//�������ݿ�����
            //System.out.println("�������ݿ������ɹ�");
            String url="jdbc:mysql://localhost:3306/dh";//�������ݿ�test��urljdbc:mysql://�������ƣ����Ӷ˿�/���ݿ������?����=ֵ
            String user="root";//���ݿ��˺�
            String password="root";//���ݿ�����
             //�������ݿ����ӣ�������Ӷ���conn
            Connection conn=DriverManager.getConnection(url, user, password);
            
    	   String sql="select * from reader where id=?";
    		PreparedStatement ps=conn.prepareStatement(sql);//�����������sql���
    			ps.setString(1,id);
    			ResultSet rs=ps.executeQuery();
    			if(rs!=null&&rs.next()){
    				  //ʹ��request�����getSession()��ȡsession�����session�������򴴽�һ��
						HttpSession session=request.getSession();
						 //�����ݴ洢��session��
						session.setAttribute("id",id);
						response.sendRedirect("bookreturn2.jsp");
				}
    			else{
    				JOptionPane.showMessageDialog(null,"�޴˶���");
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


    			
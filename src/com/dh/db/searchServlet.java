package com.dh.db;

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
@WebServlet("/searchServlet")
public class searchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public searchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String bookName=request.getParameter("bookName");
		String publisherID=request.getParameter("publisherID");
		String id=request.getParameter("id");
		
		
		try {
			
			
			if(bookName==""&&id==""){
				HttpSession session=request.getSession();
				//request.setAttribute("bookName",bookName);
				session.setAttribute("publisherID",publisherID);
				response.sendRedirect("searchbook3.jsp");
				//response.sendRedirect("/searchbook.jsp?bookName="+bookName);
			}else{
				if(id==""&&publisherID==""){
					HttpSession session=request.getSession();
					//request.setAttribute("bookName",bookName);
					session.setAttribute("bookName",bookName);
					response.sendRedirect("searchbook.jsp");
					//response.sendRedirect("/searchbook.jsp?bookName="+bookName);
					
				}else{
					if(bookName==""&&publisherID==""){
						HttpSession session=request.getSession();
						//request.setAttribute("bookName",bookName);
						session.setAttribute("id",id);
						response.sendRedirect("searchbook2.jsp");
						//response.sendRedirect("/searchbook.jsp?bookName="+bookName);
					}else{
						JOptionPane.showMessageDialog(null,"只能其中一项为关键词");
						response.sendRedirect("booksearch.html");
					}
				}
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


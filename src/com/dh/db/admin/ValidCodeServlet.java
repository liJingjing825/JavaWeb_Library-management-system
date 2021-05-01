package com.dh.db.admin;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ValidCodeServlet
 */
@WebServlet("/ValidCodeServlet")
public class ValidCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidCodeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//�ĵ���������ΪͼƬ��ʽ   ����4λ�����  ��ͼƬ��ʽ��ʾ
		response.setContentType("image/jpeg");//�����ĵ�����ΪͼƬ
		//��ȡ�������������������
		ServletOutputStream out=response.getOutputStream();
		//�������� ͼ��
		int width=80;
		int height=40;
		BufferedImage  imgbuf=new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g=imgbuf.createGraphics();//��ȡ2Dģʽ����

	g.setColor(getRandColor(100,250));//���ñ���ɫ
		g.fillRect(0, 0, width, height);//����ͼ��״���߶�
		//�������100��������
		Random r=new Random();
		g.setColor(getRandColor(160,200));
		for(int i=0;i<100;i++){
			int x=r.nextInt(width);
			int y=r.nextInt(height);
			int x1=r.nextInt(25);
			int y1=r.nextInt(12);
			g.drawOval(x, y, x+x1, y+y1);
		}
		//�������100�����ŵ�
		g.setColor(getRandColor(120,240));
		for (int i = 0; i < 100; i++) {
			int x=r.nextInt(width);
			int y=r.nextInt(height);
			g.drawOval(x,y,0,0);
		}
		//�������100�����ŵ�
	g.setColor(getRandcolor(120,240));
		for (int i = 0; i < 100; i++) {
		int x=r.nextInt(width);
		int y=r.nextInt(height);
		g.drawOval(x, y, 0, 0);
		
		}
		//�������0-9֮���4λ������֤��
		g.setFont(new Font("Times New Roman",Font.PLAIN,25));
		String code="";
		for(int i=0;i<4;i++){
			String rand=String.valueOf(r.nextInt(10));
			code+=code;
			g.setColor(new Color(20+r.nextInt(110),20+r.nextInt(110),20+r.nextInt(110)));
			g.drawString(rand, 13*i+6, 35);
			
		}

	//���ͼ��
	
		ImageIO.write(imgbuf, "JPEG", out);
		out.close();	
		
	}





	private Color getRandColor(int i, int j) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}
	
	private Color getRandcolor(int fc, int bc) {
		// TODO Auto-generated method stub
		Random random=new 	Random();
		if(fc>255)
			fc=255;
		if(fc<0)
			fc=0;
		if(bc>255)
			bc=255;
		if(bc<0)
			bc=0;
		int r=fc+random.nextInt(bc - fc);
		int g=fc+random.nextInt(bc - fc);
		int b=fc+random.nextInt(bc - fc);
		
		return new Color(r,g,b);
	}


}

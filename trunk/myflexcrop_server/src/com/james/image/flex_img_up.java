package com.james.image;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class flex_img_up
 */
public class flex_img_up extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	private int len=0;//处理流
	private int mm=0;//重命名
	private String fileName="";//文件原名
	private String extName="";//文件扩展名
	private String tempFileName="";//文件名加扩展名
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public flex_img_up() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);   
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);   
	}

	
	public void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
	{
		request.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		String photoPathOnServer=request.getParameter("photoPathOnServer");
		if(photoPathOnServer==null)photoPathOnServer="/upload/";
		//System.out.println("FMSFS-->realPath:"+realPath);
		response.setContentType("application/octet-stream");
		InputStream is = request.getInputStream();
		
		try {
				int size = 0;
				byte[] tmp = new byte[100000];
				String realBaseDir = getServletContext().getRealPath(photoPathOnServer);
				File baseFile = new File(realBaseDir);
				if (!baseFile.exists()) {
					baseFile.mkdirs();
				}
				SimpleDateFormat fileFormatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
				String fileUrl=photoPathOnServer+fileFormatter.format(new Date())+".jpg";//request.getContextPath()
				String photoPath=getServletContext().getRealPath(fileUrl);
				File f = new File(photoPath);
				DataOutputStream dos = new DataOutputStream(new FileOutputStream(f));
				while ((len = is.read(tmp)) != -1){
					dos.write(tmp, 0, len);
					size += len;
				}
				dos.flush();
				dos.close();
				out.println(fileUrl);
		} catch (IOException e) {
			out.println("err");
			e.printStackTrace();
		}
		out.close();
  }
 
 
}

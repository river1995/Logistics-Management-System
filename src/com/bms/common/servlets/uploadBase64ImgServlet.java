package com.bms.common.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bms.test.Base64Test;
import com.bms.utils.common.StringUtil;

/**
 * Servlet implementation class uploadBase64ImgServlet
 */
@WebServlet("/api/v1.0/upload_base64img")
public class uploadBase64ImgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Base64Test base64Test = new Base64Test();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public uploadBase64ImgServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idPhotoBase64 = request.getParameter("photo");
		String photoUrl = idPhotoBase64.split("base64,")[1];
		base64Test.GenerateImage(photoUrl);
		System.out.println(idPhotoBase64);
	}

}

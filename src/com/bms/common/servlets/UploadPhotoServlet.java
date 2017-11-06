package com.bms.common.servlets;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.bms.commom.domain.ApiResultEntity;
import com.bms.commom.domain.ResponseImageEntity;
import com.bms.utils.common.ConstantsUtil;
import com.bms.utils.json.ChangeFieldNameStandard;
import com.bms.utils.json.IgnoreNullProprety;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * Servlet implementation class UploadImageServlet
 */
@WebServlet("/app1/v1.0/upload_photo")
public class UploadPhotoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String uploadPath = ConstantsUtil.imagePath; // 上传文件的目录
	private String tempPath = ConstantsUtil.imagePath + "/tmp"; // 临时文件目录
	File tempPathFile;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadPhotoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		ApiResultEntity<List<String>> apiResultEntity = new ApiResultEntity<List<String>>();
		ResponseImageEntity imageEntity = new ResponseImageEntity();
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		List<String> fileNameList = null;
		try {
			// Create a factory for disk-based file items
			DiskFileItemFactory factory = new DiskFileItemFactory();

			// Set factory constraints
			factory.setSizeThreshold(4096000); // 设置缓冲区大小，这里是400kb
			factory.setRepository(tempPathFile);// 设置缓冲区目录

			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);

			// Set overall request size constraint
			upload.setSizeMax(400194304); // 设置最大文件尺寸，这里是400MB

			fileNameList = new ArrayList<String>();
			List<FileItem> items = upload.parseRequest(request);// 得到所有的文件
			Iterator<FileItem> i = items.iterator();
			while (i.hasNext()) {
				FileItem fi = (FileItem) i.next();
				String fileName = fi.getName();
				if (fileName != null) {
					String newFileName = fi.getName();
					File fullFile = new File(newFileName);
					File savedFile = new File(uploadPath, fullFile.getName());
					fi.write(savedFile);
					fileNameList.add(newFileName);
				}
			}
			System.out.println("upload succeed");
		} catch (Exception e) {
			// 可以跳转出错页面
			e.printStackTrace();
		}
		
		
		
		if (fileNameList.size() > 0) {
			apiResultEntity.setCode(0);
			apiResultEntity.setMessage("Success");
			apiResultEntity.setData(fileNameList);
		}else {
			apiResultEntity.setCode(40021);
			apiResultEntity.setMessage("上传图片失败");
		}
		
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setJsonPropertyFilter(new IgnoreNullProprety());
		//jsonConfig.registerJsonPropertyNameProcessor(ResponseImageEntity.class, new ChangeFieldNameStandard());
		//response.getWriter().print(JSONObject.fromObject(apiResultEntity ,jsonConfig));
		response.getWriter().print(JSONObject.fromObject(apiResultEntity ,jsonConfig));
	}

	public void init() throws ServletException {
		File uploadFile = new File(uploadPath);
		if (!uploadFile.exists()) {
			uploadFile.mkdirs();
		}
		File tempPathFile = new File(tempPath);
		if (!tempPathFile.exists()) {
			tempPathFile.mkdirs();
		}
	}

}

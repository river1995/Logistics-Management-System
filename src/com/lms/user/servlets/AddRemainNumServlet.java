package com.lms.user.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lms.commom.domain.ApiResultEntity;
import com.lms.user.services.impl.UserServiceImpl;
import com.lms.utils.common.StringUtil;
import com.lms.utils.json.IgnoreNullProprety;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * Servlet implementation class AddRemainNumServlet
 */
@WebServlet("/api/v1.0/add_remain_no")
public class AddRemainNumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StringUtil stringUtil = new StringUtil();
	private UserServiceImpl userService = new UserServiceImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddRemainNumServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ApiResultEntity<String> apiResultEntity = new ApiResultEntity<>();
		String userId = request.getParameter("user_id");
		String remainNum = request.getParameter("remain_num");
				
		if (!stringUtil.isNullString(remainNum) && !stringUtil.isNullString(userId)) {
			boolean flag = userService.addRemainNum(Integer.parseInt(userId), Integer.parseInt(remainNum));
			if (flag) {
				apiResultEntity.setCode(0);
				apiResultEntity.setMessage("success");
			}else{
				apiResultEntity.setCode(50000);
				apiResultEntity.setMessage("服务器错误");
			}
		}
		
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setJsonPropertyFilter(new IgnoreNullProprety());
		response.getWriter().print(JSONObject.fromObject(apiResultEntity ,jsonConfig));
	}

}

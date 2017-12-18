package com.lms.user.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lms.commom.domain.ApiResultEntity;
import com.lms.user.entities.UserEntity;
import com.lms.user.services.impl.UserServiceImpl;
import com.lms.utils.common.StringUtil;
import com.lms.utils.json.IgnoreNullProprety;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * Servlet implementation class AddUserServlet
 */
@WebServlet("/api/v1.0/admin_add_user")
public class AddUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StringUtil stringUtil = new StringUtil();
	private UserServiceImpl userService = new UserServiceImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int role = (int) request.getSession().getAttribute("type");
		int userId = (int) request.getSession().getAttribute("user_id");
		ApiResultEntity<String> apiResultEntity = new ApiResultEntity<>();
		String username = request.getParameter("username");		
		String password = request.getParameter("password");
		String type = request.getParameter("type");
		
		String phone = request.getParameter("phone");
		String remainNum = request.getParameter("remain_num");
		System.out.println("username:"+username+"~~~~~~~~"+"password:"+password+"~~~~~~~~"+"type:"+type+"~~~~~~~~"+"phone"+phone);
		if (!stringUtil.isNullString(username) && !stringUtil.isNullString(password) && !stringUtil.isNullString(type)
				&& !stringUtil.isNullString(phone) ) {
			if (role == 2 && Integer.parseInt(type) == 2 || role ==1) {
				apiResultEntity.setCode(40017);
				apiResultEntity.setMessage("没有权限");
				JsonConfig jsonConfig = new JsonConfig();
				jsonConfig.setJsonPropertyFilter(new IgnoreNullProprety());
				response.getWriter().print(JSONObject.fromObject(apiResultEntity ,jsonConfig));
				return;
			}
			if(userService.checkUserName(username)){
				String number = request.getParameter("number");
				UserEntity userEntity = new UserEntity();
				userEntity.setUsername(username);
				userEntity.setPassword(password);
				userEntity.setType(Integer.parseInt(type));
				if (Integer.parseInt(type) == 1) {
					userEntity.setRemainNum(Integer.parseInt(remainNum));	
					userEntity.setProxy(userId+"");
				}
				userEntity.setPhone(phone);
				if (!stringUtil.isNullString(number)) {
					userEntity.setNumber(Integer.parseInt(number));
				}
				boolean flag = userService.addUser(userEntity);
				if (flag) {
					apiResultEntity.setCode(0);
					apiResultEntity.setMessage("success");
				}else{
					apiResultEntity.setCode(50000);
					apiResultEntity.setMessage("服务器错误");
				}
			}else{
				apiResultEntity.setCode(40007);
				apiResultEntity.setMessage("用户名已经被使用");
			}
						
		}else{
			apiResultEntity.setCode(40000);
			apiResultEntity.setMessage("参数不齐全");
		}
		
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setJsonPropertyFilter(new IgnoreNullProprety());
		response.getWriter().print(JSONObject.fromObject(apiResultEntity ,jsonConfig));
	}

}

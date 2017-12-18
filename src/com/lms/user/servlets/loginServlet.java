package com.lms.user.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lms.commom.domain.ApiResultEntity;
import com.lms.user.entities.UserEntity;
import com.lms.user.services.impl.UserServiceImpl;
import com.lms.utils.common.StringUtil;
import com.lms.utils.json.ChangeFieldNameStandard;
import com.lms.utils.json.IgnoreNullProprety;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * Servlet implementation class LoginBackendServlet
 */
@WebServlet("/lms/v1.0/login")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StringUtil stringUtil = new StringUtil();
	private UserServiceImpl userService = new UserServiceImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ApiResultEntity<UserEntity> resultEntity = new ApiResultEntity<UserEntity>();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if (!stringUtil.isNullString(username) && !stringUtil.isNullString(password)) {
			UserEntity userEntity = userService.login(username, password);
			if (userEntity.getId() > 0) {
				HttpSession session = request.getSession();
				session.setAttribute("pod-token", username + session.getId());
				System.out.println(session.getAttribute("pod-token"));
				session.setAttribute("user_id", userEntity.getId());
				session.setAttribute("current_user", userEntity);
				session.setAttribute("type", userEntity.getType());
				resultEntity.setCode(0);
				resultEntity.setMessage("success");
				resultEntity.setData(userEntity);
			}else{
				resultEntity.setCode(40001);
				resultEntity.setMessage("用户名或密码不正确");
			}
		}else{
			resultEntity.setCode(40000);
			resultEntity.setMessage("参数不齐全");
		}
		
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setJsonPropertyFilter(new IgnoreNullProprety());
		jsonConfig.registerJsonPropertyNameProcessor(UserEntity.class, new ChangeFieldNameStandard());
		response.getWriter().print(JSONObject.fromObject(resultEntity ,jsonConfig));
	}
	

}

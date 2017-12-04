package com.bms.user.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bms.commom.domain.ApiResultEntity;
import com.bms.user.entities.UserEntity;
import com.bms.user.services.impl.UserServiceImpl;
import com.bms.utils.common.StringUtil;
import com.bms.utils.json.IgnoreNullProprety;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * Servlet implementation class DisabeUserServlet
 */
@WebServlet("/api/v1.0/disable_user")
public class DisabeUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StringUtil stringUtil = new StringUtil();
	private UserServiceImpl userService = new UserServiceImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisabeUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ApiResultEntity<String> apiResultEntity = new ApiResultEntity<>();
		String userId = request.getParameter("user_id");
		if (!stringUtil.isNullString("user_id")) {
			boolean flag = userService.disableUser(Integer.parseInt(userId));
			if (flag) {
				apiResultEntity.setCode(0);
				apiResultEntity.setMessage("success");
			}else{
				apiResultEntity.setCode(50000);
				apiResultEntity.setMessage("服务器错误");
			}
		}else {
			apiResultEntity.setCode(40000);
			apiResultEntity.setMessage("参数不齐全");
		}
		
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setJsonPropertyFilter(new IgnoreNullProprety());
		response.getWriter().print(JSONObject.fromObject(apiResultEntity ,jsonConfig));
	}

}

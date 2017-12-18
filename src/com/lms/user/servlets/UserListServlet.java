package com.lms.user.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lms.commom.domain.ApiResultEntity;
import com.lms.commom.domain.PageEntity;
import com.lms.commom.domain.QueryEntity;
import com.lms.user.entities.UserEntity;
import com.lms.user.services.impl.UserServiceImpl;
import com.lms.utils.common.StringUtil;
import com.lms.utils.json.ChangeFieldNameStandard;
import com.lms.utils.json.IgnoreFieldProcessorImpl;
import com.lms.utils.json.IgnoreNullProprety;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * Servlet implementation class UserListServlet
 */
@WebServlet("/api/v1.0/user_list")
public class UserListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserServiceImpl userService = new UserServiceImpl();
	private StringUtil stringUtil = new StringUtil();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int role = (int) request.getSession().getAttribute("type");
		int userId = (int) request.getSession().getAttribute("user_id");
		ApiResultEntity<PageEntity<UserEntity>> apiResultEntity = new ApiResultEntity<>();
		PageEntity<UserEntity> pageEntity = new PageEntity<>();
		QueryEntity queryEntity = new QueryEntity();
		String offset = request.getParameter("offset");
		String limit = request.getParameter("limit");
		String typeStr = request.getParameter("type");
		int type = 0;
		System.out.println("offset:"+offset+"\n"+"limit:"+limit+"\n"+"type:"+typeStr);
		if (!stringUtil.isNullString(offset) && !stringUtil.isNullString(limit))  {
			queryEntity.setOffset(Integer.parseInt(offset));
			queryEntity.setLimit(Integer.parseInt(limit));
		}
		if (!stringUtil.isNullString(typeStr)) {
			type = Integer.parseInt(typeStr);
			if (role == 2 && type == 2 || role == 1) {
				apiResultEntity.setCode(40017);
				apiResultEntity.setMessage("没有权限");
				JsonConfig jsonConfig = new JsonConfig();
				jsonConfig.setJsonPropertyFilter(new IgnoreNullProprety());
				response.getWriter().print(JSONObject.fromObject(apiResultEntity ,jsonConfig));
				return;
			}
			if (role == 2 && type == 1) {
				int count  = userService.proxyCountUser(type);
				pageEntity.setTotal(count);
				System.out.println("type:"+type+"queryEntity:"+JSONObject.fromObject(queryEntity));
				List<UserEntity> list = userService.proxyUserList(queryEntity ,userId);
				if (list != null && list.size() > 0) {
					pageEntity.setRows(list);
					apiResultEntity.setCode(0);
					apiResultEntity.setMessage("success");
					apiResultEntity.setData(pageEntity);
				}else {
					apiResultEntity.setCode(40016);
					apiResultEntity.setMessage("数据未找到");
				}	
			}else{
				int count  = userService.countUser(type);
				pageEntity.setTotal(count);
				System.out.println("type:"+type+"queryEntity:"+JSONObject.fromObject(queryEntity));
				List<UserEntity> list = userService.userList(type ,queryEntity);
				if (list != null && list.size() > 0) {
					pageEntity.setRows(list);
					apiResultEntity.setCode(0);
					apiResultEntity.setMessage("success");
					apiResultEntity.setData(pageEntity);
				}else {
					apiResultEntity.setCode(40016);
					apiResultEntity.setMessage("数据未找到");
				}	
			}
					
		}else{
			apiResultEntity.setCode(40000);
			apiResultEntity.setMessage("参数不齐全");
		}
		
		
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonPropertyNameProcessor(UserEntity.class, new ChangeFieldNameStandard());
		if (type == 2) {
			jsonConfig.setJsonPropertyFilter(new IgnoreFieldProcessorImpl(true ,new String[]{"total","remainNum"}));
		}		
		response.getWriter().print(JSONObject.fromObject(apiResultEntity ,jsonConfig));
		
		
	}

}

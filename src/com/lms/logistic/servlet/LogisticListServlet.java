package com.lms.logistic.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bms.commom.domain.ApiResultEntity;
import com.bms.commom.domain.PageEntity;
import com.bms.commom.domain.QueryEntity;
import com.bms.utils.common.StringUtil;
import com.bms.utils.json.ChangeFieldNameStandard;
import com.bms.utils.json.IgnoreNullProprety;
import com.lms.logistic.entities.LogisticEntity;
import com.lms.logistic.services.dao.impl.LogisticServicesDaoImpl;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * Servlet implementation class LogisticListServlet
 */
@WebServlet("/api/v1.0/logistic_list")
public class LogisticListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StringUtil stringUtil = new StringUtil();
	private LogisticServicesDaoImpl logisticService = new LogisticServicesDaoImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogisticListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("run test");
		int userId = (int) request.getSession().getAttribute("user_id");
		ApiResultEntity<PageEntity<LogisticEntity>> apiResultEntity = new ApiResultEntity<>();
		PageEntity<LogisticEntity> pageEntity = new PageEntity<LogisticEntity>();
		QueryEntity queryEntity = new QueryEntity();
		int limit = 0;
		int offset = 0;
		
		if (!stringUtil.isNullString(request.getParameter("limit"))) {
			limit = Integer.parseInt(request.getParameter("limit"));
		}
		if (!stringUtil.isNullString(request.getParameter("offset"))) {
			offset = Integer.parseInt(request.getParameter("offset"));
		}
		System.out.println("limit:"+limit+"\n"+"offset:"+offset);
		queryEntity.setLimit(limit);
		queryEntity.setOffset(offset);
		
		List<LogisticEntity> list = logisticService.logisticList(userId ,queryEntity);
		pageEntity.setTotal(logisticService.getOrderCounts(userId));
		if (list != null && list.size() > 0) {
			pageEntity.setRows(list);
			apiResultEntity.setCode(0);
			apiResultEntity.setMessage("success");
			apiResultEntity.setData(pageEntity);
		}else{
			apiResultEntity.setCode(40016);
			apiResultEntity.setMessage("数据未找到");
		}
		JsonConfig jsonConfig = new JsonConfig();
		//jsonConfig.setJsonPropertyFilter(new IgnoreNullProprety());
		jsonConfig.registerJsonPropertyNameProcessor(LogisticEntity.class, new ChangeFieldNameStandard());
		response.getWriter().print(JSONObject.fromObject(apiResultEntity ,jsonConfig));
	}

}

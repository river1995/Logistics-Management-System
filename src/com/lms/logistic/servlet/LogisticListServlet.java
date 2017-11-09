package com.lms.logistic.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bms.commom.domain.ApiResultEntity;
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
		ApiResultEntity<List<LogisticEntity>> apiResultEntity = new ApiResultEntity<>();
		List<LogisticEntity> list = logisticService.logisticList();
		if (list != null && list.size() > 0) {
			apiResultEntity.setCode(0);
			apiResultEntity.setMessage("success");
			apiResultEntity.setData(list);
		}else{
			apiResultEntity.setCode(40016);
			apiResultEntity.setMessage("数据未找到");
		}
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setJsonPropertyFilter(new IgnoreNullProprety());
		jsonConfig.registerJsonPropertyNameProcessor(LogisticEntity.class, new ChangeFieldNameStandard());
		response.getWriter().print(JSONObject.fromObject(apiResultEntity ,jsonConfig));
	}

}

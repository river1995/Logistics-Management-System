package com.lms.logistic.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bms.commom.domain.ApiResultEntity;
import com.bms.utils.common.StringUtil;
import com.bms.utils.json.ChangeFieldNameStandard;
import com.bms.utils.json.IgnoreNullProprety;
import com.lms.logistic.entities.LogisticEntity;
import com.lms.logistic.services.dao.impl.LogisticServicesDaoImpl;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * Servlet implementation class LogisticInfoServlet
 */
@WebServlet("/api/v1.0/logistic_info")
public class LogisticInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StringUtil stringUtil = new StringUtil();
	private LogisticServicesDaoImpl logisticService = new LogisticServicesDaoImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogisticInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ApiResultEntity<LogisticEntity> apiResultEntity = new ApiResultEntity<>();
		String orderIdStr = request.getParameter("order_id");
		if (!stringUtil.isNullString(orderIdStr)) {
			int orderId = Integer.parseInt(orderIdStr);
			LogisticEntity logisticEntity = logisticService.logisticInfo(orderId);
			if (logisticEntity != null && logisticEntity.getId() > 0) {
				apiResultEntity.setCode(0);
				apiResultEntity.setMessage("success");
				apiResultEntity.setData(logisticEntity);
			}else{
				apiResultEntity.setCode(40016);
				apiResultEntity.setMessage("数据未找到");
			}
		}else{
			apiResultEntity.setCode(40000);
			apiResultEntity.setMessage("参数不齐全");
		}
		
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setJsonPropertyFilter(new IgnoreNullProprety());
		jsonConfig.registerJsonPropertyNameProcessor(LogisticEntity.class, new ChangeFieldNameStandard());
		response.getWriter().print(JSONObject.fromObject(apiResultEntity ,jsonConfig));
	}

}

package com.lms.logistic.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lms.commom.domain.ApiResultEntity;
import com.lms.logistic.entities.LogisticStatusEntity;
import com.lms.logistic.services.dao.impl.LogisticServicesDaoImpl;
import com.lms.utils.common.StringUtil;
import com.lms.utils.json.ChangeFieldNameStandard;
import com.lms.utils.json.IgnoreNullProprety;
import com.sun.org.apache.bcel.internal.generic.LSTORE;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * Servlet implementation class StatusListServlet
 */
@WebServlet("/api/v1.0/logistic_status_list")
public class StatusListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StringUtil stringUtil = new StringUtil();
	private LogisticServicesDaoImpl logisticService = new LogisticServicesDaoImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StatusListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ApiResultEntity<List<LogisticStatusEntity>> apiResultEntity = new ApiResultEntity<>();
		List<LogisticStatusEntity> list = new ArrayList<>();
		String orderIdStr = request.getParameter("order_id");
		if (!stringUtil.isNullString(orderIdStr)) {
			int orderId = Integer.parseInt(orderIdStr);
			list = logisticService.statusList(orderId);
			if (list.size() > 0 && list != null) {
				apiResultEntity.setCode(0);
				apiResultEntity.setMessage("success");
				apiResultEntity.setData(list);
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
		jsonConfig.registerJsonPropertyNameProcessor(LogisticStatusEntity.class, new ChangeFieldNameStandard());
		response.getWriter().print(JSONObject.fromObject(apiResultEntity ,jsonConfig));
	}

}

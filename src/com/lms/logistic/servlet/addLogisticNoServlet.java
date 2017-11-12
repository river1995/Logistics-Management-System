package com.lms.logistic.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.directwebremoting.impl.StartupUtil;

import com.bms.commom.domain.ApiResultEntity;
import com.bms.utils.common.StringUtil;
import com.bms.utils.json.IgnoreNullProprety;
import com.lms.logistic.services.dao.impl.LogisticServicesDaoImpl;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * Servlet implementation class addLogisticNoServlet
 */
@WebServlet("/api/v1.0/add_logistic_no")
public class addLogisticNoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StringUtil stringUtil = new StringUtil();
	private LogisticServicesDaoImpl logisticService = new LogisticServicesDaoImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addLogisticNoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ApiResultEntity<String> apiResultEntity = new ApiResultEntity<>();
		String orderIdStr = request.getParameter("order_id");
		String logisticCompany = request.getParameter("logistic_company");
		String logisticNo = request.getParameter("logistic_no");
		if (!stringUtil.isNullString(orderIdStr) && !stringUtil.isNullString(logisticCompany) && !stringUtil.isNullString(logisticNo)) {
			int orderId = Integer.parseInt(orderIdStr);
			boolean flag = logisticService.addLogisticNo(logisticNo, logisticCompany, orderId);
			if (flag) {
				apiResultEntity.setCode(0);
				apiResultEntity.setMessage("success");
			}else{
				apiResultEntity.setCode(50000);
				apiResultEntity.setMessage("服务器错误");
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

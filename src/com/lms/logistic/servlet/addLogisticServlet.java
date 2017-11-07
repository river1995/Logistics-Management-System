package com.lms.logistic.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bms.commom.domain.ApiResultEntity;
import com.bms.utils.common.StringUtil;
import com.bms.utils.json.IgnoreNullProprety;
import com.lms.logistic.entities.LogisticDetailEntity;
import com.lms.logistic.entities.LogisticEntity;
import com.lms.logistic.services.dao.impl.LogisticServicesDaoImpl;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * Servlet implementation class addLogisticServlet
 */
@WebServlet("/addLogisticServlet")
public class addLogisticServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StringUtil stringUtil = new StringUtil();
	private LogisticServicesDaoImpl logisticService = new LogisticServicesDaoImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addLogisticServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userId = (int) request.getSession().getAttribute("user_id");
		ApiResultEntity<String> apiResultEntity = new ApiResultEntity<>();
		String fromCity = request.getParameter("from_city");
		String gatewayCity = request.getParameter("gateway_city");
		String lastTime = request.getParameter("last_time");
		String logisticCompany = request.getParameter("logistic_company");
		if (!stringUtil.isNullString(fromCity) && !stringUtil.isNullString(gatewayCity) && !stringUtil.isNullString(lastTime) && !stringUtil.isNullString(logisticCompany)) {
			LogisticEntity logisticEntity = new LogisticEntity();
			logisticEntity.setExpireTime(lastTime);
			logisticEntity.setFromCity(fromCity);
			logisticEntity.setGatewayCity(gatewayCity);
			logisticEntity.setLogisticCompany(logisticCompany);
			logisticEntity.setOrderSeq(System.currentTimeMillis()+"");
			logisticEntity.setUserId(userId);
		}else{
			apiResultEntity.setCode(40000);
			apiResultEntity.setMessage("参数不齐全");
		}
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setJsonPropertyFilter(new IgnoreNullProprety());
		response.getWriter().print(JSONObject.fromObject(apiResultEntity ,jsonConfig));
	}

}

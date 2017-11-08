package com.lms.logistic.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bms.commom.domain.ApiResultEntity;
import com.bms.utils.common.StringUtil;
import com.bms.utils.json.IgnoreNullProprety;
import com.lms.logistic.entities.LogisticEntity;
import com.lms.logistic.entities.LogisticStatusEntity;
import com.lms.logistic.services.dao.impl.LogisticServicesDaoImpl;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * Servlet implementation class generateLogisticInfoServlet
 */
@WebServlet("/api/v1.0/generate_logistic_info")
public class generateLogisticInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StringUtil stringUtil = new StringUtil();
	private LogisticServicesDaoImpl logisticService = new LogisticServicesDaoImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public generateLogisticInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ApiResultEntity<List<LogisticStatusEntity>> apiResultEntity = new ApiResultEntity<>();
		String fromCountry = request.getParameter("from_country");
		String fromCity = request.getParameter("from_city");
		String gatewayCity = request.getParameter("gateway_city");
		String lastTime = request.getParameter("last_time");
		String logisticCompany = request.getParameter("logistic_company");
		if (!stringUtil.isNullString(fromCity) && !stringUtil.isNullString(gatewayCity) && !stringUtil.isNullString(lastTime)
				&& !stringUtil.isNullString(logisticCompany) && !stringUtil.isNullString(fromCountry)) {
			LogisticEntity logisticEntity = new LogisticEntity();
			logisticEntity.setExpireTime(lastTime);
			logisticEntity.setFromCountry(fromCountry);
			logisticEntity.setFromCity(fromCity);
			logisticEntity.setGatewayCity(gatewayCity);
			logisticEntity.setLogisticCompany(logisticCompany);
			logisticEntity.setOrderSeq(System.currentTimeMillis()+"");
			List<LogisticStatusEntity> list = logisticService.generateLogsiticInfo(logisticEntity);
			if (list != null && list.size() > 0) {
				apiResultEntity.setCode(0);
				apiResultEntity.setMessage("success");
				apiResultEntity.setData(list);
			}else{
				apiResultEntity.setCode(50000);
				apiResultEntity.setMessage("生成物流信息错误");
				
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

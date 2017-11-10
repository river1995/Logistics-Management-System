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
import com.bms.utils.json.ChangeFieldNameCamel;
import com.bms.utils.json.IgnoreNullProprety;
import com.lms.logistic.entities.LogisticDetailEntity;
import com.lms.logistic.entities.LogisticEntity;
import com.lms.logistic.entities.LogisticStatusEntity;
import com.lms.logistic.services.dao.impl.LogisticServicesDaoImpl;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonBeanProcessor;

/**
 * Servlet implementation class addLogisticServlet
 */
@WebServlet("/api/v1.0/add_logstic")
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
		String gatewayTime = request.getParameter("gateway_time");
		String contact = request.getParameter("contact");
		String contactAddress = request.getParameter("contact_address");
		String phone = request.getParameter("phone");
		String sender = request.getParameter("sender");
		String senderAddress = request.getParameter("sender_address");
		String fromCountry = request.getParameter("from_country");
		String fromCity = request.getParameter("from_city");
		String gatewayCity = request.getParameter("gateway_city");
		//String expireTime = request.getParameter("expire_time");
		String logisticJSON = request.getParameter("logistic_json");
		System.out.println(contactAddress);
		//System.out.println("fromCountry:"+fromCountry+"\n"+"fromCity:"+fromCity+"\n"+"gatewayCity:"+gatewayCity+"\n"+"expireTime:"+expireTime+"\n"+"logisticCompany:"+logisticCompany+"\n"+"logisticJSON:"+logisticJSON);
		
		if (!stringUtil.isNullString(fromCity) && !stringUtil.isNullString(gatewayCity)&& !stringUtil.isNullString(gatewayTime)
				&& !stringUtil.isNullString(fromCountry) && !stringUtil.isNullString(logisticJSON) && !stringUtil.isNullString(contactAddress)
				&& !stringUtil.isNullString(contact) && !stringUtil.isNullString(phone) && !stringUtil.isNullString(sender) && !stringUtil.isNullString(senderAddress)) {
			LogisticDetailEntity detailEntity = new LogisticDetailEntity();
			LogisticEntity logisticEntity = new LogisticEntity();
			logisticEntity.setFinishTime(gatewayTime);
			logisticEntity.setContact(contact);
			logisticEntity.setContactAddress(contactAddress);
			logisticEntity.setPhone(phone);
			logisticEntity.setSender(sender);
			logisticEntity.setSenderAddress(senderAddress);
			//logisticEntity.setExpireTime(expireTime);
			logisticEntity.setFromCountry(fromCountry);
			logisticEntity.setFromCity(fromCity);
			logisticEntity.setGatewayCity(gatewayCity);
			logisticEntity.setOrderSeq(StringUtil.generateLogisticNo());
			logisticEntity.setUserId(userId);
			
			List<LogisticStatusEntity> list = JSONArray.toList(JSONArray.fromObject(logisticJSON), new LogisticStatusEntity(), new JsonConfig());//参数1为要转换的JSONArray数据，参数2为要转换的目标数据，即List盛装的数据
			detailEntity.setLogisticEntity(logisticEntity);
			detailEntity.setStatusEntity(list);
	        boolean addRs = logisticService.addLogistic(detailEntity);
	        if (addRs) {
				apiResultEntity.setCode(0);
				apiResultEntity.setMessage("success");				
			}else{
				apiResultEntity.setCode(50000);
				apiResultEntity.setMessage("添加失败");
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

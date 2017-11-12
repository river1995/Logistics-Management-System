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
 * Servlet implementation class getLogisticInfoById
 */
@WebServlet("/api/v1.0/logistic_info_orderseq")
public class getLogisticInfoById extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StringUtil stringUtil = new StringUtil();
	private LogisticServicesDaoImpl logisticService = new LogisticServicesDaoImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getLogisticInfoById() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ApiResultEntity<LogisticEntity> apiResultEntity = new ApiResultEntity<>();
		String orderSeq = request.getParameter("order_seq");
		if (!stringUtil.isNullString(orderSeq)) {
			LogisticEntity logisticEntity = logisticService.logisticInfoById(orderSeq);
			if (logisticEntity != null) {
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
		//jsonConfig.setJsonPropertyFilter(new IgnoreNullProprety());
		jsonConfig.registerJsonPropertyNameProcessor(LogisticEntity.class, new ChangeFieldNameStandard());
		response.getWriter().print(JSONObject.fromObject(apiResultEntity ,jsonConfig));
	}

}

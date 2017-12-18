package com.lms.logistic.servlet;

import java.io.IOException;
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

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * Servlet implementation class CustomerLogisticInfoServlet
 */
@WebServlet("/api/ex/v1.0/logistic_info")
public class CustomerLogisticInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StringUtil stringUtil = new StringUtil();
    private LogisticServicesDaoImpl logisticService = new LogisticServicesDaoImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerLogisticInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("run test");
		ApiResultEntity<List<LogisticStatusEntity>> apiResultEntity = new ApiResultEntity<>();
		String orderSeq = request.getParameter("order_seq");
		if (!stringUtil.isNullString(orderSeq)) {
			List<LogisticStatusEntity> list = logisticService.customerLogisticList(orderSeq);
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

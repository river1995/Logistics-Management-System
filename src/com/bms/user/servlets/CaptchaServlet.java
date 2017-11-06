package com.bms.user.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bms.commom.domain.ApiResultEntity;
import com.bms.utils.common.EmailUtil;
import com.bms.utils.common.StringUtil;
import com.bms.utils.common.ValidatorUtil;
import com.bms.utils.json.IgnoreNullProprety;
import com.bms.utils.redis.RedisUtil;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * Servlet implementation class CaptchaServlet
 */
@WebServlet("/app/v1.0/captcha")
public class CaptchaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private StringUtil stringUtil = new StringUtil();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CaptchaServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ApiResultEntity<String> result = new ApiResultEntity<String>();
		String email = request.getParameter("email");
		String type = request.getParameter("type");
		if (!stringUtil.isNullString(email) && ValidatorUtil.isEmail(email) && !stringUtil.isNullString(type)
				&& (type.equalsIgnoreCase("signup") || type.equalsIgnoreCase("resetpassword"))) {
			if (RedisUtil.sendCaptchaAgain(email, type)) {
				try {
					EmailUtil sender = new EmailUtil();
					String captcha = stringUtil.RandomString(6);
					sender.sendAuthCode(captcha, email, type);
					RedisUtil.insertEmailCaptcha(email, captcha, type);
					result.setCode(0);
					result.setMessage("Send Captcha Success");
				} catch (Exception e) {
					e.printStackTrace();
					result.setCode(50000);
					result.setMessage("Backend Error");
				}
			} else {
				result.setCode(40029);
				result.setMessage("Too Many Requests");
			}

		} else {
			result.setCode(40000);
			result.setMessage("参数不齐全");
		}

		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setJsonPropertyFilter(new IgnoreNullProprety());
		response.getWriter().println(JSONObject.fromObject(result ,jsonConfig));
	}

}

package com.bms.utils.common;


public class ConstantsUtil {

	public static String dbdriver;
	public static String dburl;
	public static String dbusername;
	public static String dbpassword;
	public static String basePath;
	public static String imagePath;
	public static String companyImgPath;
	public static String exportPath;

	public static String redisUrl;
	public static String redisPassword;
	public static int redisPort;
	
	public static String accessIp;

	public final static String aeskey = "darkwood_secrect";
	public final static String aesiv = "secrect_darkwood";
	public final static String encrypt = "yes";
	public final static String mode = "dev";
	public final static String server = "outside";
	public final static String dateFormat = "yyyy-MM-dd HH:mm:ss";
	public final static int dbSharping = 10;
	public final static int tableSharping = 5;
	public final static String md5Salt = "NUFmosKT";
	public final static String tokenMd5Salt = "DHYsgOSnD";
	public final static long accessTokenTime = 15 * 24 * 60 * 60 * 1000; // token失效时间：单位ms
	public final static int emailCaptchaTime = 30 * 60 * 1000; // 邮箱验证码失效时间：单位ms
	public final static int sendCaptchaAgainTime = 60 * 1000; // 允许再次发送验证码的时间:单位ms
	

	// 邮箱配置
	//	public final static String smtpServer = "smtp.163.com";
	//	public final static String emailUser = "gecko_browser@163.com";
	//	public final static String emailPassword = "hellogecko123";
	 public final static String smtpServer = "smtp.gmail.com";
	 public final static String emailUser = "riverjiang1995@gmail.com";
	 public final static String emailPassword = "jy3561854";

	// 请求返回码
	public final static int REQUEST_SUCCESS = 200; // 请求成功
	public final static int BAD_REQUEST = 400; // 参数不齐全
	public final static int INVALID_REQUEST = 401; // 参数数据不正确
	public final static int FORBIDDEN = 403; // 禁止使用，数据已使用
	public final static int NOT_FOUND = 404; // 数据未找到
	public final static int SERVER_ERROR = 500; // 服务器出现错误
	public final static int TokenTimeout = 419; // 令牌过期

	// 默认数量
	public final static int DEFAULT_BRAND_COUNT = 1;
	public final static int DEFAULT_SOURCE_DOMAIN_COUNT = 1;
	public final static int DEFAULT_TARGET_DOMAIN_COUNT = 20;

	public final static int WORKSTATUS_FREE = 0;
	public final static int WORKSTATUS_BUSY = 1;
	public final static int WORKSTATUS_OFFLINE = 2;

	public final static int INCIDENT_STATUS_SUBMITTED = 1;
	public final static int INCIDENT_STATUS_ASSIGNED = 2;
	public final static int INCIDENT_STATUS_ACCEPTED = 3;
	public final static int INCIDENT_STATUS_ARRIVED = 4;
	public final static int INCIDENT_STATUS_FINISHED = 5;
	public final static int INCIDENT_STATUS_CANCELED = 6;

	public final static int USER_TYPE_CUSTOMER = 1;
	public final static int USER_TYPE_SECURITY = 2;
	public final static int USER_TYPE_ADMIN = 3;

	public final static int ACCOUNT_STATUS_OPEN = 0;
	public final static int ACCOUNT_STATUS_APPROVED = 1;
	public final static int ACCOUNT_STATUS_REJECTED = 2;
	
	public final static String ROLE_SUPER_ADMIN = "super_admin";
	public final static String ROLE_COMPANY_ADMIN = "company_admin";
	public final static String ROLE_INVOICE_ADMIN = "invoice_admin";
	
	

	
	

	static {

		dbdriver = "com.mysql.jdbc.Driver";
		if (mode.equals("dev")) {
			accessIp = "192.168.2.175";
			dburl = "jdbc:mysql://localhost:3306/logistics_center?characterEncoding=UTF-8";
			dbusername = "root";
			dbpassword = "1234";
			// redis数据库配置
			redisUrl = "127.0.0.1";
			redisPort = 6379;
			redisPassword = "root";

			basePath = "C:\\photo";
			// basePath =
			// "C:\\software\\ApacheTomcat\\apache-tomcat-8.5.15-windows-x64\\apache-tomcat-8.5.15\\wtpwebapps\\bms-service-center";
			// basePath =
			// "/Users/andy/Documents/apache-tomcat-8.0.44/wtpwebapps/dw-brower-backend";
			companyImgPath = basePath + "/company_img/";
			imagePath = basePath + "/img/upload/";
			exportPath = basePath + "/export-file/";
		} else if (mode.equals("test")) {
			accessIp = "45.79.74.195";
			dburl = "jdbc:mysql://localhost:3306/logistics_center?characterEncoding=UTF-8";
			dbusername = "root";
			dbpassword = "f2jsEQ!u)";
			// redis数据库配置
			redisUrl = "127.0.0.1";
			redisPort = 6379;
			redisPassword = "jgb.dpIp4";

			// basePath = "/usr/local/tomcat/webapps";
			// if (server.equals("inside")) {
			// exportPath = basePath + "/ROOT/export-file/";
			// imagePath = basePath + "/ROOT/img/upload/";
			// } else {
			// exportPath = basePath + "/bms-center/export-file/";
			// imagePath = basePath + "/bms-center/img/upload/";
			// }

			basePath = "/usr/local/tomcat/photo";
			imagePath = basePath + "/img/upload/";
			exportPath = basePath + "/export-file/";
		} else {
			dburl = "jdbc:mysql://localhost:3306/bms_center";
			dbusername = "root";
			dbpassword = "@Dcba654123";
			// redis数据库配置
			redisUrl = "127.0.0.1";
			redisPort = 6379;
			redisPassword = "root";

			basePath = "C:/apache-tomcat-8.0.30/webapps";
			if (server.equals("inside")) {
				exportPath = basePath + "/ROOT/export-file/";
				imagePath = basePath + "/ROOT/img/upload/";
			} else {
				exportPath = basePath + "/dw-sports-news/export-file/";
				imagePath = basePath + "/dw-sports-news/img/upload/";
			}
		}
	}
}

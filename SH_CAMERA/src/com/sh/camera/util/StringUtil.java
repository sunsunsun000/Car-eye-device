/*  car eye 车辆管理平台 
 * 企业网站:www.shenghong-technology.com
 * 车眼管理平台   www.car-eye.cn
 * 车眼开源网址:https://github.com/Car-eye-admin
 * Copyright
 */


package com.sh.camera.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串处理工具类
 * 
 * @author Administrator
 * 
 */
public class StringUtil {
	static public boolean startCheck(String reg, String string) {
		boolean tem = false;

		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(string);
		tem = matcher.matches();
		return tem;
	}
	
	/**
	 * 反序
	 * */
	public static String getAntitone(String text){
		StringBuilder sb = new StringBuilder();
		for(int i = 0;i<text.length();i++){
			sb.append(String.valueOf(text.charAt(text.length()-1-i)));
		}
		return sb.toString();
	}
	/**四舍五入  double value)*/
	public static String getDecimal(double num) {
		String numStr ="";
		DecimalFormat df = new DecimalFormat("0.00");
		double d = 123.9078;
		String db = df.format(d);
		//则db=123.90;
		if (Double.isNaN(num)) {
			return "0.00";
		}
		BigDecimal bd = new BigDecimal(num);
		num = bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		numStr =  df.format(num) ;
		return numStr;
	}
	
	public static double div(double d1,
		    double d2,int len) {// 进行除法运算
		             BigDecimal b1 = new BigDecimal(d1);
		             BigDecimal b2 = new BigDecimal(d2);
		            return b1.divide(b2,len,BigDecimal.
		    ROUND_HALF_UP).doubleValue();
		         }
	/**
	 * 检验整数,适用于正整数、负整数、0，负整数不能以-0开头, 正整数不能以0开头
	 * 
	 * */
	static public boolean checkNr(String nr) {
		String reg = "^(-?)[1-9]+\\d*|0";
		return startCheck(reg, nr);
	}
	 /**
     * 检查字符串是否为空
     * @param str 字符串
     * @return
     */
    public static boolean isEmpty(String str) {
        if (str == null) {
            return true;
        } else if (str.length() == 0) {
            return true;
        } else {
            return false;
        }
    }
	/**检测是否为空*/
	public static boolean isNull(String obj){
		if(obj == null || obj.equals("") || obj.equals("null") || obj.equals("NULL")){
			return true;
		}
		return false;
	}
	public static boolean isMobileNO(String mobiles) { 
		
//		目前移动、联通、电信三大运营商的手机号段如下： 2015-10-14
//		1、移动号段有134,135,136,137, 138,139,147,150,151, 152,157,158,159,178,182,183,184,187,188。
//		2、联通号段有130，131，132，155，156，185，186，145，176。
//		3、电信号段有133，153，177，180，181，189。 
		
		
        /* 
        移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188 
        联通：130、131、132、152、155、156、185、186 
        电信：133、153、180、189、（1349卫通） 
        总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9 
        */  
        String telRegex = "[1][34578]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。  
        if (StringUtil.isEmpty(mobiles)) return false;  
        else return mobiles.matches(telRegex);  
       } 
	/**
	 * 手机号码验证,11位，不知道详细的手机号码段，只是验证开头必须是1和位数
	 * */
	static public boolean checkCellPhone(String cellPhoneNr) {
		String reg = "^[1][\\d]{10}";
		return startCheck(reg, cellPhoneNr);
	}

	/**
	 * 检验空白符
	 * */
	static public boolean checkWhiteLine(String line) {
		String regex = "(\\s|\\t|\\r)+";

		return startCheck(regex, line);
	}

	/**
	 * 检查EMAIL地址 用户名和网站名称必须>=1位字符
	 * 地址结尾必须是以com|cn|com|cn|net|org|gov|gov.cn|edu|edu.cn结尾
	 * */
	static public boolean checkEmailWithSuffix(String email) {
		String regex = "\\w+\\@\\w+\\.(com|cn|com.cn|net|org|gov|gov.cn|edu|edu.cn)";

		return startCheck(regex, email);
	}

	/**
	 * 检查EMAIL地址 用户名和网站名称必须>=1位字符 地址结尾必须是2位以上，如：cn,test,com,info
	 * */
	static public boolean checkEmail(String email) {
		String regex = "\\w+\\@\\w+\\.\\w{2,}";

		return startCheck(regex, email);
	}

	/**
	 * 检查邮政编码(中国),6位，第一位必须是非0开头，其他5位数字为0-9
	 * */
	static public boolean checkPostcode(String postCode) {
		String regex = "^[1-9]\\d{5}";
		return startCheck(regex, postCode);
	}

	/**
	 * 检验用户名 取值范围为a-z,A-Z,0-9,"_"，不能以"_"结尾 用户名有最小长度和最大长度限制，比如用户名必须是4-20位
	 * */
	static public boolean checkUsername(String username, int min, int max) {
		String regex = "[\\w]{" + min + "," + max + "}(?<!_)";
		return startCheck(regex, username);
	}

	/**
	 * 检验用户名 取值范围为a-z,A-Z,0-9,"_"，不能以"_"结尾 有最小位数限制的用户名，比如：用户名最少为4位字符
	 * */
	static public boolean checkUsername(String username, int min) {
		// [\\w\u4e00-\u9fa5]{2,}?
		String regex = "[\\w]{" + min + ",}(?<!_)";

		return startCheck(regex, username);
	}

	/**
	 * 检验用户名 取值范围为a-z,A-Z,0-9,"_", 最少一位字符，最大字符位数无限制，不能以"_"结尾
	 * */
	static public boolean checkUsername(String username) {
		String regex = "[\\w]+(?<!_)";
		return startCheck(regex, username);
	}

	/**
	 * 检查字符串为中英文、大小写字母、数字、下划线等任意字符组合
	 * 
	 * @param str
	 * @param min
	 * @param max
	 * @return
	 */
	static public boolean checkStrLength(String str, int min, int max) {
		String regex = "[\\w\u4e00-\u9fa5]{" + min + "," + max + "}";
		return startCheck(regex, str);
	}

	/**
	 * 查看IP地址是否合法
	 * */
	static public boolean checkIP(String ipAddress) {
		String regex = "(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\."
				+ "(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\."
				+ "(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\."
				+ "(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])";

		return startCheck(regex, ipAddress);
	}

	/**
	 * 判断是否为数字
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str){
		for(int i=str.length();--i>=0;){
			int chr=str.charAt(i);
			if(chr<48 || chr>57)
				return false;
		}
		return true;
	}

	/**
	 * 验证国内电话号码 格式：010-67676767，区号长度3-4位，必须以"0"开头，号码是7-8位
	 * */
	static public boolean checkPhoneNr(String phoneNr) {
		String regex = "^[0]\\d{2,3}\\-?\\d{7,8}";

		return startCheck(regex, phoneNr);
	}

	/**
	 * 验证国内电话号码 格式：6767676, 号码位数必须是7-8位,头一位不能是"0"
	 * */
	static public boolean checkPhoneNrWithoutCode(String phoneNr) {
		String reg = "^[1-9]\\d{6,7}";

		return startCheck(reg, phoneNr);
	}

	/**
	 * 验证国内电话号码 格式：0106767676，共11位或者12位，必须是0开头
	 * */
	static public boolean checkPhoneNrWithoutLine(String phoneNr) {
		String reg = "^[0]\\d{10,11}";

		return startCheck(reg, phoneNr);
	}

	/**
	 * 验证国内身份证号码：15或18位，由数字组成，不能以0开头
	 * */
	static public boolean checkIdCard(String idNr) {
		String reg = "^[1-9](\\d{14}|\\d{17})";

		return startCheck(reg, idNr);
	}

	/**
	 * 网址验证<br>
	 * 符合类型：<br>
	 * http://www.test.com<br>
	 * http://163.com
	 * */
	static public boolean checkWebSite(String url) {
		// http://www.163.com
		String reg = "^(http)\\://(\\w+\\.\\w+\\.\\w+|\\w+\\.\\w+)";

		return startCheck(reg, url);
	}

	/**
	 * 判断字符是否相等
	 * 
	 * @param first
	 * @param second
	 * @return
	 */
	static public boolean checkEqualString(String first, String second) {
		boolean flag = false;
		if (first.equals(second))
			flag = true;

		return flag;
	}

	/**
	 * 判断已选择area是否包含当前区域
	 * 
	 * @param root
	 * @param id
	 * @return
	 */
	static public boolean checkContainArea(String root, String id) {
		boolean flag = false;
		if (root.indexOf(",") > 0) {
			String[] idStr = root.split(",");
			for (int i = 0; i < idStr.length; i++) {
				if (idStr[i].equals(id)) {
					return true;
				}
			}

		} else {
			if (root.equals(id))
				return true;
		}
		return flag;
	}

	/**
	 * 版本号比较
	 * @param version1
	 * @param version2
	 * @return
	 */
	public static boolean getVersion(String version1,String version2){
		
		try {
			//对比前两位
			int q1 = Integer.parseInt(version1.split("\\.")[0]);
			int q2 = Integer.parseInt(version2.split("\\.")[0]);
			if(q1 == q2){
				int qq1 = Integer.parseInt(version1.split("\\.")[1]);
				int qq2 = Integer.parseInt(version2.split("\\.")[1]);
				
				if(qq1 == qq2){
					int qqq1 = Integer.parseInt(version1.split("\\.")[2]);
					int qqq2 = Integer.parseInt(version2.split("\\.")[2]);
					if(qqq1 < qqq2){
						return true;
					}else{
						return false;
					}
				}else if(qq1 < qq2){
					return true;
				}else{
					return false;
				}

			}else if(q1<q2){
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}

	}

	public static String getWeekDate() {
		String[] weekDays = { "周日", "周一", "周二", "周三", "周四", "周五", "周六" };
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());

		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;

		return cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1)
				+ "-" + cal.get(Calendar.DATE) + "  " + weekDays[w];
	}
	
	/**
	 * android开发中怎么将手机号中间四位隐藏  134****2545
	 */
	public static String mobieStr(String mobile){
		if ("".equals(mobile)) {
			return mobile;
		}else {
			String maskNumber = mobile.substring(0,3)+"****"+mobile.substring(7,mobile.length());
			return maskNumber;
		}
		
		
	}
	/**
	 * 邮箱判断正则表达式
	 */
	public static boolean emailFormat(String eMAIL1) {
		Pattern pattern = Pattern
		.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
		Matcher mc = pattern.matcher(eMAIL1);
		return mc.matches();
	}
	/**规范标准是：号牌号码只能由汉字、大写英文字母、数字组成;并且以汉字或"WJ"开头;长度不能小于6位*/
	public static boolean isTypeOk(String cphT) {
		String cph=cphT;
//		String cph="WJ12345";
		Pattern pattern = Pattern.compile("^[\u4e00-\u9fa5|WJ]{1}[A-Z0-9]{6}$");
		Matcher matcher = pattern.matcher(cph);
		if (!matcher.matches()) {
			System.out.println("车牌号格式不对！");
			return false;
		}else{
			System.out.println("车牌号格式正确！");
			return true;
		}
	}
}

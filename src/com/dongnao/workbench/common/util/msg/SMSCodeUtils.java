/**   
* @Title: MsgCodeUtils.java 
* @Package org.k12py.common.utils  
* @author yangfan
* @date 2016年2月19日 下午5:20:15 
* @version V1.0   
*/
package com.dongnao.workbench.common.util.msg;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.util.WebUtils;

import com.dongnao.workbench.basic.model.UserInfo;
import com.dongnao.workbench.common.bean.ResultMessage;
import com.dongnao.workbench.common.enums.ResultStatus;
import com.dongnao.workbench.common.util.DateUtil;
import com.dongnao.workbench.common.util.StringUtil;
import com.dongnao.workbench.common.util.Utils;
import com.dongnao.workbench.school.model.Employee;


/**
 * @ClassName: MsgCodeUtils
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author yangfan
 * @date 2016年2月19日 下午5:20:15
 * 
 */
public final class SMSCodeUtils{
    
    public static final String MESSAGE_CODE= "MESSAGE_CODE";
    
    public static final boolean flag= false;//0代表不发验证码（888888），1代表发验证码
    
    /**
     * 
     * @Title: getMsgCode @Description: 获取当下正有效的验证码，如果没有则返回null @param @param
     * user @param @return 设定文件 @return ResultBean 返回类型 @throws
     */
    public static ResultMessage getMsgCode(HttpServletRequest request,UserInfo user)
        throws Exception{
        return newMsgCode(request,user);
    }
    
    /**
     * 
     * @Title: newMsgCode @Description: 生成新的验证码 @param @param
     * user @param @return 设定文件 @return ResultBean 返回类型 @throws
     */
    public static ResultMessage newMsgCode(HttpServletRequest request,UserInfo user){
        Random rand= new Random();
        String code= (flag?rand.nextInt(899999)+ 100000:888888)+"";
        ResultMessage result= new ResultMessage();
        // 生成、入库
        result.setStatus(ResultStatus.SUCCESS.getValue());
        result.setData(code);
        result.setMessage("");
        WebUtils.setSessionAttribute(request,MESSAGE_CODE,code);
        if(flag){
            try {
            	SMSServiceUtils.sendMessages("18607325595","提现短信验证码:"+code,DateUtil.nowSqlTimestamp().toString());
			} catch (Exception e) {
				e.printStackTrace();
				result.setStatus(ResultStatus.FAIL.getValue());
				result.setMessage(e.getMessage());
			}//发送短信
        }
        return result;
    }
    
    /**
     * 
     * @Title: checkMsgCode @Description: 判断验证码是否正确 @param @param
     * user @param @param code @param @return 设定文件 @return boolean 返回类型 @throws
     */
    public static boolean checkMsgCode(HttpServletRequest request,String smsCode){
       String msgCode = (String)WebUtils.getSessionAttribute(request, MESSAGE_CODE);
       if(StringUtil.isNotBlank(msgCode)){
    	   return smsCode.equals(msgCode);
       }
       return false;
    }
}

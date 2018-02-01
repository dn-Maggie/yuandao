package com.dongnao.workbench.student.controller;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dongnao.workbench.account.model.AccountFinance;
import com.dongnao.workbench.account.service.AccountFinanceService;
import com.dongnao.workbench.basic.model.Subject;
import com.dongnao.workbench.basic.model.UserInfo;
import com.dongnao.workbench.basic.service.SubjectService;
import com.dongnao.workbench.common.bean.ResultMoney;
import com.dongnao.workbench.common.excel.ExcelExpUtils;
import com.dongnao.workbench.common.excel.ExpParamBean;
import com.dongnao.workbench.common.page.Page;
import com.dongnao.workbench.common.util.AjaxUtils;
import com.dongnao.workbench.common.util.DateUtil;
import com.dongnao.workbench.common.util.Utils;
import com.dongnao.workbench.common.util.FormatEntity;
import com.dongnao.workbench.student.model.ContinuePay;
import com.dongnao.workbench.student.model.VipStudent;
import com.dongnao.workbench.student.service.ContinuePayService;
import com.dongnao.workbench.student.service.VipStudentService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


/**
 * 描述：补款管理模块controller类，负责页面分发与跳转
 * 
 * @author yang.liu
 * @version 1.0 2016-05-02
 */
 
@Controller
@RequestMapping("continuePay")
public class ContinuePayController{
         @Resource
         private ContinuePayService continuePayService;
         @Resource
     	 private AccountFinanceService accountFinanceService;
         @Resource
     	 private VipStudentService vipStudentService;
     	@Resource
    	private SubjectService subjectService;
	 
 	/**
 	* 进入新增页面
 	* @return ModelAndView 返回到新增页面
 	*/
 	@RequestMapping("/toAddContinuePay")
	public ModelAndView toAdd(){
 		ModelAndView mv = new ModelAndView(
 				"WEB-INF/jsp/student/continuePay/addContinuePay");
 		
 		AccountFinance accountFlow = accountFinanceService.getByPrimaryKey("17625a1d-a55e-4ef1-9ad0-c48e6582299f");
 		mv.addObject("accountFlow", accountFlow);
 		
		return mv;
	}
	
	/**
	 * 进入查看页面方法
	 * @param key String：实体id
	 * @return ModelAndView: 查询实体
	 */	
	@RequestMapping("/toShowContinuePay")
	public ModelAndView toShow(String key){
		Map<String,Object> model = new HashMap<String,Object>();
		
		ContinuePay entity = continuePayService.getByPrimaryKey(key);
		Map<String,String> continuePay = FormatEntity.getObjectValue(entity);
		
		String stuid = entity.getStuId();
		VipStudent student = vipStudentService.getByPrimaryKey(stuid);
		Map<String,String> vipStudent = FormatEntity.getObjectValue(student);
		
		model.put("continuePay", continuePay);
		model.put("vipStudent", vipStudent);
		
		return new ModelAndView("WEB-INF/jsp/student/continuePay/showContinuePay",model);
	}
	
	
	/**
	 * 新增方法
	 * @param response HttpServletResponse
	 * @param continuePay ContinuePay:实体类
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/addContinuePay")
	public void add(ContinuePay continuePay,HttpServletRequest request,HttpServletResponse response){
	UserInfo user=Utils.getLoginUserInfo(request);
	continuePay.setId(Utils.generateUniqueID());
	continuePay.setOperator(user.getId());
	AjaxUtils.sendAjaxForObjectStr(
				response,continuePayService.add(continuePay));		
	}
	
	/**
	 * 删除方法
	 * @param response HttpServletResponse
	 * @param key String:多个由“，”分割开的id字符串
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/deleteContinuePay")
	public void deleteByKey(String key,HttpServletResponse response){
		String[] str = key.split(",");
		for(int i=0;i<str.length;i++){
			continuePayService.deleteByKey(str[i]);
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("msg", "成功");
		AjaxUtils.sendAjaxForMap(response, map);
	}
	
	/**
	 * 进入列表页面
	 * @return ModelAndView
	 */
	@RequestMapping("/toListContinuePay")
	public ModelAndView toList(String term){
		Map<String,Object> model = new HashMap<String,Object>();
		Calendar calendar = Calendar.getInstance();
		if(term!=null){
 			switch (term) {
			case "month":
				model.put("condition", term);
	 			model.put("currDate", calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MONTH)+1)+"-01");
				break;
			case "year":
				model.put("condition", term);
	 			model.put("currDate", calendar.get(Calendar.YEAR)+"-01-01");
				break;
			default:
				break;
			}
		}
		
		Map<String,List> er = new HashMap<String, List>();
 		List<Subject> list = subjectService.listByCondition(new Subject());
 		
 		er.put("subject", list);
 		model.put("er", er);
		
		/*model.put("allCP",continuePayService.getStatistical(calendar.get(Calendar.YEAR)+""));*/
		model.put("currCP", continuePayService.getStatistical(new ContinuePay()));
		/*model.put("beforeCP", continuePayService.getStatistical(calendar.get(Calendar.YEAR)+""+(calendar.get(Calendar.MONTH))));*/		
		
		return new ModelAndView("WEB-INF/jsp/student/continuePay/listContinuePay",model);
	}

	/**
	 * 根据条件查找列表方法
	 * @param continuePay ContinuePay：实体对象（查询条件）
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @param page Page:分页对象
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/listContinuePay")
	public void listByCondition(ContinuePay continuePay,HttpServletRequest request,
			HttpServletResponse response, Page page){
		continuePay.setPage(page);	
		List<ContinuePay> list = continuePayService.listByCondition(continuePay);
		AjaxUtils.sendAjaxForPage(request, response, page, list);
	}
	
	
	/**
	 * 修改方法
	 * @param continuePay ContinuePay：实体对象
	 * @param response HttpServletResponse
	 * @return: ajax输入json字符串
	 */	
	@RequestMapping("/updateContinuePay")
	public void update(ContinuePay continuePay,HttpServletRequest request,HttpServletResponse response){
		AjaxUtils.sendAjaxForObjectStr(
				response,continuePayService.update(continuePay));	
	}
	
	/**
	 * 进入欠费会员信息选择界面
	 */	
	@RequestMapping("/toSelectOwnVip")
	public ModelAndView toSelectOwnVip(){
		return new ModelAndView("WEB-INF/jsp/student/continuePay/selectOwnVip");
	}
	
	/**
	 * 统计总数
	 */	
	@RequestMapping("/staticContinuePay")
	public void staticContinuePay(ContinuePay continuePay,HttpServletResponse response){
		String joinStartDate ="";String joinEndDate ="" ;String startDate ="";String endDate="";
		try{
			startDate = continuePay.getPropsMap().get("startDate").toString();
			endDate = continuePay.getPropsMap().get("endDate").toString();
			joinStartDate = continuePay.getPropsMap().get("joinStartDate").toString();
			joinEndDate = continuePay.getPropsMap().get("joinEndDate").toString();
		}catch(Exception e){
			
		}
		if(joinStartDate.length()>0||joinEndDate.length()>0){
			continuePay.setJoinStartDate(joinStartDate);
			continuePay.setJoinEndDate(joinEndDate);
		}if(startDate.length()>0||endDate.length()>0){
			continuePay.setStartDate(startDate);
			continuePay.setEndDate(endDate);
		}
		Map<String,Object> model =new HashMap<String, Object>();
		model.put("statics", continuePayService.getStatistical(continuePay));
		AjaxUtils.sendAjaxForMap(response, model);
	}
	
	
	/**
	 * ajax验证本月补款是否已录入
	 * 
	 * @param id、subjectId、courseId、money、month
	 *            id、学科ID、课程ID、金额、月份
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 */
	@RequestMapping("/ajaxGetContinuePayInfo")
	public void ajaxGetContinuePayInfo(ContinuePay continuepay,
			HttpServletRequest request, HttpServletResponse response) {		
		List<ContinuePay> continuePay = continuePayService.listByCondition(continuepay);
		boolean b = false;
		if (continuePay.size()>0) {
			b = true;
		}
		AjaxUtils.sendAjaxForObject(response, b);
	}
	

	@RequestMapping("/exportExcel")
	public void exportExcel(ContinuePay continuepay, ExpParamBean epb,
			HttpServletRequest request, HttpServletResponse response, Page page)
			throws Exception {		
		int expType = Integer.parseInt(request.getParameter("expType"));
		if (expType == 1) {
			continuepay.setPage(page);
		}
		List<ContinuePay> list = continuePayService.listByCondition(continuepay);
		ExcelExpUtils.exportListToExcel(list, response, epb.getFieldlist(),
				"vip学员补款信息列表", "vip学员补款信息列表");
	}
}
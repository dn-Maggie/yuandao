package com.dongnao.workbench.student.controller;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dongnao.workbench.account.model.AccountFinance;
import com.dongnao.workbench.account.service.AccountFinanceService;
import com.dongnao.workbench.basic.model.Subject;
import com.dongnao.workbench.basic.model.UserInfo;
import com.dongnao.workbench.basic.service.SubjectService;
import com.dongnao.workbench.common.excel.ExcelExpUtils;
import com.dongnao.workbench.common.excel.ExpParamBean;
import com.dongnao.workbench.common.page.Page;
import com.dongnao.workbench.common.util.AjaxUtils;
import com.dongnao.workbench.common.util.FormatEntity;
import com.dongnao.workbench.common.util.Utils;
import com.dongnao.workbench.student.model.VipRefund;
import com.dongnao.workbench.student.model.VipStudent;
import com.dongnao.workbench.student.service.VipRefundService;
import com.dongnao.workbench.student.service.VipStudentService;


/**
 * 描述：VIP学员退款信息模块controller类，负责页面分发与跳转
 * 
 * @author maggie
 * @version 1.0 2016-08-01
 */
 
@Controller
@RequestMapping("vipRefund")
public class VipRefundController{
        @Resource
        private VipRefundService vipRefundService;
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
 	@RequestMapping("/toAddVipRefund")
	public ModelAndView toAdd(){
 		ModelAndView mv = new ModelAndView(
 				"WEB-INF/jsp/student/vipRefund/addVipRefund");
 		
 		AccountFinance accountFlow = accountFinanceService.getByPrimaryKey("0289d56d-9f1b-4551-896f-e3ebc091c0b5");
 		mv.addObject("accountFlow", accountFlow);
 		
 		return mv;
	}
	
	/**
	 * 进入查看页面方法
	 * @param key String：实体id
	 * @return ModelAndView: 查询实体
	 */	
	@RequestMapping("/toShowVipRefund")
	public ModelAndView toShow(String key){
		Map<String,Object> model = new HashMap<String,Object>();
		
		VipRefund entity = vipRefundService.getByPrimaryKey(key);
		Map<String,String> vipRefund = FormatEntity.getObjectValue(entity);
		
		String stuid = entity.getStuId();
		VipStudent student = vipStudentService.getByPrimaryKey(stuid);
		Map<String,String> vipStudent = FormatEntity.getObjectValue(student);
		
		model.put("vipRefund", vipRefund);
		model.put("vipStudent", vipStudent);
		return new ModelAndView("WEB-INF/jsp/student/vipRefund/showVipRefund",model);
	}
	
	/**
	 * 新增方法
	 * @param response HttpServletResponse
	 * @param vipRefund VipRefund:实体类
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/addVipRefund")
	public void add(VipRefund vipRefund,HttpServletRequest request,HttpServletResponse response){
	UserInfo user=Utils.getLoginUserInfo(request);
	vipRefund.setId(Utils.generateUniqueID());
	vipRefund.setOperator(user.getId());
	AjaxUtils.sendAjaxForObjectStr(
				response,vipRefundService.add(vipRefund));		
	}
	
	/**
	 * 删除方法
	 * @param response HttpServletResponse
	 * @param key String:多个由“，”分割开的id字符串
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/deleteVipRefund")
	public void deleteByKey(String key,HttpServletResponse response){
		String[] str = key.split(",");
		for(int i=0;i<str.length;i++){
			vipRefundService.deleteByKey(str[i]);
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("msg", "成功");
		AjaxUtils.sendAjaxForMap(response, map);
	}
	
	/**
	 * 进入列表页面
	 * @return ModelAndView
	 */
	@RequestMapping("/toListVipRefund")
	public ModelAndView toList(String term){
		Map<String,Object> model = new HashMap<String,Object>();
		Calendar calendar = Calendar.getInstance();
		if(term!=null){
 			switch (term) {
			case "month":
				model.put("condition", term);
	 			model.put("currDate", calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MONTH)+1)+"-01");
				break;
			default:
				break;
			}
		}
		Map<String,List> er = new HashMap<String, List>();
 		List<Subject> list = subjectService.listByCondition(new Subject());
 		
 		er.put("subject", list);
 		model.put("er", er);
		return new ModelAndView("WEB-INF/jsp/student/vipRefund/listVipRefund",model);
	}
	
	/**
	 * 根据条件查找列表方法
	 * @param vipRefund VipRefund：实体对象（查询条件）
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @param page Page:分页对象
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/listVipRefund")
	public void listByCondition(VipRefund vipRefund,HttpServletRequest request,
			HttpServletResponse response, Page page){
		vipRefund.setPage(page);	
		List<VipRefund> list = vipRefundService.listByCondition(vipRefund);
		AjaxUtils.sendAjaxForPage(request, response, page, list);
	}
	
	/**
	 * 统计总数
	 */	
	@RequestMapping("/staticVipRefund")
	public void staticVipRefund(VipRefund vipRefund,HttpServletResponse response){
		String joinStartDate ="";String joinEndDate ="" ;String startDate ="";String endDate="";
		try{
			startDate = vipRefund.getPropsMap().get("startDate").toString();
			endDate = vipRefund.getPropsMap().get("endDate").toString();
			joinStartDate = vipRefund.getPropsMap().get("joinStartDate").toString();
			joinEndDate = vipRefund.getPropsMap().get("joinEndDate").toString();
		}catch(Exception e){
			
		}
		if(joinStartDate.length()>0||joinEndDate.length()>0){
			vipRefund.setJoinStartDate(joinStartDate);
			vipRefund.setJoinEndDate(joinEndDate);
		}if(startDate.length()>0||endDate.length()>0){
			vipRefund.setStartDate(startDate);
			vipRefund.setEndDate(endDate);
		}
		Map<String,Object> model =new HashMap<String, Object>();
		model.put("statics", vipRefundService.getStatistical(vipRefund));
		AjaxUtils.sendAjaxForMap(response, model);
	}
	
	/**
	 * 修改方法
	 * @param vipRefund VipRefund：实体对象
	 * @param response HttpServletResponse
	 * @return: ajax输入json字符串
	 */	
	@RequestMapping("/updateVipRefund")
	public void update(VipRefund vipRefund,HttpServletRequest request,HttpServletResponse response){
		AjaxUtils.sendAjaxForObjectStr(
				response,vipRefundService.update(vipRefund));	
	}
	
	/**
	 * 进入会员信息选择界面
	 */	
	@RequestMapping("/toSelectAllVip")
	public ModelAndView toSelectOwnVip(){
		return new ModelAndView("WEB-INF/jsp/student/vipRefund/selectAllVip");
	}
	
	@RequestMapping("/exportExcel")
	public void exportExcel(VipRefund vipRefund, ExpParamBean epb,
			HttpServletRequest request, HttpServletResponse response, Page page)
			throws Exception {		
		int expType = Integer.parseInt(request.getParameter("expType"));
		if (expType == 1) {
			vipRefund.setPage(page);
		}
		List<VipRefund> list = vipRefundService.listByCondition(vipRefund);
		ExcelExpUtils.exportListToExcel(list, response, epb.getFieldlist(),
				"vip学员退款信息列表", "vip学员退款信息列表");
	}
	
}
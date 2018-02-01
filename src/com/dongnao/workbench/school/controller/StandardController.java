package com.dongnao.workbench.school.controller;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dongnao.workbench.account.model.AccountFlow;
import com.dongnao.workbench.account.model.FinStatements;
import com.dongnao.workbench.account.service.AccountFlowService;
import com.dongnao.workbench.account.service.AccountFlowServiceImpl;
import com.dongnao.workbench.basic.model.Subject;
import com.dongnao.workbench.basic.service.SubjectService;
import com.dongnao.workbench.basic.service.SubjectServiceImpl;
import com.dongnao.workbench.common.bean.ReportQuerycondition;
import com.dongnao.workbench.common.bean.ResultMoney;
import com.dongnao.workbench.common.page.Page;
import com.dongnao.workbench.common.util.AjaxUtils;
import com.dongnao.workbench.common.util.FormatEntity;
import com.dongnao.workbench.common.util.Utils;
import com.dongnao.workbench.school.model.RecentlyThirtyDayData;
import com.dongnao.workbench.school.model.RecentlyThreeMonthData;
import com.dongnao.workbench.school.model.Standard;
import com.dongnao.workbench.school.model.StudentBarData;
import com.dongnao.workbench.school.service.StandardService;
import com.dongnao.workbench.student.model.Statistical;
import com.dongnao.workbench.student.model.VipStudent;
import com.dongnao.workbench.student.service.ContinuePayService;
import com.dongnao.workbench.student.service.MarketStudentService;
import com.dongnao.workbench.student.service.VipStudentService;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 描述：考核标准表模块controller类，负责页面分发与跳转
 * 
 * @author maggie
 * @version 1.0 2016-07-18
 */
 
@Controller
@RequestMapping("standard")
public class StandardController{
	@Resource
	private SubjectService subjectService;
         @Resource
         private StandardService standardService;
         @Resource
         private VipStudentService vipStudentService;
         @Resource
         private ContinuePayService continuePayService;
         @Resource
     	 private AccountFlowService accountFlowService;
         @Resource
     	private MarketStudentService marketStudentService;
 	/**
 	* 进入新增页面
 	* @return ModelAndView 返回到新增页面
 	*/
 	@RequestMapping("/toAddStandard")
	public ModelAndView toAdd(){
		return new ModelAndView("WEB-INF/jsp/school/standard/addStandard");
	}
	
	/**
	 * 进入查看页面方法
	 * @param key String：实体id
	 * @return ModelAndView: 查询实体
	 */	
	@RequestMapping("/toShowStandard")
	public ModelAndView toShow(String key){
		Standard entity = standardService.getByPrimaryKey(key);
		Map<String,String> standard = FormatEntity.getObjectValue(entity);
		return new ModelAndView("WEB-INF/jsp/school/standard/showStandard","standard",standard );
	}
	
	/**
	 * 进入数据统计列表页面
	 * @return ModelAndView
	 */
	@RequestMapping("/toListStandard")
	public ModelAndView toList() {
		ModelAndView mv = new ModelAndView(
				"WEB-INF/jsp/school/standard/listStandard");
		
 		List<Subject> list = subjectService.listByCondition(new Subject());
 		mv.addObject("subject", list);
		return mv;
	}
	
	/**
	 * 进入图表统计列表页面
	 * @return ModelAndView
	 */
	@RequestMapping("/toListStandardBar")
	public ModelAndView toListBar(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView(
				"WEB-INF/jsp/school/standard/listStandardBar");
		if(Utils.isSuperAdmin(request)){
			mv.addObject("isAdmin",true);
		}
		List<Subject> list = subjectService.listByCondition(new Subject());
 		mv.addObject("subject", list);
		return mv;
	}

	/**
	 * 进入财务报表图表统计列表页面
	 * @return ModelAndView
	 */
	@RequestMapping("/toListFinStatements")
	public ModelAndView toFinStatements(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("WEB-INF/jsp/school/standard/finStatements");
		List<Subject> list = subjectService.listByCondition(new Subject());
 		mv.addObject("subjectList", list);
		return mv;
	}

	/**
	 * 财务报表图表统计
	 * @return ModelAndView
	 */
	@RequestMapping("/listFinStatements")
	public void finStatements(@RequestBody String param, HttpServletRequest request, HttpServletResponse response,
			Page page) {
		ReportQuerycondition rqc = new ReportQuerycondition();
		rqc.setPage(page);
		List<FinStatements> finStaList = new ArrayList<FinStatements>();
		FinStatements zyjobj = null;
		FinStatements zcbobj = null;
		if (param == null || param.length() == 0) {// 第一次进入页面
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
			Date date = new Date();
			String nowyear = sdf.format(date);
			rqc.setYear(nowyear);
			// 获取所有部门名称
			List<Subject> Deptlist = subjectService.listByCondition(new Subject());
			for (int i = 0; i < Deptlist.size(); i++) {// 第一次进入页面默认查询所有部门数据,包括总业绩，总成本，--总利润在jsp页面处理
				rqc.setDeptname(Deptlist.get(i).getName());
				rqc.setClasstype("achieve");
				zyjobj = accountFlowService.reportlistByzyj(rqc).size()>0?accountFlowService.reportlistByzyj(rqc).get(0):new FinStatements();// 查询总业绩
				rqc.setClasstype("cost");
				zcbobj = accountFlowService.reportlistByzyj(rqc).size()>0?accountFlowService.reportlistByzyj(rqc).get(0):new FinStatements();// 查询总成本
				finStaList.add(zyjobj);
				finStaList.add(zcbobj);
			}
		} else {// param不为空说明有条件传入-
			JSONObject jso = JSONArray.fromObject(param).getJSONObject(0);
			if (jso.getString("r_year") == null || jso.getString("r_year").length() == 0) {// 如没有选择年份条件，默认设置当前年份
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
				Date date = new Date();
				String nowyear = sdf.format(date);
				rqc.setYear(nowyear);
			} else {
				rqc.setYear(jso.getString("r_year"));
			}
			// rqc.setZcbcolunm(StringUtils.defaultIfEmpty(jso.getString("zcbcolunm"),
			// StringUtils.EMPTY));
			// rqc.setZyjcolunm(StringUtils.defaultIfEmpty(jso.getString("zyjcolunm"),
			// StringUtils.EMPTY));
			String arr1[] = null;
			arr1 = jso.getString("deptArr").split(",");// 获取选择的想要查询的部门
			if (arr1[0].length() != 0) {// 说明有部门筛选条件
				for (int i = 0; i < arr1.length; i++) {
					rqc.setDeptname(arr1[i]);
					rqc.setClasstype("achieve");
					zyjobj = accountFlowService.reportlistByzyj(rqc).size()>0?accountFlowService.reportlistByzyj(rqc).get(0):new FinStatements();// 查询总业绩
					finStaList.add(zyjobj);
					rqc.setClasstype("cost");
					zcbobj = accountFlowService.reportlistByzyj(rqc).size()>0?accountFlowService.reportlistByzyj(rqc).get(0):new FinStatements();// 查询总成本
					finStaList.add(zcbobj);
				}
			} else {// 说明没有部门筛选条件，就查询所有部门数据
				List<Subject> Deptlist = subjectService.listByCondition(new Subject());
				for (int i = 0; i < Deptlist.size(); i++) {
					rqc.setDeptname(Deptlist.get(i).getName());
					rqc.setClasstype("achieve");
					zyjobj = accountFlowService.reportlistByzyj(rqc).size()>0?accountFlowService.reportlistByzyj(rqc).get(0):new FinStatements();// 查询总业绩
					finStaList.add(zyjobj);
					rqc.setClasstype("cost");
					zcbobj = accountFlowService.reportlistByzyj(rqc).size()>0?accountFlowService.reportlistByzyj(rqc).get(0):new FinStatements();// 查询总成本
					finStaList.add(zcbobj);
				}
			}
		}
		AjaxUtils.sendAjaxForPage(request, response, page, finStaList);
	}

	/**
	 * 获取统计数据
	 * @return ModelAndView
	 */
	@RequestMapping("/getStatistic")
	public void getStatistic(String month,String subject,HttpServletRequest request,HttpServletResponse response) {
		/*if(month!=""){*/
			Map<Object,Object> model = new HashMap<Object,Object>();
			VipStudent vipstudent = new VipStudent();
			vipstudent.setJointime(month);
			vipstudent.setSubjectId(subject);
			model.put("curr", vipStudentService.getStatistical(vipstudent));
			
			model.put("currMarkStu", marketStudentService.getMarkStuCount(month));
			
			AccountFlow accountxf = new AccountFlow();
			accountxf.setCreateTime(month);
			accountxf.setSubjectName(subject);;
			model.put("currxf", accountFlowService.getXFMoney(accountxf));
			AjaxUtils.sendAjaxForMap(response, model);
		/*}*/
	}
	
	/**
	 * 
	 * 获取学生统计数据
	 * @return ModelAndView
	 */
	@RequestMapping("/getStuStatistic")
	public void getStuStatistic(VipStudent vipStudent, HttpServletRequest request, HttpServletResponse response) {
		String joinStartDate = "";
		String joinEndDate = "";
		try {
			joinStartDate = vipStudent.getPropsMap().get("joinStartDate").toString();
			joinEndDate = vipStudent.getPropsMap().get("joinEndDate").toString();
		} catch (Exception e) {
		}
		if (joinStartDate.length() > 0 || joinEndDate.length() > 0) {
			vipStudent.setJoinStartDate(joinStartDate);
			vipStudent.setJoinEndDate(joinEndDate);
		}
		Map<Object, Object> model = new HashMap<Object, Object>();
		model.put("curr", vipStudentService.getTotal(vipStudent));
		AjaxUtils.sendAjaxForMap(response, model);
	}
	
	/**
	 *  获取某个部门最近30天的业绩数据
	 */
	
	@RequestMapping("/getRecentlyThirtyDayData")
	public void getRecentlyThirtyDayData(String subjectName,HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
		Map<Object,Object> model = new HashMap<Object,Object>();
		List<RecentlyThirtyDayData> rtdd = accountFlowService.getRecentlyThirtyDayData(subjectName);
		model.put("rd", rtdd);
		AjaxUtils.sendAjaxForMap(response, model);
	}
	
	/**
	 *  获取最近三个月的每日业绩对比数据
	 */
	
	@RequestMapping("/getRecentlyThreeMonthData")
	public void getRecentlyThreeMonthData(String subjectName,HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
		RecentlyThreeMonthData rt = new RecentlyThreeMonthData();
		Map<Object,Object> model = new HashMap<Object,Object>();
		if(subjectName!=null && subjectName!="")rt.setSubjectName(subjectName);
		
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		int month = (now.get(Calendar.MONTH) + 1);
				
		for(int i=0;i<3;i++){
			int queryMonth = 1;
			if((month - i)<=0){
				queryMonth = month - i + 12;
				year--;
			}else{
				queryMonth = month - i;
			}
			rt.setYearMonth(year + "-" + queryMonth);
			List<RecentlyThreeMonthData> rtlist = accountFlowService.getRecentlyThreeMonthData(rt);
			model.put("month" + i, rtlist);
		}
		AjaxUtils.sendAjaxForMap(response, model);
	}
	
	/**
	 *获取学生报名人数数据
	 */
	
	@RequestMapping("/getStudentBarData")
	public void getStudentBarData(String year,String subjectName,HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
		Map<Object,Object> model = new HashMap<Object,Object>();
		StudentBarData sb = new StudentBarData();
		sb.setYear(year);
		sb.setSubjectName(subjectName);
		sb = vipStudentService.getStudentBarData(sb);
		model.put("sb", sb);
		AjaxUtils.sendAjaxForMap(response, model);
	}
	
	/**
	 * 获取统计图表数据
	 * @return ModelAndView
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("/getBarStatistic")
	public void getBarStatistic(String months,String subjectName,HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
		Map<Object,Object> model = new HashMap<Object,Object>();
		if(months!=""){
			String[] yearMonth = months.split(",");//拆分年月
			for(int i=0;i<yearMonth.length;i++){
				String[] ym = yearMonth[i].split("-");//分别获取日期的年、月，存入数组ym中
				int month = Integer.parseInt(ym[1]);//将月份转化为整型
				AccountFlow accountFlow = new AccountFlow();//创建一个财务流水实例
				if(subjectName!=""&&subjectName!=null)accountFlow.setSubjectName(subjectName);//设置科目为所传入科目
				accountFlow.setCreateTime(ym[0]+month);//设置时间为所传入年月
				ResultMoney resm  = accountFlowService.getBarStatistic(accountFlow);//根据查询条件获取查询值并添加在对象中
				if(resm==null)resm = new ResultMoney();
				VipStudent vipStudent = new VipStudent();//创建一个学生类实例
				vipStudent.setJointime(ym[0]+month);//时间为所传入年月
				if(subjectName!=""&&subjectName!=null)vipStudent.setSubjectName(subjectName);//科目
				List<Statistical> sv = vipStudentService.getTotal(vipStudent);//获取本月应收账款
				if(sv.size()>0&&sv.get(0).getShouldPay()!=null){
					resm.setShouldPay(sv.get(0).getShouldPay().doubleValue());//添加在对象中
				}else{resm.setShouldPay(0.00);}
				model.put(yearMonth[i], resm);
			}
		}else{
			initBar(model,subjectName);
		}
		AjaxUtils.sendAjaxForMap(response, model);
	}
	//初始化图表(没有月份，初始默认为五个最近月份)
	public void initBar(Map<Object, Object> modelObj,String subjectName) {
		Calendar calendar = Calendar.getInstance();
		int month = calendar.get(Calendar.MONTH)+1;
		for(int i=0;i<=5;i++){
			AccountFlow accountFlow = new AccountFlow();
			if(subjectName!=""&&subjectName!=null)accountFlow.setSubjectName(subjectName);
			int year = calendar.get(Calendar.YEAR);
				if(month-i<=0){
					year=year-1;
				}
				accountFlow.setCreateTime(year+""+(((month-i>0)?(month-i):(month-i+12))<10?"0"+((month-i>0)?(month-i):(month-i+12)):((month-i>0)?(month-i):(month-i+12)))+"01");
				ResultMoney resm = accountFlowService.getBarStatistic(accountFlow);
				if(resm==null)resm = new ResultMoney();
				
				
				//获取本月应收账款
				VipStudent vipStudent = new VipStudent();
				vipStudent.setJointime(year+""+((month-i>0)?(month-i):(month-i+12)));
				if(subjectName!=""&&subjectName!=null)vipStudent.setSubjectName(subjectName);
				
				
				List<Statistical> sv = vipStudentService.getTotal(vipStudent);
				
				
				//添加在对象中
				if(sv.size()>0&&sv.get(0).getShouldPay()!=null){
					resm.setShouldPay(Double.valueOf(sv.get(0).getShouldPay().toString()));
				}else{resm.setShouldPay(0.00);}
				modelObj.put(year+"-"+(((month-i>0)?(month-i):(month-i+12))<10?"0"+((month-i>0)?(month-i):(month-i+12)):((month-i>0)?(month-i):(month-i+12))),resm);
		}
	}

	/**
	 * 根据条件查找列表方法
	 * @param standard Standard：实体对象（查询条件）
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @param page Page:分页对象
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/listStandard")
	public void listByCondition(Standard standard,HttpServletRequest request,
			HttpServletResponse response, Page page){
		standard.setPage(page);	
		List<Standard> list = standardService.listByCondition(standard);
		AjaxUtils.sendAjaxForPage(request, response, page, list);
	}
	
	/**
	 * 进入修改页面方法
	 * @param key String：实体id
	 * @return ModelAndView: 查询实体
	 */	
	@RequestMapping("/toEditStandard")
	public ModelAndView toEdit(String key){
		Standard entity = standardService.getByPrimaryKey(key);
		Map<String,String> standard = FormatEntity.getObjectValue(entity);
		return new ModelAndView("WEB-INF/jsp/school/standard/editStandard","standard",standard );
	}
	
	/**
	 * 修改方法
	 * @param standard Standard：实体对象
	 * @param response HttpServletResponse
	 * @return: ajax输入json字符串
	 */	
	@RequestMapping("/updateStandard")
	public void update(Standard standard,HttpServletRequest request,HttpServletResponse response){
		AjaxUtils.sendAjaxForObjectStr(
				response,standardService.update(standard));	
	}
	
	/**
	 * 异步ajax查询业绩比例rate
	 */	
 	@RequestMapping("/getRate")
 	public void getRate(String parentId,String subId,String positionId,HttpServletResponse response){
 		Standard standard = new Standard();
 		standard.setParentId(parentId);
 		standard.setSubId(subId);
 		standard.setPositionId(positionId);
 		List<Standard> standards=standardService.listByCondition(standard);
 		if(standards!=null&&standards.size()==1){
 			AjaxUtils.sendAjaxForObjectStr(response, standards.get(0));
 		}else{
 			AjaxUtils.sendAjaxForObjectStr(response, standard);
 		}
 		
 	}
 	
}
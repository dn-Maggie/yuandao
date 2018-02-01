package com.dongnao.workbench.school.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dongnao.workbench.basic.model.Duty;
import com.dongnao.workbench.basic.model.Org;
import com.dongnao.workbench.basic.model.UserInfo;
import com.dongnao.workbench.basic.service.DutyLevelService;
import com.dongnao.workbench.basic.service.DutyService;
import com.dongnao.workbench.basic.service.OrgService;
import com.dongnao.workbench.common.page.Page;
import com.dongnao.workbench.common.util.AjaxUtils;
import com.dongnao.workbench.common.util.Utils;
import com.dongnao.workbench.school.model.CheckHtmlForm;
import com.dongnao.workbench.school.model.EmpCheck;
import com.dongnao.workbench.school.model.Employee;
import com.dongnao.workbench.school.service.EmpCheckService;
import com.dongnao.workbench.school.service.EmployeeService;
import com.dongnao.workbench.system.service.DictInfoService;


/**
 * 描述：员工模块controller类，负责页面分发与跳转
 * 
 * @author maggie
 * @version 1.0 2016-07-15
 */
 
@Controller
@RequestMapping("empCheck")
public class EmpCheckController{
	
	Logger logger = Logger.getLogger(EmpCheckController.class);// 日志
	
    @Resource
	private EmployeeService employeeService;
    @Resource
	private EmpCheckService empCheckService;
    @Resource
    private OrgService orgService;
    @Resource
    private DictInfoService dictInfoService;
    @Resource
	private DutyService dutyService;
    @Resource
	private DutyLevelService dutyLevelService;

	/**
	 * 进入查看员工考核信息页面方法
	 * @param key String：实体id
	 * @return ModelAndView: 查询实体
	 */	
	@RequestMapping("/toShowAssinfo")
	public ModelAndView ShowAssinfo(String key){
		ModelAndView mv = new ModelAndView(
				"WEB-INF/jsp/school/empCheck/ShowAssessmentinfo");
		EmpCheck empCheck = empCheckService.getByPrimaryKey(key);
		mv.addObject("empCheck", empCheck);
		return mv;
	}
	
	
	/**
	 * 员工进入查看自己的考核信息页面方法
	 * @param key String：实体id
	 * @return ModelAndView: 查询实体
	 */	
	@RequestMapping("/toShowMyAssinfo")
	public ModelAndView ShowMyAssinfo(HttpServletRequest request,HttpServletResponse response){
		UserInfo user = Utils.getLoginUserInfo(request);
		ModelAndView mv = new ModelAndView(
				"WEB-INF/jsp/school/empCheck/ShowAssessmentinfo");
		EmpCheck empCheck = new EmpCheck();
		empCheck.setEmpName(user.getFullName());
		mv.addObject("empCheck", empCheck);
		mv.addObject("user", user);
		return mv;
	}
	/**
	 * 进入当月考核页面
	 * @return ModelAndView
	 */
	@RequestMapping("/toAssessment")
	public ModelAndView toAssessment(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("WEB-INF/jsp/school/empCheck/Assessment");
/*		Employee entity = employeeService.getByPrimaryKey(Utils.getLoginUserInfo(request).getId());
		Org org = new Org();
		org.setOrgNo(entity.getDeptNo());
		org=orgService.listByCondition(org).get(0);
 		mv.addObject("org",org);*/
		return mv;
	}
	
	@RequestMapping("/saveAssinfo")
	public void saveAssinfo(EmpCheck empCheck,HttpServletRequest request,
			HttpServletResponse response, Page page){	
		empCheck.setCheckPeople(Utils.getLoginUserInfo(request).getFullName());
		List<EmpCheck> lt = empCheckService.listByCondition(empCheck);
		if(lt.size()>0){
			empCheck.setState(1);
			empCheck.setPage(page);	
			List<EmpCheck> list = empCheckService.listByCondition(empCheck);
			AjaxUtils.sendAjaxForPage(request, response, page, list);
		}else{
			Employee employee=new Employee();
			employee.setCheckName(Utils.getLoginUserInfo(request).getFullName());
			employee.setIsAssess(1);
			List<Employee> list = employeeService.listByCondition(employee);
			List<EmpCheck> empc = new ArrayList<EmpCheck>();
			Iterator it = list.iterator();
			while(it.hasNext()) {
				Employee e = (Employee) it.next();
				EmpCheck ec=new EmpCheck();
				ec.setEmpNo(e.getEmpNo());
				ec.setEmpName(e.getName());
				ec.setPost(e.getPosition());
				ec.setDepartment(e.getDept());
				ec.setCheckMonth(empCheck.getCheckMonth());
				ec.setCheckNote("");
				ec.setCheckPoint("");
				ec.setNickName(e.getNickName());
				ec.setState(1);
				ec.setCheckPeople(e.getCheckName());
				ec.setCheckStanderd(e.getCheckStanderd());
				ec.setIsConfirm(1);
				empc.add(ec);
			}
			//插入部门所有员工未考核的数据
			if(empc.size()>0){
				empCheckService.add(empc);
			}
			empCheck.setPage(page);	
			List<EmpCheck> list2 = empCheckService.listByCondition(empCheck);
			AjaxUtils.sendAjaxForPage(request, response, page, list2);
		}
	}
	
/*	*//**
	 * 修改方法
	 * @param empCheck EmpCheck：实体对象
	 * @param response HttpServletResponse
	 * @return: ajax输入json字符串
	 *//*	
	@RequestMapping("/update")
	public void update(EmpCheck empCheck,HttpServletRequest request,HttpServletResponse response){
		empCheck.setState(2);
		AjaxUtils.sendAjaxForObjectStr(response,empCheckService.update(empCheck));	
	}
	*/
	/**
	 * 进入员工考核表格页面方法
	 * @param key String：实体id
	 * @return ModelAndView: 查询实体
	 */	
	@RequestMapping("/toCheckTable")
	public ModelAndView toCheckTable(String key,HttpServletRequest request,
			HttpServletResponse response){
		UserInfo user = Utils.getLoginUserInfo(request);
		EmpCheck empCheck = empCheckService.getByPrimaryKey(key);
		String mvstr = "";
		int checkstanderd = 1;
		checkstanderd = empCheck.getCheckStanderd();
	    switch (checkstanderd){
	        case 2:mvstr = "WEB-INF/jsp/school/empCheck/module/kf";break;
	        case 3:mvstr = "WEB-INF/jsp/school/empCheck/module/gkkjs";break;
	        case 4:mvstr = "WEB-INF/jsp/school/empCheck/module/vipjs";break;
	        case 5:mvstr = "WEB-INF/jsp/school/empCheck/module/zj";break;
	        case 6:mvstr = "WEB-INF/jsp/school/empCheck/module/bzr";break;
	        default:mvstr = "WEB-INF/jsp/school/empCheck/module/znbm";break;
	    }
	    ModelAndView mv = new ModelAndView(mvstr);
		ArrayList<Object> arr = new ArrayList<>();
		for(int i=0;i<30;i++){
			arr.add(i+1);
		}
		mv.addObject("empCheck", empCheck);
		mv.addObject("arr", arr);
		mv.addObject("user", user);
		mv.addObject("type", "check");
		return mv;
	}
	
	/**
	 * 查看员工考核情况方法
	 * @param key String：实体id
	 * @return ModelAndView: 查询实体
	 */	
	@RequestMapping("/showCheckForm")
	public ModelAndView showCheckForm(String key,String commonEmp,HttpServletRequest request,
			HttpServletResponse response){
		UserInfo user = Utils.getLoginUserInfo(request);
		EmpCheck empCheck = empCheckService.showCheckFormKey(key);
		String mvstr = "";
		int checkstanderd = 1;
		checkstanderd = empCheck.getCheckStanderd();
	    switch (checkstanderd){
	        case 2:mvstr = "WEB-INF/jsp/school/empCheck/module/kf";break;
	        case 3:mvstr = "WEB-INF/jsp/school/empCheck/module/gkkjs";break;
	        case 4:mvstr = "WEB-INF/jsp/school/empCheck/module/vipjs";break;
	        case 5:mvstr = "WEB-INF/jsp/school/empCheck/module/zj";break;
	        case 6:mvstr = "WEB-INF/jsp/school/empCheck/module/bzr";break;
	        default:mvstr = "WEB-INF/jsp/school/empCheck/module/znbm";break;
	    }
	    CheckHtmlForm checkHtmlForm = new CheckHtmlForm();
	    String checkPointStr = empCheck.getCheckPoint();
    	String checkNoteStr = empCheck.getCheckNote();
    	String[] pointArray = checkPointStr.split(",");
        String[] noteArray = checkNoteStr.split("_");
        checkHtmlForm.setCore1(pointArray[0]);
        checkHtmlForm.setCore2(pointArray[1]);
        checkHtmlForm.setCore3(pointArray[2]);
        checkHtmlForm.setCore4(pointArray[3]);
        checkHtmlForm.setCore5(pointArray[4]);
        checkHtmlForm.setText1(noteArray[0]);
        checkHtmlForm.setText2(noteArray[1]);
        checkHtmlForm.setText3(noteArray[2]);
        checkHtmlForm.setText4(noteArray[3]);
        checkHtmlForm.setText5(noteArray[4]);
        switch (checkstanderd) {
		case 1:
			checkHtmlForm.setTotalcore(pointArray[5]);
			break;
		case 2:
			checkHtmlForm.setCore6(pointArray[5]);
        	checkHtmlForm.setTotalcore(pointArray[6]);
        	checkHtmlForm.setText6(noteArray[5]);
			break;
		case 3:
			checkHtmlForm.setCore6(pointArray[5]);
        	checkHtmlForm.setTotalcore(pointArray[6]);
        	checkHtmlForm.setText6(noteArray[5]);
			break;
		case 4:
			checkHtmlForm.setCore6(pointArray[5]);
        	checkHtmlForm.setCore7(pointArray[6]);
        	checkHtmlForm.setTotalcore(pointArray[7]);
        	checkHtmlForm.setText6(noteArray[5]);
        	checkHtmlForm.setText7(noteArray[6]);
			break;
		case 5:
			checkHtmlForm.setCore6(pointArray[5]);
        	checkHtmlForm.setCore7(pointArray[6]);
        	checkHtmlForm.setTotalcore(pointArray[7]);
        	checkHtmlForm.setText6(noteArray[5]);
        	checkHtmlForm.setText7(noteArray[6]);
			break;
		default:
			checkHtmlForm.setCore6(pointArray[5]);
        	checkHtmlForm.setCore7(pointArray[6]);
        	checkHtmlForm.setCore8(pointArray[7]);
        	checkHtmlForm.setTotalcore(pointArray[8]);
        	checkHtmlForm.setText6(noteArray[5]);
        	checkHtmlForm.setText7(noteArray[6]);
        	checkHtmlForm.setText8(noteArray[7]);
			break;
		}     
	    ModelAndView mv = new ModelAndView(mvstr);
		mv.addObject("empCheck", empCheck);
		mv.addObject("checkHtmlForm", checkHtmlForm);
		mv.addObject("type", "show");
		mv.addObject("user", user);
		mv.addObject("commonEmp", commonEmp);
		return mv;
	}
	
	/**
	 * 员工考核--记录成绩与生成html文件方法
	 * @param response HttpServletResponse
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/checkproducehtml")
	public void addLot(CheckHtmlForm checkHtmlForm,HttpServletRequest request,HttpServletResponse response){
			EmpCheck empCheck = new EmpCheck();
			empCheck.setCheckMonth(checkHtmlForm.getCheckMonth());
			empCheck.setEmpName(checkHtmlForm.getEmpName());
			empCheck.setCheckPoint(checkHtmlForm.getTotalcore());
			empCheck.setState(2);
			empCheckService.update(empCheck);
			int checkStanderd = checkHtmlForm.getCheckStanderd();
			String filePath = "";
		    switch (checkStanderd){
		        case 2:filePath = "/usr/dnfile/checkmodel/kf.html";break;
		        case 3:filePath = "/usr/dnfile/checkmodel/gkkjs.html";break;
		        case 4:filePath = "/usr/dnfile/checkmodel/vipjs.html";break;
		        case 5:filePath = "/usr/dnfile/checkmodel/zj.html";break;
		        case 6:filePath = "/usr/dnfile/checkmodel/bzr.html";break;
		        default:filePath = "/usr/dnfile/checkmodel/znbm.html"; break;
		    }

	        try {
	        	System.setProperty("sun.jnu.encoding","UTF-8");
	        	final String encoding = System.getProperty("file.encoding");      
	            System.out.println("encoding-----------------------:"+encoding); 
	        	String templateContent = "";
	        	BufferedReader  r=new BufferedReader(new InputStreamReader(new FileInputStream(filePath),"UTF-8"));    
	            String str="";
	            while((str = r.readLine())!=null) {
	            	templateContent += str;
	            }
	            templateContent = templateContent.replaceAll("#name#", checkHtmlForm.getEmpName());
	        	templateContent = templateContent.replaceAll("#month#", checkHtmlForm.getCheckMonth());
	        	templateContent = templateContent.replaceAll("#core1#", checkHtmlForm.getCore1());
	        	templateContent = templateContent.replaceAll("#core2#", checkHtmlForm.getCore2());
	        	templateContent = templateContent.replaceAll("#core3#", checkHtmlForm.getCore3());
	        	templateContent = templateContent.replaceAll("#core4#", checkHtmlForm.getCore4());
	        	templateContent = templateContent.replaceAll("#core5#", checkHtmlForm.getCore5());
	        	templateContent = templateContent.replaceAll("#note1#", StringUtils.defaultIfEmpty(checkHtmlForm.getText1(),""));
	        	templateContent = templateContent.replaceAll("#note2#", StringUtils.defaultIfEmpty(checkHtmlForm.getText2(),""));
	        	templateContent = templateContent.replaceAll("#note3#", StringUtils.defaultIfEmpty(checkHtmlForm.getText3(),""));
	        	templateContent = templateContent.replaceAll("#note4#", StringUtils.defaultIfEmpty(checkHtmlForm.getText4(),""));
	        	templateContent = templateContent.replaceAll("#note5#", StringUtils.defaultIfEmpty(checkHtmlForm.getText5(),""));
	        	templateContent = templateContent.replaceAll("#total#", checkHtmlForm.getTotalcore());
	        	if(checkHtmlForm.getCore6()!=null){
	        		templateContent = templateContent.replaceAll("#core6#", checkHtmlForm.getCore6());
	        	}
	        	if(checkHtmlForm.getCore7()!=null){
	        		templateContent = templateContent.replaceAll("#core7#", checkHtmlForm.getCore7());
	        	}
	        	if(checkHtmlForm.getCore8()!=null){
	        		templateContent = templateContent.replaceAll("#core8#", checkHtmlForm.getCore8());
	        	}
	        	if(checkHtmlForm.getText6()!=null){
	        		templateContent = templateContent.replaceAll("#note6#", checkHtmlForm.getText6());
	        	}
	        	if(checkHtmlForm.getText7()!=null){
	        		templateContent = templateContent.replaceAll("#note7#", checkHtmlForm.getText7());
	        	}
	        	if(checkHtmlForm.getText8()!=null){
	        		templateContent = templateContent.replaceAll("#note8#", checkHtmlForm.getText8());
	        	}
	        	
	        	logger.debug("文件内容=====" + templateContent);
	        	String fileame = checkHtmlForm.getEmpNo() + "-" + checkHtmlForm.getCheckMonth() + ".html";//文件名
	        	//System.out.println(checkHtmlForm.getEmpName());
	        	fileame = "/usr/dnfile/checkfile/" + fileame;//生成的html文件保存路径。D:\\TestFile\\checkfile\\   --/usr/dnfile/checkfile/     	
	        	logger.debug("====================文件名1====================" + fileame);
	        	byte[] bytes = fileame.getBytes("UTF-8");
	        	String f = new String(bytes,"UTF-8");
	        	logger.debug("====================文件名2====================" + f);
	        	BufferedWriter  writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f),"UTF-8"));
	        	writer.write(templateContent);     
	        	writer.flush();     
	        	writer.close(); 
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}
	
	/**
	 * 员工考核--保存考核数据到数据库
	 * @param response HttpServletResponse
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/saveCheckData")
	public void saveCheckData(CheckHtmlForm checkHtmlForm,HttpServletRequest request,HttpServletResponse response){
				EmpCheck empCheck = new EmpCheck();
				empCheck.setCheckMonth(checkHtmlForm.getCheckMonth());
				empCheck.setEmpName(checkHtmlForm.getEmpName());
				empCheck.setState(2);
				empCheck.setIsConfirm(1);
	        	String checkPointStr = "";
	        	String checkNoteStr = "";
	        	checkPointStr = checkHtmlForm.getCore1() + "," + checkHtmlForm.getCore2() + "," + checkHtmlForm.getCore3() + "," + checkHtmlForm.getCore4() + "," + checkHtmlForm.getCore5();
	        	checkNoteStr = StringUtils.defaultIfEmpty(checkHtmlForm.getText1(),"无") + "_" + StringUtils.defaultIfEmpty(checkHtmlForm.getText2(),"无")+ "_" + StringUtils.defaultIfEmpty(checkHtmlForm.getText3(),"无")+ "_" + StringUtils.defaultIfEmpty(checkHtmlForm.getText4(),"无")+ "_" + StringUtils.defaultIfEmpty(checkHtmlForm.getText5(),"无");
	        	if(checkHtmlForm.getCore6()!=null){
	        		checkPointStr = checkPointStr + ',' + checkHtmlForm.getCore6();
	        		checkNoteStr = checkNoteStr + '_' + StringUtils.defaultIfEmpty(checkHtmlForm.getText6(),"无");
	        	}
	        	if(checkHtmlForm.getCore7()!=null){
	        		checkPointStr = checkPointStr + ',' + checkHtmlForm.getCore7();
	        		checkNoteStr = checkNoteStr + '_' + StringUtils.defaultIfEmpty(checkHtmlForm.getText7(),"无");
	        	}
	        	if(checkHtmlForm.getCore8()!=null){
	        		checkPointStr = checkPointStr + ',' + checkHtmlForm.getCore8();
	        		checkNoteStr = checkNoteStr + '_' + StringUtils.defaultIfEmpty(checkHtmlForm.getText8(),"无");
	        	}
	        	checkPointStr = checkPointStr + "," + checkHtmlForm.getTotalcore();
	        	empCheck.setCheckPoint(checkPointStr);
	        	empCheck.setCheckNote(checkNoteStr);
	        	empCheckService.update(empCheck);
	}
	
	
	/**
	 * 员工确认考核单方法
	 * @throws UnsupportedEncodingException 
	 */	
	@RequestMapping("/empConfirm")
	public void empConfirm(EmpCheck empCheck,HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException{
/*		Employee employee =new Employee();
		employee.setName(new String(empCheck.getEmpName().getBytes("ISO-8859-1"),"utf-8"));以后禁止使用中文字段来做匹配*/
		if(empCheckService.empConfirm(empCheck)>0){
			AjaxUtils.sendAjaxSuccessMessage(response);
		}else{
			AjaxUtils.sendAjaxFailureMessage(response);
		};
	}
}
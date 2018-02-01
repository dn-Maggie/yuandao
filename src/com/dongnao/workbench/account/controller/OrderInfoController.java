package com.dongnao.workbench.account.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dongnao.workbench.common.page.Page;
import com.dongnao.workbench.common.util.AjaxUtils;
import com.dongnao.workbench.common.util.Utils;
import com.dongnao.workbench.student.model.VipStudent;
import com.dongnao.workbench.common.util.FormatEntity;
import com.dongnao.workbench.account.model.OrderInfo;
import com.dongnao.workbench.account.service.OrderInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


/**
 * 描述：订单信息模块controller类，负责页面分发与跳转
 * 
 * @author maggie
 * @version 1.0 2016-08-30
 */
 
@Controller
@RequestMapping("orderInfo")
public class OrderInfoController{
         @Resource
	private OrderInfoService orderInfoService;
	 
 	/**
 	* 进入新增页面
 	* @return ModelAndView 返回到新增页面
 	*/
 	@RequestMapping("/toAddOrderInfo")
	public ModelAndView toAdd(){
		return new ModelAndView("WEB-INF/jsp/account/orderInfo/addOrderInfo");
	}
	
	/**
	 * 进入查看页面方法
	 * @param key String：实体id
	 * @return ModelAndView: 查询实体
	 */	
	@RequestMapping("/toShowOrderInfo")
	public ModelAndView toShow(String key){
		OrderInfo entity = orderInfoService.getByPrimaryKey(key);
		Map<String,String> orderInfo = FormatEntity.getObjectValue(entity);
		return new ModelAndView("WEB-INF/jsp/account/orderInfo/showOrderInfo","orderInfo",orderInfo );
	}
	
	/**
	 * 新增方法
	 * @param response HttpServletResponse
	 * @param orderInfo OrderInfo:实体类
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/addOrderInfo")
	public void add(OrderInfo orderInfo,HttpServletRequest request,HttpServletResponse response){
	orderInfo.setId(Utils.generateUniqueID());
	AjaxUtils.sendAjaxForObjectStr(
				response,orderInfoService.add(orderInfo));		
	}
	
	/**
	 * 删除方法
	 * @param response HttpServletResponse
	 * @param key String:多个由“，”分割开的id字符串
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/deleteOrderInfo")
	public void deleteByKey(String key,HttpServletResponse response){
		String[] str = key.split(",");
		for(int i=0;i<str.length;i++){
			orderInfoService.deleteByKey(str[i]);
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("msg", "成功");
		AjaxUtils.sendAjaxForMap(response, map);
	}
	
	/**
	 * 进入列表页面
	 * @return ModelAndView
	 */
	@RequestMapping("/toListOrderInfo")
	public ModelAndView toList(){
		return new ModelAndView("WEB-INF/jsp/account/orderInfo/listOrderInfo");
	}
	
	/**
	 * 根据条件查找列表方法
	 * @param orderInfo OrderInfo：实体对象（查询条件）
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @param page Page:分页对象
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/listOrderInfo")
	public void listByCondition(OrderInfo orderInfo,HttpServletRequest request,
			HttpServletResponse response, Page page){
		orderInfo.setPage(page);	
		List<OrderInfo> list = orderInfoService.listByCondition(orderInfo);
		AjaxUtils.sendAjaxForPage(request, response, page, list);
	}
	
	/**
	 * 进入修改页面方法
	 * @param key String：实体id
	 * @return ModelAndView: 查询实体
	 */	
	@RequestMapping("/toEditOrderInfo")
	public ModelAndView toEdit(String key){
		OrderInfo entity = orderInfoService.getByPrimaryKey(key);
		Map<String,String> orderInfo = FormatEntity.getObjectValue(entity);
		
		return new ModelAndView("WEB-INF/jsp/account/orderInfo/editOrderInfo","orderInfo",orderInfo );
	}
	
	/**
	 * 修改方法
	 * @param orderInfo OrderInfo：实体对象
	 * @param response HttpServletResponse
	 * @return: ajax输入json字符串
	 */	
	@RequestMapping("/updateOrderInfo")
	public void update(OrderInfo orderInfo,HttpServletRequest request,HttpServletResponse response){
		AjaxUtils.sendAjaxForObjectStr(
				response,orderInfoService.update(orderInfo));	
	}
	
	@RequestMapping("/sumOrder")
	public void sumOrder(OrderInfo orderInfo,HttpServletRequest request,
			HttpServletResponse response, Page page){	
		orderInfo.setPage(page);
		List<OrderInfo> list = orderInfoService.sumOrder(orderInfo);
		AjaxUtils.sendAjaxForPage(request, response, page, list);
	}
	
	@RequestMapping("/subgridlist")
	public void subgridlist(String id,HttpServletRequest request,
			HttpServletResponse response, Page page){	
		OrderInfo entity = orderInfoService.getByPrimaryKey(id);
		entity.setPage(page);
		List<OrderInfo> list = orderInfoService.subgridlist(entity);
		AjaxUtils.sendAjaxForPage(request, response, page, list);
	}
}
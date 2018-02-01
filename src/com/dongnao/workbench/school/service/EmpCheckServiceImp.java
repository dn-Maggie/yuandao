package com.dongnao.workbench.school.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dongnao.workbench.basic.model.Duty;
import com.dongnao.workbench.common.bean.ResultMessage;
import com.dongnao.workbench.common.util.AjaxUtils;
import com.dongnao.workbench.school.dao.EmpCheckMapper;
import com.dongnao.workbench.school.dao.EmployeeMapper;
import com.dongnao.workbench.school.model.EmpCheck;

@Service("empCheckService")
public class EmpCheckServiceImp implements EmpCheckService {

	@Resource
	private EmpCheckMapper empCheckMapper;
	
	@Override
	public List<EmpCheck> listByCondition(EmpCheck empCheck) {
		return empCheckMapper.listByCondition(empCheck);
	}

	@Override
	public EmpCheck getByPrimaryKey(String key) {
		return empCheckMapper.getByPrimaryKey(key);
	}

	@Override
	public EmpCheck showCheckFormKey(String key) {
		return empCheckMapper.showCheckFormKey(key);
	}
	@Override
	public void add(List<EmpCheck> empc) {
		empCheckMapper.add(empc);
	}

	@Override
	public ResultMessage update(EmpCheck empc) {
		try {
			empCheckMapper.update(empc);
		} catch (Exception e) {
			return AjaxUtils.getResultMessage(0,e.getMessage());
		}
		return AjaxUtils.getSuccessMessage();
	}

	
	@Override
	public int empConfirm(EmpCheck empc) {
		return empCheckMapper.empConfirm(empc);
	}

}

package com.dongnao.workbench.basic.service;
import javax.annotation.Resource;
import java.util.List;
import com.dongnao.workbench.basic.dao.DutyLevelMapper;
import com.dongnao.workbench.basic.model.DutyLevel;
import com.dongnao.workbench.basic.service.DutyLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dongnao.workbench.common.bean.ResultMessage;
import com.dongnao.workbench.common.util.AjaxUtils;
 
/**
 * 描述：岗位级别表模块service接口实现类，实现service接口方法
 *
 * @author maggie
 * @version 1.0 2016-12-20
 */
@Service("dutyLevelService")
public class DutyLevelServiceImpl implements DutyLevelService{
        @Resource
	private DutyLevelMapper dutyLevelMapper;
	
 
	/**
	 * 新增岗位级别表方法
	 * @param dutyLevel:实体类
	 */	
	public ResultMessage add(DutyLevel dutyLevel){
		dutyLevelMapper.add(dutyLevel);
		return AjaxUtils.getSuccessMessage();
	}
	
	/**
	 * 根据主键查找岗位级别表实体方法
	 * @param key String 实体主键
	 * @return DutyLevel 实体对象
	 */
	public DutyLevel getByPrimaryKey(String key){
		return dutyLevelMapper.getByPrimaryKey(key);
	}
	
	/**
	 * 删除岗位级别表方法
	 * @param key String 多个由“，”分割开的id字符串
	 */
	public void deleteByKey(String key){
		dutyLevelMapper.deleteByKey(key);
	}
	
	/**
	 * 根据条件查找岗位级别表列表方法
	 * @param dutyLevel DutyLevel 实体对象（查询条件）
	 * @return List<DutyLevel> 实体对象的list
	 */
	public List<DutyLevel> listByCondition(DutyLevel dutyLevel){
		return dutyLevelMapper.listByCondition(dutyLevel);
	}
	
	/**
	 * 修改岗位级别表方法
	 * @param dutyLevel DutyLevel 实体对象
	 */	
	public ResultMessage update(DutyLevel dutyLevel){
		dutyLevelMapper.update(dutyLevel);
		return AjaxUtils.getSuccessMessage();
	}
}
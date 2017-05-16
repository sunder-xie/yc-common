package com.ai.yc.common.service.atom.sysconfig.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.yc.common.dao.mapper.bo.SysConfig;
import com.ai.yc.common.dao.mapper.bo.SysConfigCriteria;
import com.ai.yc.common.dao.mapper.factory.MapperFactory;
import com.ai.yc.common.dao.mapper.interfaces.SysConfigMapper;
import com.ai.yc.common.service.atom.sysconfig.ISysConfigAtomSV;

/**
 * @author hougang@asiainfo.com
 * @date 2016年11月1日 下午5:19:10 
 * @version V1.0
 */
@Service
public class SysConfigAtomSVimpl implements ISysConfigAtomSV{

	@Override
	public SysConfig selectOne() {
		SysConfigCriteria example = new SysConfigCriteria();
		List<SysConfig> confs = MapperFactory.getSysConfigMapper().selectByExample(example);
		if(CollectionUtil.isEmpty(confs)){
			return null;
		}
		return confs.get(0);
	}

	@Override
	public SysConfig changeOne() {
		SysConfig sysOri = selectOne();
		//000001
//		SysConfig sysConfig = new SysConfig();
		sysOri.setCustomNum((Integer.valueOf(sysOri.getCustomNum())+1) + "");
		sysOri.setInterpreterNum((Integer.valueOf(sysOri.getInterpreterNum())+1) + "");
		sysOri.setOrderNum((Integer.valueOf(sysOri.getOrderNum())+1) + "");
		MapperFactory.getSysConfigMapper().updateByPrimaryKey(sysOri);
		return sysOri;
	}

	@Override
	public Integer saveSysConfig(SysConfig sysConfig) {
		SysConfigCriteria sysConfigCriteria = new SysConfigCriteria();
		SysConfigCriteria.Criteria criteria = sysConfigCriteria.createCriteria();
		if(sysConfig.getConfigId() != null){
			criteria.andConfigIdEqualTo(sysConfig.getConfigId());
			SysConfigMapper mapper = MapperFactory.getSysConfigMapper();
			int updateByPrimaryKey = mapper.updateByExampleSelective(sysConfig, sysConfigCriteria);
			return updateByPrimaryKey;
		}else{
			SysConfigMapper mapper = MapperFactory.getSysConfigMapper();
			sysConfig.setConfigId("1");
			int insertSelective = mapper.insertSelective(sysConfig);
			return insertSelective;
		}
	}
	
}

package com.ai.yc.common.service.business.syssensitive.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.sdk.components.sequence.util.SeqUtil;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.yc.common.api.syssensitive.param.SaveSysSensitive;
import com.ai.yc.common.api.syssensitive.param.SensitivePageQueryRequest;
import com.ai.yc.common.api.syssensitive.param.SensitivePageVo;
import com.ai.yc.common.constants.Constants;
import com.ai.yc.common.dao.mapper.bo.SysSensitive;
import com.ai.yc.common.service.atom.syssensitive.ISysSensitiveAtomSV;
import com.ai.yc.common.service.business.syssensitive.IQuerySysSensitiveBusiSV;

/**
 * @author shancc
 * @date 2017年5月16日 
 * @version V1.0.1
 */
@Service
public class QuerySysSensitiveBusiSVImpl implements IQuerySysSensitiveBusiSV {
	private static final Logger logger = Logger.getLogger(QuerySysSensitiveBusiSVImpl.class);
	@Autowired 
	private transient ISysSensitiveAtomSV  iSysSensitiveAtomSV;


	@Override
	public PageInfo<SensitivePageVo> querySensitivePage(SensitivePageQueryRequest param) {
		PageInfo<SensitivePageVo> questionsPageInfo = iSysSensitiveAtomSV.querySensitivePage(param);
		return questionsPageInfo;
	}


	@Override
	public Integer saveSysSensitive(SaveSysSensitive req) {
		SysSensitive sysSensitive = new SysSensitive();
		BeanUtils.copyProperties(sysSensitive,req);
		Long id = SeqUtil.getNewId(Constants.SEQ.SENSITIVE_ID_SEQ);
		sysSensitive.setId(id.toString());
		logger.info("Busis添加敏感词》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》");
		return iSysSensitiveAtomSV.saveSysItemBank(sysSensitive);
	}


	@Override
	public Integer deleteSysSensitive(String id) {
			return iSysSensitiveAtomSV.deleteSysSensitive(id);
	}


	@Override
	public Integer updateSysSensitive(SaveSysSensitive req) {
		SysSensitive sysSensitive = new SysSensitive();
		BeanUtils.copyProperties(sysSensitive,req);
		return iSysSensitiveAtomSV.updateSysSensitive(sysSensitive);	
	}

}

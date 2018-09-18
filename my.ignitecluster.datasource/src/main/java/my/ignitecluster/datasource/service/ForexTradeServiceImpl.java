package my.ignitecluster.datasource.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import my.ignitecluster.api.service.ForexTradeService;
import my.ignitecluster.common.bean.ForexTrade;
import my.ignitecluster.datasource.mapper.ForexTradeMapper;

@Service
public class ForexTradeServiceImpl implements ForexTradeService {
	
	@Autowired
	ForexTradeMapper forexTradeMapper;

	@Override
	public List<ForexTrade> QueryForexTradeSection(int start, int offset) {
		// TODO Auto-generated method stub
		return forexTradeMapper.find(start, offset);
	}
	
}

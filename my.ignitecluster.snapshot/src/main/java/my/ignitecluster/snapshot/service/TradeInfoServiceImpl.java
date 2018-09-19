package my.ignitecluster.snapshot.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.cache.Cache.Entry;

import org.apache.ignite.IgniteCache;
import org.apache.ignite.cache.query.QueryCursor;
import org.apache.ignite.cache.query.SqlFieldsQuery;
import org.apache.ignite.cache.query.SqlQuery;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import my.ignitecluster.api.service.TradeInfoService;
import my.ignitecluster.common.constant.IgniteConstant;
import my.ignitecluster.common.model.TradeInfo;
import my.ignitecluster.common.model.TradeInfoKey;
import my.ignitecluster.snapshot.utils.IgniteUtils;

@Service(value="TradeInfoService")
@Slf4j
public class TradeInfoServiceImpl implements TradeInfoService {
	
	@Resource
	private IgniteUtils igniteUtils;

	@Override
	public void putOrCreateTradeInfo(TradeInfo tradeInfo) {
		// TODO Auto-generated method stub
		String login = tradeInfo.getLogin();
		String symbol = tradeInfo.getSymbol();
		IgniteCache<TradeInfoKey, TradeInfo> tradeInfoCache = 
				igniteUtils.getIgniteInstance().cache(IgniteConstant.TRADEINFO_CACHE);
		SqlQuery<TradeInfoKey, TradeInfo> sql = new SqlQuery<>(TradeInfo.class, "login=? and symbol=?");
		try(QueryCursor<Entry<TradeInfoKey, TradeInfo>> cursor = tradeInfoCache.query(sql.setArgs(login, symbol))){
			List<Entry<TradeInfoKey, TradeInfo>> tradeInfoList = cursor.getAll();
			if (tradeInfoList.size() > 1) {
				log.error("login: " + login + ", symbol: " + symbol + ", size error.");
			}
			if (tradeInfoList.size() == 0) {
				tradeInfoCache.put(tradeInfo.getId(), tradeInfo);
				log.info("creat login: " + tradeInfo.getLogin());
			}else {
				TradeInfo dbTradeInfo = tradeInfoList.get(0).getValue();
				dbTradeInfo.setProfit(tradeInfo.getProfit() + dbTradeInfo.getProfit());
				dbTradeInfo.setVolume(tradeInfo.getVolume() + dbTradeInfo.getVolume());
				tradeInfoCache.put(dbTradeInfo.getId(), dbTradeInfo);
				log.info("update login: " + tradeInfo.getLogin());
			}
		}
	}

	@Override
	public List<String> getLogins() {
		// TODO Auto-generated method stub
		List<String> loginList = new ArrayList<String>();
		IgniteCache<TradeInfoKey, TradeInfo> tradeInfoCache = 
				igniteUtils.getIgniteInstance().cache(IgniteConstant.TRADEINFO_CACHE);
		SqlFieldsQuery sql = new SqlFieldsQuery("select distinct login from TradeInfo");
		try(QueryCursor<List<?>> cursor = tradeInfoCache.query(sql)){
			cursor.forEach(n->loginList.add((String) n.get(0)));
		}
		return loginList;
	}

	@Override
	public void putTradeInfo(TradeInfo tradeInfo) {
		// TODO Auto-generated method stub

		IgniteCache<TradeInfoKey, TradeInfo> tradeInfoCache = 
				igniteUtils.getIgniteInstance().cache(IgniteConstant.TRADEINFO_CACHE);
		tradeInfoCache.put(tradeInfo.getId(), tradeInfo);
	}

	@Override
	public List<TradeInfo> getTradeInfoByLogin(String login) {
		// TODO Auto-generated method stub
		List<TradeInfo> tradeInfoList = new ArrayList<>();
		IgniteCache<TradeInfoKey, TradeInfo> tradeInfoCache = 
				igniteUtils.getIgniteInstance().cache(IgniteConstant.TRADEINFO_CACHE);
		SqlQuery<TradeInfoKey, TradeInfo> sql = new SqlQuery<>(TradeInfo.class, "login=?");
		try(QueryCursor<Entry<TradeInfoKey, TradeInfo>> cursor = tradeInfoCache.query(sql.setArgs(login))){
			cursor.forEach(n->tradeInfoList.add(n.getValue()));
		}
		return tradeInfoList;
	}


}

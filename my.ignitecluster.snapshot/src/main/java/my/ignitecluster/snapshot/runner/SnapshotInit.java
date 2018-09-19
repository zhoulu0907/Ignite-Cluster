package my.ignitecluster.snapshot.runner;

import javax.annotation.Resource;

import org.apache.ignite.cache.CacheMode;
import org.apache.ignite.configuration.CacheConfiguration;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import my.ignitecluster.common.constant.IgniteConstant;
import my.ignitecluster.common.model.TradeInfo;
import my.ignitecluster.common.model.TradeInfoKey;
import my.ignitecluster.common.model.UserTradeInfo;
import my.ignitecluster.common.model.UserTradeInfoKey;
import my.ignitecluster.snapshot.utils.IgniteUtils;

@Component
@Order(0)
public class SnapshotInit implements CommandLineRunner {

	@Resource
	private IgniteUtils igniteUtils;
	@Override
	public void run(String... arg0) throws Exception {
		// TODO Auto-generated method stub
		//INIT TRADE INFO CACHE
		CacheConfiguration<TradeInfoKey, TradeInfo> tradeInfoCfg = new CacheConfiguration<TradeInfoKey, TradeInfo>();
		tradeInfoCfg.setName(IgniteConstant.TRADEINFO_CACHE);
		tradeInfoCfg.setIndexedTypes(TradeInfoKey.class, TradeInfo.class);
		tradeInfoCfg.setCacheMode(CacheMode.PARTITIONED);
		tradeInfoCfg.setStatisticsEnabled(true);
		igniteUtils.getIgniteInstance().getOrCreateCache(tradeInfoCfg);
		//INIT USER TRADE INFO CACHE
		CacheConfiguration<UserTradeInfoKey, UserTradeInfo> userTradeInfoCfg =
				new CacheConfiguration<UserTradeInfoKey, UserTradeInfo>();
		userTradeInfoCfg.setName(IgniteConstant.USERTRADEINFO_CACHE);
		userTradeInfoCfg.setIndexedTypes(UserTradeInfoKey.class, UserTradeInfo.class);
		userTradeInfoCfg.setCacheMode(CacheMode.PARTITIONED);
		userTradeInfoCfg.setStatisticsEnabled(true);
		igniteUtils.getIgniteInstance().getOrCreateCache(userTradeInfoCfg);
		
	}

}

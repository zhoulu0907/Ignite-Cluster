package my.ignitecluster.snapshot.runner;

import javax.annotation.Resource;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.IgniteQueue;
import org.apache.ignite.configuration.CollectionConfiguration;
import org.apache.ignite.lang.IgniteRunnable;
import org.apache.ignite.resources.SpringResource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import my.ignitecluster.api.service.TradeInfoService;
import my.ignitecluster.common.bean.ForexTrade;
import my.ignitecluster.common.constant.IgniteConstant;
import my.ignitecluster.common.model.TradeInfo;
import my.ignitecluster.common.model.TradeInfoKey;
import my.ignitecluster.common.ultis.TimeUtils;
import my.ignitecluster.snapshot.utils.IgniteUtils;

@Component
@Order(1)
@Slf4j
public class TradeInfoRecv implements CommandLineRunner {

	@Resource
	private IgniteUtils igniteUtils;
	@Override
	public void run(String... arg0) throws Exception {
		// TODO Auto-generated method stub
		new Thread(new Runnable() {
			private Ignite ignite = igniteUtils.getIgniteInstance();
			private IgniteQueue<ForexTrade> forexTradeQueue = ignite.queue("ForexTrade", 0, new CollectionConfiguration());
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				//接收queue中数据，进行处理
				while(true) {
					ForexTrade forexTrade = forexTradeQueue.take();
					log.info("Get forextrade: " + forexTrade.getDeal());
					TradeInfo tradeInfo = assembleTradeInfo(forexTrade);
					ignite.compute().affinityRunAsync(IgniteConstant.TRADEINFO_CACHE, tradeInfo.getId(), new IgniteRunnable() {

						/**
						 * 
						 */
						private static final long serialVersionUID = 1L;
						
						@SpringResource(resourceName="TradeInfoService")
						private transient TradeInfoService tradeInfoService;

						@Override
						public void run() {
							// TODO Auto-generated method stub
							tradeInfoService.putTradeInfo(tradeInfo);
						}
						
					});
				}

			}

			private TradeInfo assembleTradeInfo(ForexTrade forexTrade) {
				// TODO Auto-generated method stub
				TradeInfoKey tradeInfoKey = new TradeInfoKey();
				tradeInfoKey.setDealId(forexTrade.getDeal());
				tradeInfoKey.setLogin(forexTrade.getLogin());
				TradeInfo tradeInfo = new TradeInfo();
				tradeInfo.setId(tradeInfoKey);
				tradeInfo.setLogin(forexTrade.getLogin());
				tradeInfo.setProfit(forexTrade.getProfit());
				tradeInfo.setSymbol(forexTrade.getSymbol());
				String type = forexTrade.getType();
				int direct = type.equalsIgnoreCase("Buy") ? 1 : -1;
				tradeInfo.setVolume(forexTrade.getVolume() * direct);
				tradeInfo.setHoldTime(TimeUtils.getInstance().getSpan(forexTrade.getOpentime(), forexTrade.getClosetime()));
				return tradeInfo;
			}
		}).start();
	}

}

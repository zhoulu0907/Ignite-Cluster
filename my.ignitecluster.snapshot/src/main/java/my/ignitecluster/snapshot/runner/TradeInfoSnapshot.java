package my.ignitecluster.snapshot.runner;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import my.ignitecluster.api.service.TradeInfoService;
import my.ignitecluster.api.service.UserTradeInfoService;
import my.ignitecluster.common.model.TradeInfo;
import my.ignitecluster.common.model.UserTradeInfo;
import my.ignitecluster.common.model.UserTradeInfoKey;

@Component
@Order(2)
public class TradeInfoSnapshot implements CommandLineRunner {
	@Resource(name="TradeInfoService")
	private TradeInfoService tradeInfoService;
	
	@Resource(name="UserTradeInfoService")
	private UserTradeInfoService userTradeInfoSerive;
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(true) {
					List<String> loginList = tradeInfoService.getLogins();
					for (String login : loginList) {
						List<TradeInfo> tradeInfoList = tradeInfoService.getTradeInfoByLogin(login);
						double totalProfit = 0;
						double totalVolume = 0;
						double meanHoldTime = 0;
						for (TradeInfo tradeInfo : tradeInfoList) {
							totalProfit += tradeInfo.getProfit();
							totalVolume += tradeInfo.getVolume();
							meanHoldTime += tradeInfo.getHoldTime();
						}
						meanHoldTime = meanHoldTime / tradeInfoList.size();
						UserTradeInfoKey userTradeInfoKey = new UserTradeInfoKey();
						userTradeInfoKey.setLogin(login);
						UserTradeInfo userTradeInfo = new UserTradeInfo();
						userTradeInfo.setId(userTradeInfoKey);
						userTradeInfo.setLogin(login);
						userTradeInfo.setTotalProfit(totalProfit);
						userTradeInfo.setTotalVolume(totalVolume);
						userTradeInfo.setMeanHoldTime(meanHoldTime);
						userTradeInfoSerive.put(userTradeInfo);
					}
					try {
						Thread.sleep(5 * 1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
			
		}).start();
	}

}

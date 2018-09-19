package my.ignitecluster.api.service;

import java.util.List;

import my.ignitecluster.common.model.TradeInfo;

public interface TradeInfoService {
	public void putOrCreateTradeInfo(TradeInfo tradeInfo);

	public List<String> getLogins();

	public void putTradeInfo(TradeInfo tradeInfo);

	public List<TradeInfo> getTradeInfoByLogin(String login);
}

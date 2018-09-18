package my.ignitecluster.api.service;

import java.util.List;

import my.ignitecluster.common.bean.ForexTrade;

public interface ForexTradeService {
	List<ForexTrade> QueryForexTradeSection(int start, int offset);
}

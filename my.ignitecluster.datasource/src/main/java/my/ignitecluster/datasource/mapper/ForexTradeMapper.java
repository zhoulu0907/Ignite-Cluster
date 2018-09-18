package my.ignitecluster.datasource.mapper;

import java.util.List;

import my.ignitecluster.common.bean.ForexTrade;

public interface ForexTradeMapper {
	List<ForexTrade> find(int start, int offset);
}

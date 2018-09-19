package my.ignitecluster.common.model;

import org.apache.ignite.cache.query.annotations.QuerySqlField;

import lombok.Data;

@Data
public class TradeInfo {
	private TradeInfoKey id;
	private String dealId;
	@QuerySqlField
	private String login;
	@QuerySqlField
	private String symbol;
	
	private double volume;
	private double profit;
	private long holdTime;
}

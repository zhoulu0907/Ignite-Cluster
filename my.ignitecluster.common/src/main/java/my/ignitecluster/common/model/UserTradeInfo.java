package my.ignitecluster.common.model;

import org.apache.ignite.cache.query.annotations.QuerySqlField;

import lombok.Data;

@Data
public class UserTradeInfo {
	private UserTradeInfoKey id;
	@QuerySqlField
	private String login;
	private double totalVolume;
	private double totalProfit;
	private double meanHoldTime;
}

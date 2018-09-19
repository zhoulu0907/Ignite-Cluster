package my.ignitecluster.common.model;

import org.apache.ignite.cache.affinity.AffinityKeyMapped;

import lombok.Data;

@Data
public class TradeInfoKey {
	private String dealId;
	@AffinityKeyMapped
	private String login;
}

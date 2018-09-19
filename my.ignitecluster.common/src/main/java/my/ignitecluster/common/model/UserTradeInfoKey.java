package my.ignitecluster.common.model;

import org.apache.ignite.cache.affinity.AffinityKeyMapped;

import lombok.Data;

@Data
public class UserTradeInfoKey {
	@AffinityKeyMapped
	private String login;

}

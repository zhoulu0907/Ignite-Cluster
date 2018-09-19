package my.ignitecluster.snapshot.service;

import javax.annotation.Resource;

import org.apache.ignite.IgniteCache;
import org.springframework.stereotype.Service;

import my.ignitecluster.api.service.UserTradeInfoService;
import my.ignitecluster.common.constant.IgniteConstant;
import my.ignitecluster.common.model.UserTradeInfo;
import my.ignitecluster.common.model.UserTradeInfoKey;
import my.ignitecluster.snapshot.utils.IgniteUtils;

@Service(value="UserTradeInfoService")
public class UserTradeInfoServiceImpl implements UserTradeInfoService {

	@Resource
	private IgniteUtils igniteUtils;
	@Override
	public void put(UserTradeInfo userTradeInfo) {
		// TODO Auto-generated method stub
		IgniteCache<UserTradeInfoKey, UserTradeInfo> userTradeInfoCache =
				igniteUtils.getIgniteInstance().cache(IgniteConstant.USERTRADEINFO_CACHE);
		userTradeInfoCache.put(userTradeInfo.getId(), userTradeInfo);
	}

}

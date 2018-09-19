package my.ignitecluster.datasource.runner;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ignite.IgniteQueue;
import org.apache.ignite.configuration.CollectionConfiguration;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import my.ignitecluster.api.service.ForexTradeService;
import my.ignitecluster.common.bean.ForexTrade;
import my.ignitecluster.datasource.utils.IgniteUtils;


@Component
@Order(1)
public class DataSourceManager implements CommandLineRunner {

	@Resource
	private IgniteUtils igniteUtils;
	@Resource
	private ForexTradeService forexTradeService;
	@Override
	public void run(String... arg0) throws Exception {
		// TODO Auto-generated method stub
		List<ForexTrade> forexTradeList = forexTradeService.QueryForexTradeSection(0, 1000);
		IgniteQueue<ForexTrade> forexTradeQueue = igniteUtils.getIgniteInstance().queue("ForexTrade", 0, new CollectionConfiguration());
		forexTradeList.forEach(n->forexTradeQueue.add(n));
	}

}

package my.ignitecluster.datasource.runner;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import my.ignitecluster.datasource.utils.IgniteUtils;

@Component
@Order(0)
public class IgniteClusterInit implements CommandLineRunner{
	@Resource
	private IgniteUtils igniteUtils;

	@Override
	public void run(String... arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
}

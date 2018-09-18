package my.ignitecluster.datasource.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import my.ignitecluster.api.service.ForexTradeService;
import my.ignitecluster.common.bean.ForexTrade;

@RestController
public class ForexTradeController {

	@Resource
	private ForexTradeService forexTradeService;
	
	//http://localhost:8080/QueryForexTrade?start=0&offset=1000
	@RequestMapping("QueryForexTrade")
	public List<ForexTrade> ForexTradeData(@RequestParam(value="start") String start,
			@RequestParam(value="offset") String offset) {
		
		return forexTradeService.QueryForexTradeSection(Integer.parseInt(start), Integer.parseInt(offset));
	}
}

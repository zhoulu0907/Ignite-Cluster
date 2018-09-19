package my.ignitecluster.common.ultis;

import java.sql.Timestamp;

public class TimeUtils {
	private static TimeUtils instance = new TimeUtils();
	
	private TimeUtils() {
		
	}

	public static TimeUtils getInstance() {
		return instance;
	}

	public long getSpan(Timestamp opentime, Timestamp closetime) {
		// TODO Auto-generated method stub
		long timeSpan = closetime.getTime() - opentime.getTime();
		return timeSpan / 1000;
	}
}

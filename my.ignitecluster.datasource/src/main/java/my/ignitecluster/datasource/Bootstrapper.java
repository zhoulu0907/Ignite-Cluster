package my.ignitecluster.datasource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@MapperScan("my.ignitecluster.datasource.mapper")
@ImportResource("applicationContext.xml")
public class Bootstrapper {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(Bootstrapper.class, args);
	}

}

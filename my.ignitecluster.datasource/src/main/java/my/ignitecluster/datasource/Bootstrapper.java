package my.ignitecluster.datasource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("my.ignitecluster.datasource.mapper")
public class Bootstrapper {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(Bootstrapper.class, args);
	}

}

package tacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


// 제일 먼저 시작되는게 부트스트랩 클래스, 이것도!

//@SpringBootApplication = @SpringBootConfiguration + @EnableAutoConfiguration + @ComponentScan
// 현재 클래스를 구성(configuration)클래스로 지정, @Configuration의 특화된 애너테이션
// @EnableAutoConfiguration - 스트링부트 자동 -구성 활성화 (컴포넌트 자동 구성하도록함)
// @ComponentScan - 컴포넌트들 찾아서 스프링 애플리케이션 컨텍스트에 컴포넌트로 등록
@SpringBootApplication
public class TacoCloudApplication {

	public static void main(String[] args) {
		SpringApplication.run(TacoCloudApplication.class, args);
	}

}

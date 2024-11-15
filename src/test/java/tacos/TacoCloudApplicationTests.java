package tacos;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

//main() 메서드의 SpringApplication.run() 호출에 부합되는 테스트 클래스를 나타냄
//스프링부트 기능으로 테스트를 시작하라는 것을 JUnit에 알려줌 -> 스프링 애클리케이션 컨텍스트 로드 하는 작업 수행
@SpringBootTest
class TacoCloudApplicationTests {

	@Test
	void contextLoads() {
	}

}

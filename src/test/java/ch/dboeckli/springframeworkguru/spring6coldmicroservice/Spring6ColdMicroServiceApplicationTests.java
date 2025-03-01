package ch.dboeckli.springframeworkguru.spring6coldmicroservice;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest(useMainMethod = SpringBootTest.UseMainMethod.ALWAYS)
@DirtiesContext
@Slf4j
class Spring6ColdMicroServiceApplicationTests {

    @Test
    void contextLoads() {
        log.info("Testing Spring 6 Application...");
    }

}

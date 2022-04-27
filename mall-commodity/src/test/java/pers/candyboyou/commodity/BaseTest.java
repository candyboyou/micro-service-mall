package pers.candyboyou.commodity;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTestContextBootstrapper;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.BootstrapWith;
import org.springframework.test.context.TestContextBootstrapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import pers.candyboyou.commodity.CommodityApplication;

@BootstrapWith(value= SpringBootTestContextBootstrapper.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CommodityApplication.class)
@ActiveProfiles("dev")
public class BaseTest {

    @Autowired
    private Environment environment;

    static {
        System.setProperty("user.env", "dev");
    }
}

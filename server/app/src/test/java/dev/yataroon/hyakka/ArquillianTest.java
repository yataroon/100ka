package dev.yataroon.hyakka;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.io.File;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit5.ArquillianExtension;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.FileAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@Disabled
@ExtendWith(ArquillianExtension.class)
public class ArquillianTest {

    @ApplicationScoped
    public static class TestBean {

        public String getMessage() {
            return "Hello from CDI!";
        }
    }

    @Inject
    private TestBean testBean;

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClass(TestBean.class)
                .addAsWebInfResource(new FileAsset(new File("src/main/webapp/WEB-INF/beans.xml")), "beans.xml");
    }

    @Test
    public void testCDIInjection() {
        assertNotNull(testBean);
        assertNotNull(testBean.getMessage());
        System.out.println("CDI injection works: " + testBean.getMessage());
    }
}

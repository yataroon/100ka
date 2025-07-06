package dev.yataroon.hyakka;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit5.ArquillianExtension;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.api.asset.FileAsset;
import java.io.File;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import jakarta.inject.Inject;
import jakarta.enterprise.context.ApplicationScoped;

import static org.junit.jupiter.api.Assertions.assertNotNull;

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
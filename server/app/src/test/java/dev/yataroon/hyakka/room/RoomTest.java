/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.yataroon.hyakka.room;

import dev.yataroon.hyakka.room.dto.request.StartRoomSessionRequest;
import dev.yataroon.hyakka.room.dto.response.StartRoomSessionResponse;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.io.File;
import java.net.URL;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit5.ArquillianExtension;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(ArquillianExtension.class)
public class RoomTest {

    @ArquillianResource
    private URL baseURL;

    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        File warFile = new File("target/hyakka.war");

        // デバッグ情報を出力
        System.out.println("Current working directory: " + System.getProperty("user.dir"));
        System.out.println("WAR file path: " + warFile.getAbsolutePath());
        System.out.println("WAR file exists: " + warFile.exists());
        System.out.println("WAR file size: " + warFile.length() + " bytes");

        if (!warFile.exists()) {
            throw new RuntimeException("WAR file not found at: " + warFile.getAbsolutePath());
        }
        return ShrinkWrap.createFromZipFile(WebArchive.class,
                new File("target/hyakka.war"));
    }

    @Test
    @RunAsClient
    public void testRoomList() {
        Client client = ClientBuilder.newClient();

        Response response = client
                .target(baseURL + "api/room/list")
                .request(MediaType.APPLICATION_JSON)
                .get();

        assertEquals(200, response.getStatus());

    }

    @Test
    @RunAsClient
    public void testStartRoomSession() {
        Client client = ClientBuilder.newClient();

        StartRoomSessionRequest request = new StartRoomSessionRequest();
        request.setUserName("arquillian");

        Response response = client.target(baseURL + "api/room/start")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.json(request));

        var responseEntity = response.readEntity(StartRoomSessionResponse.class);
        assertEquals("arquillian", responseEntity.getUserName());

    }

}

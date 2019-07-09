package com.hwork.wiremock;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static com.github.tomakehurst.wiremock.client.WireMock.*;

/**
 * Created by yangshengju on 2019-7-9.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class WireMockTest {
    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8089); // No-args constructor defaults to port 8080

    @Test
    public void exampleTest() {
        stubFor(get(urlEqualTo("/user/1"))
        .withHeader("Accept",equalTo("text/xml"))
        .willReturn(aResponse().withStatus(200)
        .withHeader("Content-Type","text/xml")
        .withBody("<response>Some content</response>")));
    }
}

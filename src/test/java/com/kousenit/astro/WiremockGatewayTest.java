package com.kousenit.astro;

import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@WireMockTest
public class WiremockGatewayTest {

    @BeforeEach
    void setUp() {
        stubFor(get("/astros.json").willReturn(
                okJson("""
                        {
                            "people": [
                                {
                                    "craft": "Discovery One",
                                    "name": "David Bowman"
                                },
                                {
                                    "craft": "Discovery One",
                                    "name": "Frank Poole"
                                },
                                {
                                    "craft": "Discovery One",
                                    "name": "HAL 9000"
                                }
                            ],
                            "message": "success",
                            "number": 3
                        }
                        """))
        );
    }

    @Test
    void testWithWiremock(WireMockRuntimeInfo info) {
        AstroGateway gateway = new AstroGateway(info.getHttpBaseUrl() + "/astros.json");
        Result<AstroResponse> result = gateway.getResponse();
        switch (result) {
            case Success<AstroResponse> astroSuccess -> {
                AstroResponse data = astroSuccess.data();
                System.out.println(result);
                assertAll(
                        () -> assertTrue(data.number() >= 0),
                        () -> assertEquals(data.people().size(), data.number()),
                        () -> assertEquals("success", data.message()),
                        () -> assertAll(
                                () -> assertThat(data.people())
                                        .extracting("craft")
                                        .contains("Discovery One"),
                                () -> assertThat(data.people())
                                        .extracting("name")
                                        .containsExactly("David Bowman", "Frank Poole", "HAL 9000"))
                );
            }
            case Failure<AstroResponse> astroFailure -> assertNotNull(astroFailure.exception());

        }

        verify(getRequestedFor(urlPathEqualTo("/astros.json"))
                .withHeader("Accept", equalTo("application/json")));
    }
}

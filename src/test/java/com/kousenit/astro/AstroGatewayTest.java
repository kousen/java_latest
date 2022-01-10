package com.kousenit.astro;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AstroGatewayTest {
    private final Gateway<AstroResponse> gateway = new AstroGateway();

    @Test
    void testDeserializeToRecords() {
        Result<AstroResponse> result = gateway.getResponse();
        System.out.println(result);
        switch (result) {
            case Success<AstroResponse> astroSuccess -> {
                AstroResponse data = astroSuccess.data();
                assertAll(
                        () -> assertTrue(data.number() >= 0),
                        () -> assertEquals(data.people().size(), data.number())
                );
            }
            case Failure<AstroResponse> astroFailure ->
                    assertNotNull(astroFailure.exception());
        }
    }
}
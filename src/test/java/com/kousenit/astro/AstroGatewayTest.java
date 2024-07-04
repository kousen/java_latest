package com.kousenit.astro;

import com.kousenit.http.AstroResponse;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class AstroGatewayTest {
    private final Gateway<AstroResponse> gateway = new AstroGateway();

    @Test
    void testResult() {
        Result<AstroResponse> result = gateway.getResult();
        System.out.println(result);

        switch (result) {
            case Success<AstroResponse> astroSuccess -> checkSuccess(astroSuccess);
            case Failure<AstroResponse> astroFailure -> checkFailure(astroFailure);
        }
    }

    private void checkSuccess(Success<AstroResponse> astroSuccess) {
        AstroResponse data = astroSuccess.data();
        assertAll(
                () -> assertThat(data.message()).isEqualTo("success"),
                () -> assertThat(data.number())
                        .isGreaterThanOrEqualTo(0)
                        .isEqualTo(data.people().size())
        );
    }

    private void checkFailure(Failure<AstroResponse> astroFailure) {
        assertThat(astroFailure.exception()).isNotNull();
    }
}
package com.kousenit.astro;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class AstroServiceTest {
    private final AstroResponse mockAstroResponse =
            new AstroResponse(7, "Success", List.of(
                    new Assignment("John Sheridan", "Babylon 5"),
                    new Assignment("Susan Ivanova", "Babylon 5"),
                    new Assignment("Beckett Mariner", "USS Cerritos"),
                    new Assignment("Brad Boimler", "USS Cerritos"),
                    new Assignment("Sam Rutherford", "USS Cerritos"),
                    new Assignment("D'Vana Tendi", "USS Cerritos"),
                    new Assignment("Ellen Ripley", "Nostromo")
            ));

    @Mock
    private Gateway<AstroResponse> gateway;

    @InjectMocks
    private AstroService service;

    // Unit test with injected mock Gateway
    @Test
    void testAstroData_InjectedMockGateway() {
        // Mock Gateway already injected into AstroService using annotations
        // Set the expectations on the mock
        given(gateway.getResponse())
                .willReturn(new Success<>(mockAstroResponse));

        // Call the method under test
        Map<String, Long> astroData = service.getAstroData();

        // Check the results from the method under test
        assertThat(astroData)
                .containsEntry("Babylon 5", 2L)
                .containsEntry("Nostromo", 1L)
                .containsEntry("USS Cerritos", 4L);
        astroData.forEach((craft, number) -> {
            System.out.println(number + " astronauts aboard " + craft);
            assertAll(
                    () -> assertThat(number).isGreaterThan(0),
                    () -> assertThat(craft).isNotBlank()
            );
        });

        // Verify the stubbed method was called
        then(gateway).should().getResponse();
    }

    // Check network failure
    @Test
    void testAstroData_failedGateway() {
        // given:
        willReturn(new Failure<>(
                new RuntimeException(new IOException("Network problems")
        ))).given(gateway).getResponse();

        // when:
        Exception exception = assertThrows(
                RuntimeException.class,
                () -> service.getAstroData());

        // then:
        Throwable cause = exception.getCause();
        assertAll(
                () -> assertEquals(IOException.class, cause.getClass()),
                () -> assertEquals("Network problems", cause.getMessage())
        );

        // verify:
        then(gateway).should().getResponse();
        then(gateway).shouldHaveNoMoreInteractions();
    }

    // Integration test -- no mocks
    @Test
    void testAstroData_RealGateway() {
        service = new AstroService(new AstroGateway());
        Map<String, Long> astroData = service.getAstroData();
        astroData.forEach((craft, number) -> {
            System.out.println(number + " astronauts aboard " + craft);
            assertAll(
                    () -> assertThat(number).isGreaterThan(0),
                    () -> assertThat(craft).isNotBlank()
            );
        });
    }

    // Own mock class -- MockGateway
    @Test
    void testAstroData_OwnMockGateway() {
        service = new AstroService(new MockGateway());
        Map<String, Long> astroData = service.getAstroData();
        astroData.forEach((craft, number) -> {
            System.out.println(number + " astronauts aboard " + craft);
            assertAll(
                    () -> assertThat(number).isGreaterThan(0),
                    () -> assertThat(craft).isNotBlank()
            );
        });
    }

    // Unit test with mock Gateway
    @SuppressWarnings("unchecked")
    @Test
    void testAstroData_MockGateway() {
        // 1. Create a mock Gateway
        Gateway<AstroResponse> mockGateway = mock(Gateway.class);

        // 2. Set up the mock Gateway to return a specific AstroResponse
        when(mockGateway.getResponse())
                .thenReturn(new Success<>(mockAstroResponse));

        // 3. Create an instance of AstroService using the mock Gateway
        AstroService service = new AstroService(mockGateway);

        // 4. Call the method under test
        Map<String, Long> astroData = service.getAstroData();
        assertThat(astroData)
                .containsEntry("Babylon 5", 2L)
                .containsEntry("USS Cerritos", 4L)
                .containsEntry("Nostromo", 1L)
                .hasSize(3);
        astroData.forEach((craft, number) -> {
            System.out.println(number + " astronauts aboard " + craft);
            assertAll(
                    () -> assertThat(number).isGreaterThan(0),
                    () -> assertThat(craft).isNotBlank()
            );
        });

        // 5. Verify that the mock Gateway was called
        verify(mockGateway).getResponse();
    }


}
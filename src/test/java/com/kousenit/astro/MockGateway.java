package com.kousenit.astro;

import com.kousenit.http.AstroResponse;

import java.util.List;

public class MockGateway implements Gateway<AstroResponse> {
    @Override
    public Result<AstroResponse> getResponse() {
        return new Success<>(new AstroResponse(7, "Success",
                List.of(new AstroResponse.Assignment("Kathryn Janeway", "USS Voyager"),
                        new AstroResponse.Assignment("Seven of Nine", "USS Voyager"),
                        new AstroResponse.Assignment("Will Robinson", "Jupiter 2"),
                        new AstroResponse.Assignment("Lennier", "Babylon 5"),
                        new AstroResponse.Assignment("James Holden", "Rocinante"),
                        new AstroResponse.Assignment("Naomi Negata", "Rocinante"),
                        new AstroResponse.Assignment("Ellen Ripley", "Nostromo"))));
    }
}

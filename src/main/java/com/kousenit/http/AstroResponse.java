package com.kousenit.http;

import java.util.List;

// {
//   "message": "success",
//   "number": NUMBER_OF_PEOPLE_IN_SPACE,
//   "people": [
//     {"name": NAME, "craft": SPACECRAFT_NAME},
//     ...
//   ]
// }
public record AstroResponse(String message, int number, List<Assignment> people) {
    public record Assignment(String name, String craft) { }

    // compact constructor
    public AstroResponse {
        if (!message.equalsIgnoreCase("success")) {
            throw new IllegalArgumentException("Houston, we have a problem");
        }
    }
}
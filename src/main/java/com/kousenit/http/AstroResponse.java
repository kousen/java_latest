package com.kousenit.http;

import java.util.List;

// """
// {
//   "message": "success",
//   "number": NUMBER_OF_PEOPLE_IN_SPACE,
//   "people": [
//     {"name": NAME, "craft": SPACECRAFT_NAME},
//     ...
//   ]
// }
// """
public record AstroResponse(int number,
                            String message,
                            List<Assignment> people) {
    public record Assignment(String name, String craft) { }
}

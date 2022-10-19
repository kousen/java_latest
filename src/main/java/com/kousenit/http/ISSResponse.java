package com.kousenit.http;

public record ISSResponse(String message,
                          Position iss_position,
                          Long timestamp) {}


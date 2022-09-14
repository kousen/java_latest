package com.kousenit.astro;

// A "Gateway" wraps network access
public interface Gateway<T> {
    Result<T> getResponse();
}

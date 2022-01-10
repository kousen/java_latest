package com.kousenit.astro;

public interface Gateway<T> {
    Result<T> getResponse();
}

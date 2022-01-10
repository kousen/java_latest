package com.kousenit.astro;

public sealed interface Result<T>
    permits Success, Failure { }

record Success<T>(T data) implements Result<T> { }

record Failure<T>(RuntimeException exception) implements Result<T> { }

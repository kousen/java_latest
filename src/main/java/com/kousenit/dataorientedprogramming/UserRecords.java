package com.kousenit.dataorientedprogramming;

// "Wrapper" class, just to see everything in one file
public class UserRecords {
    // Only two permitted types
    public sealed interface Person permits User, Admin {}

    // Each type implements the Person interface
    public record User(
            int id,
            String name,
            String email
    ) implements Person {}

    public record Admin(
            int id,
            String name,
            String email,
            String permissions
    ) implements Person {}
}

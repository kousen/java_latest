package com.kousenit.walker;

import java.util.List;
import java.util.stream.Collectors;

public class StackWalkerDemo {
    public static void main(String[] args) {
        List<StackWalker.StackFrame> frames = StackWalker.getInstance()
                                                       .walk(s -> s.limit(10)
                                                                   .collect(Collectors.toList()));
        frames.forEach(System.out::println);
    }
}

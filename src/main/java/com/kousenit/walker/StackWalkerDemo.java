package com.kousenit.walker;

import java.util.List;

public class StackWalkerDemo {
    public static void main(String[] args) {
        List<StackWalker.StackFrame> frames = StackWalker.getInstance()
                                                       .walk(s -> s.limit(10)
                                                                   .toList());
        frames.forEach(System.out::println);
    }
}

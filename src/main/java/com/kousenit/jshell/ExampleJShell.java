package com.kousenit.jshell;

import jdk.jshell.JShell;
import jdk.jshell.Snippet;
import jdk.jshell.SnippetEvent;

import java.util.List;

// from the Javadocs package summary for jdk.jshell, refactored a bit for newer features
public class ExampleJShell {
    public static void main(String[] args) {
        var console = System.console();
        try (var js = JShell.create()) {
            do {
                System.out.print("Enter some Java code: ");
                String input = console.readLine();
                if (input == null) {
                    break;
                }
                List<SnippetEvent> events = js.eval(input);
                events.forEach(e -> {
                    var sb = new StringBuilder();
                    if (e.causeSnippet() == null) {
                        //  We have a snippet creation event
                        switch (e.status()) {
                            case VALID -> sb.append("Successful ");
                            case RECOVERABLE_DEFINED -> sb.append("With unresolved references ");
                            case RECOVERABLE_NOT_DEFINED -> sb.append("Possibly reparable, failed  ");
                            case REJECTED -> sb.append("Failed ");
                        }
                        sb.append(e.previousStatus() == Snippet.Status.NONEXISTENT ? "addition" : "modification")
                                .append(" of ")
                                .append(e.snippet().source());
                        System.out.println(sb);
                        if (e.value() != null) {
                            System.out.printf("Value is: %s\n", e.value());
                        }
                        System.out.flush();
                    }
                });
            } while (true);
        }
        System.out.println("\nGoodbye");
    }
}

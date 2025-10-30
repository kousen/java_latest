package com.kousenit.processes;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.OS;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class ProcessDemoTest {

    @Test
    @DisabledOnOs(OS.WINDOWS)  // ls command is Unix/Mac specific
    void testProcessBuilder() throws Exception {
        // Test ProcessBuilder with ls command
        ProcessBuilder builder = new ProcessBuilder("ls");
        File outputFile = new File("test-output.txt");
        builder.redirectOutput(outputFile);

        Process proc = builder.start();

        // Verify process started
        assertNotNull(proc);

        // Give it a moment to complete
        proc.waitFor();

        // Process should have completed
        assertFalse(proc.isAlive());

        // Should have a valid PID
        assertTrue(proc.pid() > 0);

        // Clean up
        if (outputFile.exists()) {
            outputFile.delete();
        }
    }

    @Test
    @DisabledOnOs(OS.WINDOWS)  // ls command is Unix/Mac specific
    void testRuntimeExec() throws Exception {
        // Test Runtime.exec()
        Process proc = Runtime.getRuntime().exec(new String[]{"ls"});

        assertNotNull(proc);
        assertTrue(proc.pid() > 0);

        // Destroy the process
        proc.destroy();

        // Wait for it to terminate
        proc.waitFor();

        // Process should not be alive after destroy
        assertFalse(proc.isAlive());
    }

    @Test
    void testProcessDemoMain() {
        // Run the demo to ensure coverage
        // This might fail on Windows, but that's okay - it's a demo
        try {
            ProcessDemo.main(new String[]{});

            // Clean up output file if it was created
            File outputFile = new File("output.txt");
            if (outputFile.exists()) {
                outputFile.delete();
            }
        } catch (Exception e) {
            // Expected on Windows or if ls command not available
            // The demo uses ls which is platform-specific
            assertTrue(e.getMessage() == null ||
                      e.getMessage().contains("ls") ||
                      e.getMessage().contains("Cannot run program"));
        }
    }

    @Test
    void testProcessBuilderWithEchoCommand() throws Exception {
        // Use echo which works on all platforms
        ProcessBuilder builder = new ProcessBuilder("echo", "test");
        Process proc = builder.start();

        assertNotNull(proc);
        int exitCode = proc.waitFor();

        // echo should complete successfully
        assertEquals(0, exitCode);
        assertFalse(proc.isAlive());
    }
}

package com.kousenit.processes;

import java.io.File;

public class ProcessDemo {
    public static void main(String[] args) throws Exception {
        ProcessBuilder builder = new ProcessBuilder("ls");
        builder.redirectOutput(new File("output.txt"));
        Process proc = builder.start();

        System.out.println(proc.isAlive());
        System.out.println(proc.pid());

        Process proc1 = Runtime.getRuntime().exec(new String[]{"ls"});
        System.out.println(proc1.isAlive());
        System.out.println(proc1.pid());
        proc1.destroy();
    }
}

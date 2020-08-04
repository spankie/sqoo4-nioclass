package app;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Collections;

public class App {
    public static void ReadWithInputStream() {
        String line;
        FileInputStream fin = null;
        // FileReader fin = null;
        BufferedReader br = null;
        InputStreamReader isr = null;
        int b;
        try {
            fin = new FileInputStream("sq04-javaio/.classpath");
            // fin = new FileReader("./.classpath");
            // while ((b = fin.read()) >= 0) {
            // a byte = 8 bits
            // byte bb = 18909;
            // System.out.println((byte) b); // check the character equivalent of each
            // System.out.printf("%d ", (byte) b);
            // System.out.printf("%s\n", (char) b);
            // System.out.print(b);
            // }
            // System.out.println("----");
            isr = new InputStreamReader(fin);
            // while ((line = isr.read()) >= 0) {
            // System.out.println(line);
            // }
            br = new BufferedReader(isr);
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            fin.close();
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        } finally {
            try {
                if (fin != null)
                    fin.close();
            } catch (Exception e) {
                System.err.println("Exception: " + e.getMessage());
            }
        }

    }

    public static void WriteWithOutputStream() {
        try {
            OutputStream fout = new FileOutputStream("./file.txt");
            // min -128 max 127
            byte D = 68; // D
            byte[] dad = { D, 65, D };
            byte enter = 13; // carriage return or enter key
            fout.write(D);
            fout.write(D);
            fout.write(enter);
            fout.write(dad);
            fout.write(enter);
            fout.write(65); // A
            fout.write(65); // A
            fout.close();
        } catch (Exception e) {
            // TODO: handle exception
            System.err.println("Exception: " + e.getMessage());
        }
    }

    public static void ReadWithNIO() {
        Path path = Paths.get("sq04-javaio/.classpath");
        try {
            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
            for (String line : lines) {
                System.out.println(line);
            }
        } catch (Exception e) {
            System.err.println("exception: " + e.getMessage());
        }
    }

    public static void PipedStream() {
        // TODO: Use the piped reader and pipedwriter
        // final PipedInputStream pipedInputStream = new PipedInputStream();
        // final PipedOutputStream pipedOutputStream = new PipedOutputStream();
        // try {
        try (final PipedInputStream pipedInputStream = new PipedInputStream();
                final PipedOutputStream pipedOutputStream = new PipedOutputStream()) {
            /* Connect pipe */
            pipedInputStream.connect(pipedOutputStream);
            for (int i = 65; i < 91; i++) {
                try {
                    pipedOutputStream.write(i);
                    System.out.printf("%s ", (char) pipedInputStream.read());
                } catch (Exception e) {
                    // TODO: handle exception
                    System.err.println("Exception: " + e.getMessage());
                }
            }
            System.out.println();

        } catch (Exception e) {
            System.err.println("Exception : " + e.getMessage());
        } finally {
            System.out.println("finally block was called");
        }
    }

    public static void DoTryWithResource() {
        // MyAutoClosable ma = new MyAutoClosable();

        // try {
        try (MyAutoClosable ma = new MyAutoClosable()) {
            ma.DoSomething();
        } catch (Exception e) {
            System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage());

            for (Throwable t : e.getSuppressed()) {
                System.out.println("getSuppressed() - " + t.getClass().getSimpleName() + " - " + t.getMessage());
            }
        }
        // finally {
        //     try {
        //         ma.close();
        //     } catch (Exception e) {
        //         System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage());
        //     }
        // }
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        // ReadWithInputStream();
        // WriteWithOutputStream();
        // ReadWithNIO();
        // PipedStream();
        // DoTryWithResource();
        // env = prod
    }
}
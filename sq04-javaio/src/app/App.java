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
        // FileInputStream fin = null;
        FileReader fin = null;
        BufferedReader br = null;
        InputStreamReader isr = null;
        int b;
        try {
            fin = new FileReader("./.classpath");
            // fin = new FileInputStream("./.classpath");
            // while ((b = fin.read()) >= 0) {
            // // byte bb = 127;
            // // System.out.println((byte) b); // check the character equivalent of each
            // System.out.print((char) b);
            // // System.out.print((char) b);
            // // System.out.print(b);
            // }
            // isr = new InputStreamReader(fin);
            br = new BufferedReader(fin);
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
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

    public static void ReadWithNIO() {
        Path path = Paths.get("./.classpath");
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
                    System.out.print((char) pipedInputStream.read());
                } catch (Exception e) {
                    // TODO: handle exception
                    System.err.println("Exception: " + e.getMessage());
                }
            }

        } catch (Exception e) {
            System.err.println("Exception : " + e.getMessage());
        }
        // finally {
        // try {
        // pipedInputStream.close();
        // } catch (Exception e) {
        // System.err.println("Exception: " + e.getMessage());
        // }
        // }
    }

    public static void WriteWithOutputStream() {
        try {
            OutputStream fout = new FileOutputStream("./file.txt");
            // OutputStream fout = new (20);
            byte b = 68;
            byte enter = 13;
            fout.write(b);
            fout.write(b);
            fout.write(enter);
            fout.write(enter);
            fout.write(65);
            fout.write(65);
            fout.close();
        } catch (Exception e) {
            // TODO: handle exception
            System.err.println("Exception: " + e.getMessage());
        }
    }

    public static void main(String[] args) throws Exception {
        // ReadWithInputStream();
        // ReadWithNIO();
        // WriteWithOutputStream();
        // PipedStream();
        DoTryWithResource();
    }

    public static void DoTryWithResource() {
        // MyAutoClosable ma = new MyAutoClosable();

        try (MyAutoClosable ma = new MyAutoClosable()) {
            ma.DoSomething();
        } catch (Exception e) {
            System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage());
            for (Throwable t : e.getSuppressed()) {
                System.out.println("getSuppressed()" + t.getClass().getSimpleName() + " - " + t.getMessage());
            }
        }
        // finally {
        // try {
        // ma.close();
        // } catch (Exception e) {
        // System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage());
        // }
        // }
    }
}
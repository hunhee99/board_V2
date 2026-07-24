package util;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class TestUtil {
    private static final PrintStream ORIGINAL_OUT = System.out;

    public static Scanner genScanner(String input){
        return new Scanner(input);
    }

    public static ByteArrayOutputStream setOutToByteArray(){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        return outputStream;
    }

    public static void clearSetOutToByteArray(ByteArrayOutputStream outputStream){
        System.setOut(ORIGINAL_OUT);
    }

}

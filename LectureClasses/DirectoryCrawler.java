// This program prints out the structure of a given directory or file
import java.io.*;
import java.util.*;

public class DirectoryCrawler {

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.print("directory or file name? ");
        String name = console.nextLine();
        File f = new File(name);
        if (!f.exists()) {
           System.out.println("That file or directory does not exist");
        } else {
           print(f);
        }
    }

    // prints the name of the file
    // if the file is a directory, prints its children
    // with standard indentation for nested directories
    public static void print(File file) {
        print(file, 0);
    }

    // prints the name of the file at the given indentation level
    // if the file is a directory, prints its children at a
    // higher indentation level
    private static void print(File file, int indent) {
        for (int i = 0; i < indent; i++) {
            System.out.print("    ");
        }
        System.out.println(file.getName());
        if (file.isDirectory()) {
            // print all the children of file
            File[] subFiles = file.listFiles();
            for (File subFile : subFiles) {
                print(subFile, indent + 1);
            }
        }
    }
}

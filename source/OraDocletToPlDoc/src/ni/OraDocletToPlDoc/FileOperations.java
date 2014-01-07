package ni.OraDocletToPlDoc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Scanner;

public class FileOperations {
   
   public static String fileContents(String filename) throws FileNotFoundException {
      Scanner sc = null;
      StringBuilder builder = new StringBuilder(); 
      try {
         sc = new Scanner(new File(filename));
         while(sc.hasNextLine()){
            builder.append(sc.nextLine() + System.getProperty("line.separator"));
         }
      } catch (FileNotFoundException e) {
         System.out.println(e.getMessage());
         throw e;
      } finally {
         sc.close();
      }
      return builder.toString();
   }
   
   public static void writeFile(String filename, String content) throws IOException {
      Writer out = new OutputStreamWriter(new FileOutputStream(filename));
      try {
         out.write(content);
      } catch (IOException e) {
         throw e;
      } finally {
         out.close();
      }
   }
   
   public static void copyFile(String source, String target) throws IOException {
      File inputFile = new File(source);
      File outputFile = new File(target);
      FileReader in = new FileReader(inputFile);
      FileWriter out = new FileWriter(outputFile);
      int c;
      while ((c = in.read()) != -1) {
         out.write(c);
      }
      in.close();
      out.close();
   }
   
   public static void deleteFile(String filename) {
      File f = new File(filename);
      f.delete();
   }
   
}

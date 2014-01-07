package ni.OraDocletToPlDoc;

import java.util.*;
import java.io.*;


public class OraDocletToPlDoc {

   public static void main(String[] args) throws IOException {
      if (args.length == 0) {
         System.out.println("Usage: OradocletToPldoc <dir>");
      }
      String dir = args[0];
      DirectoryWalker rootWalker = new DirectoryWalker(dir);
      List<File> dirs = rootWalker.getDirectoryList();
      for (File subdir : dirs) {
         DirectoryWalker walker = new DirectoryWalker(subdir.getPath());
         List<File> files = walker.getFileList();
         for (File file : files ) {
            if (file.getName().matches(".*html$")) {
               HtmlFile f = new HtmlFile(file.getPath());
               f.updateIfNecessary();
               if (f.isChanged()) {
                  System.out.println(file.getPath() + " updated.");
               }
            }
         }
      }
   }

   
}

package ni.OraDocletToPlDoc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DirectoryWalker {
   
   private File dir;

   public List<File> getFileList() throws FileNotFoundException {
      validateDirectory();
      List<File> result = getFileListingNoSort(dir);
      Collections.sort(result);
      return result;
   }

   public List<File> getDirectoryList() throws FileNotFoundException {
      validateDirectory();
      List<File> result = getDirectoryListingNoSort(dir);
      Collections.sort(result);
      return result;
   }
   
   private List<File> getDirectoryListingNoSort(File startDir) throws FileNotFoundException {
      List<File> filesDirs = getFilesDirs();
      List<File> result = new ArrayList<File>();
      for(File file : filesDirs) {
         if (!file.isFile() ) {
            result.add(file);
         }
      }
      return result;
   }
   
   private List<File> getFileListingNoSort(File startDir) throws FileNotFoundException {
      List<File> filesDirs = getFilesDirs();
      List<File> result = new ArrayList<File>();
      for(File file : filesDirs) {
         if (file.isFile() ) {
            result.add(file);
         }
      }
      return result;
   }
   
   private List<File> getFilesDirs() {
      File[] filesAndDirs = dir.listFiles();
      return Arrays.asList(filesAndDirs);
   }

   public DirectoryWalker(String directory) {
      this.dir = new File(directory);
   }
   
   private void validateDirectory() throws FileNotFoundException {
      if (dir == null) {
         throw new IllegalArgumentException("Directory should not be null.");
      }
      if (!dir.exists()) {
         throw new FileNotFoundException("Directory does not exist: " + dir);
      }
      if (!dir.isDirectory()) {
         throw new IllegalArgumentException("Is not a directory: " + dir);
      }
      if (!dir.canRead()) {
         throw new IllegalArgumentException("Directory cannot be read: " + dir);
      }
   }

}

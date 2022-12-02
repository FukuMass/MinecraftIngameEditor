package io.github.fukumass.minecraftingameeditor.minecraftingameeditor;

import java.io.IOException;
import java.nio.file.*;
import java.util.stream.Stream;

public class FileBrowserUtil {

  public static Stream<FileInfo> listFiles(String path) throws IOException {
    return listFiles(Paths.get(path));
  }

  public static Stream<FileInfo> listFiles(Path path) throws IOException {
    // A stream is sort of like an array, only you don't know the total number or elements
    // here we use Files.list, which returns a stream of files, the files inside the directory we pass it
    // we need to use try () here because the stream returned by Files.list is actually more than a regular
    // stream, it is a stream with resource, which means it takes up system resources as long as it is used
    // so by using this try block we make sure that after the stream is used, it is "closed" (the system
    // resources are freed)
    // in java there is two concepts that use try:
    // 1. running code that could fail and in case it fails, do something else, or
    // 2. running code that needs "cleanup" like deleting temporary files and such
    // the first form looks like this:
    /*  ```java
    * try {
    *   // the code
    * } catch (IOException inputOutputException) {
    *   // the code that is executed when the code above fails
    * } finally {
    *   // optional, the code that is run when the first OR the second block is done
    * }
    *
    * ``` */
    // the second one you can see below
    // it has this structure
    /* ```java
    try (VARIABLE DECLARATION AND ASSIGNMENT) {
      // do something with the variable
      // at the end of this block, the "close" method of the variable is called automatically
    }
    ``` */
    try (Stream<Path> files = Files.list(path)) {
      // the files are actually not of type File but of type Path, they don't contain any information about
      // the file aside from the file name and location, it could be a directory or a file or not exist at
      // all... so we find out this information and create our new FileInfo object to contain that information
      // for us. the method map we use here converts every member of a stream using a "mapping" function
      // think of it like a dictionary. the function is called for each path and "looks up" -> "maps" it to
      // its definition, in this case the FileInfo object we just created
      return files.map(path2 -> new FileInfo(path2, getFileType(path2)));
    }
  }

  public static FileType getFileType(Path path) {
    if (Files.isDirectory(path)) return FileType.Directory;
    if (Files.isRegularFile(path)) return FileType.TextFile;
    return FileType.BinaryFile;
  }

  public static String readFile(Path path) throws IOException {
    return new String(Files.readAllBytes(path));
  }

  public static void writeFile(Path path, String content) throws IOException {
    Files.writeString(path, content);
  }

  public static void deleteFile(Path path) throws IOException {
    Files.delete(path);
  }

  public static void moveFile(Path from, Path to) throws IOException {
    Files.move(from, to);
  }

}

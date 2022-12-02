package io.github.fukumass.minecraftingameeditor.minecraftingameeditor;

import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Stream;

public class FileInfo {

  Path path;
  FileType fileType;

  public FileInfo(Path path, FileType fileType) {
    this.path = path;
    this.fileType = fileType;
  }

  public Path getPath() {
    return path;
  }

  public void setPath(Path path) {
    this.path = path;
  }

  public FileType getFileType() {
    return fileType;
  }

  public void setFileType(FileType fileType) {
    this.fileType = fileType;
  }

  public String getFileName() {
    return this.path.getFileName().toString();
  }

  public boolean isDirectory() {
    return this.fileType == FileType.Directory;
  }

  public Stream<FileInfo> listChildren() throws IOException {
    return FileBrowserUtil.listFiles(this.path);
  }

  public String readContent() throws IOException {
    return FileBrowserUtil.readFile(this.path);
  }

  public void writeContent(String content) throws IOException {
    FileBrowserUtil.writeFile(this.path, content);
  }

  public void delete() throws IOException {
    FileBrowserUtil.deleteFile(this.path);
  }

  public void moveTo(Path target) throws IOException {
    FileBrowserUtil.moveFile(this.path, target);
    this.path = target;
  }

  public void rename(String newName) throws IOException {
    // get the parent folder, look up the new name inside that folder...
    moveTo(path.getParent().resolve(newName));
  }
}

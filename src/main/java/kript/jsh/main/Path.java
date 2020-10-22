package kript.jsh.main;

public class Path {

  private static final String fimilarPath = System.getProperty("user.dir");
  private static String currentPath = fimilarPath;

  public static String getFimilarPath () {
    return fimilarPath;
  }

  public static String getCurrentPath () {
    return currentPath;
  }

  public static void setCurrentPath (String path) {
    currentPath = path;
  }
}

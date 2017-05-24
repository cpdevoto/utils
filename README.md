# utils
Random utility projects

## JAR Utils
The JAR utils includes a JAR file scanner project.  You can pass it the name of a directory and it will recursively scan all JAR files within that directory and its child directories, printing out the names of every class it finds together with the name of the JAR file in which it was found.  Combine this with the grep command in order find which JAR file contains a particular class (e.g. ./jar-scanner.bat "C:/Program Files/Java/jre1.8.0_60" | grep "java.util.List")

## String Utils

The following method can be used to determine the index of the character representing the first difference that appears between two strings.  If there are no differences, the method returns -1:

```Java
  private int findFirstDifference (String s1, String s2) {
    if (s1.equals(s2)) {
      return -1;
    }
    int index = 0;
    for (char c1 = s1.charAt(index), c2 = s2.charAt(index); index < s1.length()
        && index < s2.length() && c1 == c2; index++, c1 = s1.charAt(index), c2 = s2.charAt(index));
    return index;
  }

```

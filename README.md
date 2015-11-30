# utils
Random utility projects

##JAR Utils
The JAR utils includes a JAR file scanner project.  You can pass it the name of a directory and it will recursively scan all JAR files within that directory and its child directories, printing out the names of every class it finds together with the name of the JAR file in which it was found.  Combine this with the grep command in order find which JAR file contains a particular class (e.g. ./run.bat "C:/Program Files/Java/jre1.8.0_60" | grep "java.util.List")

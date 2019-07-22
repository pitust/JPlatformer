javac src/Main.java -d out -cp lib\pi.jar
jar cvfe main.jar Main -C out/ Main.class
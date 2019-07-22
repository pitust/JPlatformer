CLASSES := out/Main.class $(wildcard data/*)
build: main.jar

main.jar: $(CLASSES)
	jar cvfe main.jar Main -C out/ Main.class
out/%.class: src/%.java
	javac $< -d out -cp lib\pi.jar

run: main.jar
	java -Xdiag -cp ".\lib\pi.jar;main.jar" -Djava.library.path=".\lib" Main
clean:
	rm -rf out/*.java
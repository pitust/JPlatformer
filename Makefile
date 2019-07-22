CLASSES := out/Main.class $(wildcard data/*)
build: out.jar

out.jar: $(CLASSES)
	jar cvfe main.jar Main -C out/ Main.class
out/%.class: src/%.java
	javac $< -d out -cp lib\pi.jar

run: out.jar
	java -jar out.jar
clean:
	rm -rf out/*.java
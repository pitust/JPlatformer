CLASSES := out/Main.class out/Entity.class
CLASSNAMES := Main Entity
CLASSFILENAMES := Main.class Entity.class
build: main.jar

main.jar: $(CLASSES) $(wildcard data/*)
	jar cvfe main.jar Main -C out/ $(CLASSFILENAMES)
out/%.class: src/%.java
	javac $< -d out -cp lib\pi.jar

run: main.jar
	java -Xdiag -cp ".\lib\pi.jar;main.jar" -Djava.library.path=".\lib" Main
clean:
	rm -rf out/*.class

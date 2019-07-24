CLASSES := out/Util.class out/Blocks.class out/Entity.class out/Player.class out/Frames.class out/Level.class out/EightBitText.class out/Game.class out/Main.class
CLASSFILENAMES := Main.class out/Entity.class out/Player.class out/Util.class out/Blocks.class out/Frames.class out/EightBitText.class out/Game.class out/Level.class 'out/Level$$1.class'
build: main.jar

main.jar: $(CLASSES)
	jar cvfe main.jar Main -C out/ $(CLASSFILENAMES)
out/%.class: src/%.java
	javac $< -d out -cp "lib\pi.jar;out"

run: main.jar
	java -Xdiag -cp ".\lib\pi.jar;main.jar" -Djava.library.path=".\lib" Main
clean:
	rm -rf out/*.class

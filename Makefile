CLASSES := out/Util.class out/Blocks.class out/Entity.class out/Player.class out/Frames.class out/Level.class out/EightBitText.class out/Goal.class out/Game.class out/JPlatformer.class
CLASSFILENAMES := JPlatformer.class out/Entity.class out/Player.class out/Util.class out/Blocks.class out/Frames.class out/EightBitText.class out/Goal.class out/Game.class out/Level.class 'out/Level$$1.class'
build: JPlatformer.jar

main.jar: $(CLASSES)
	jar cvfe JPlatformer.jar JPlatformer -C out/ $(CLASSFILENAMES)
out/%.class: src/%.java
	javac $< -d out -cp "lib\pi.jar;out"

run: main.jar
	java -Xdiag -cp ".\lib\pi.jar;JPlatformer.jar" -Djava.library.path=".\lib" JPlatformer
clean:
	rm -rf out/*.class

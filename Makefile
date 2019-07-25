CLASSES := out/Blocks.class out/Util.class out/EightBitText.class out/Level.class out/Entity.class out/Player.class out/Frames.class out/Goal.class out/Game.class out/JPlatformer.class
CLASSFILENAMES := JPlatformer.class out/Entity.class out/Player.class out/Util.class out/Blocks.class 'out/Blocks$$1.class' out/Frames.class out/EightBitText.class out/Goal.class out/Game.class out/Level.class 'out/Level$$1.class'
build: JPlatformer.jar

main.jar: $(CLASSES)
	jar cvfe JPlatformer.jar JPlatformer -C out/ $(CLASSFILENAMES)
out/%.class: src/%.java
	javac $< -d out -cp "lib\pi.jar;out"

run_linux_god: main.jar
	java -Xdiag -cp ".\lib\pi.jar;JPlatformer.jar" -Djava.library.path=".\lib" JPlatformer $(shell cat .godmode)
run_god: main.jar
	java -Xdiag -cp ".\lib\pi.jar;JPlatformer.jar" -Djava.library.path=".\lib" JPlatformer $(shell powershell cat .godmode)
run: main.jar
	java -Xdiag -cp ".\lib\pi.jar;JPlatformer.jar" -Djava.library.path=".\lib" JPlatformer
clean:
	rm -rf out/*.class
init:
	echo "NO" > .godmode
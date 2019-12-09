target: build mainwindow Main package clean
	java -jar 扫雷.jar
	echo "done"

build:
	mkdir build

mainwindow: ms/mainwindow.java
	javac -d build ms/mainwindow.java -verbose

Main: ms/Main.java
	javac -d build ms/Main.java -verbose

package:
	jar cvfm 扫雷.jar MANIFEST.MF -C build .

clean:
	rm -rf build

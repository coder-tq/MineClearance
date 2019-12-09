target: build mainwindow ScanLei Setting RePlay Main package clean
	java -jar 扫雷.jar
	echo "done"

build:
	mkdir build

mainwindow: ms/mainwindow.java
	javac -d build ms/mainwindow.java

ScanLei: ms/ScanLei.java
	javac -d build ms/ScanLei.java

Setting: ms/ScanLei.java
	javac -d build ms/Setting.java

RePlay: ms/ScanLei.java
	javac -d build ms/RePlay.java

Main: ms/Main.java
	javac -d build ms/Main.java

package:
	jar cvfm 扫雷.jar MANIFEST.MF -C build .

clean:
	rm -rf build

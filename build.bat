@echo off

echo Cleaning old build...
rmdir /s /q build 2>nul
del SpriteSheetCuttingTool-all.jar 2>nul

echo Compiling Java files...
C:\openJdk-25\bin\javac -cp flatlaf-3.7.1.jar src\*.java

echo Creating build folders...
mkdir build
mkdir build\app
mkdir build\app\src

echo Extracting FlatLaf...
cd build\app
C:\openJdk-25\bin\jar xf ..\..\flatlaf-3.7.1.jar
cd ..\..

echo Copying compiled classes...
copy src\*.class build\app\src\ >nul

echo Creating manifest...
(
echo Main-Class: src.ui
echo.
) > manifest-fat.txt

echo Building jar...
cd build\app
C:\openJdk-25\bin\jar cfm ..\..\SpriteSheetCuttingTool-all.jar ..\..\manifest-fat.txt *
cd ..\..

echo.
echo Build finished!
echo Your jar: SpriteSheetCuttingTool-all.jar
pause
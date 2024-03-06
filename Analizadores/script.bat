echo Creando JFlex
java -jar "C:\Users\cheji\Downloads\Anal\Anal\jflex\jflex-1.9.1\lib\jflex-full-1.9.1.jar" -d ..\DB_emulador\src\main\java\com\mycompany\db_emulador Lexer.flex

echo Creando cup
java -jar "C:\Users\cheji\Downloads\Anal\Anal\cup\cup-20160615\java-cup-11b.jar" -parser Sintactico Sintactico.cup
move Sintactico.java ..\DB_emulador\src\main\java\com\mycompany\db_emulador\
move sym.java ..\DB_emulador\src\main\java\com\mycompany\db_emulador\
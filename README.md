# CmaTest
Техническое задание: 
Имеется корневая папка. В этой папке могут находиться текстовые файлы, а также другие папки. В других папках также могут находится текстовые файлы и папки (уровень вложенности может оказаться любым).
Найти все текстовые файлы, отсортировать их по имени и склеить содержимое в один текстовый файл.




Реализовал без использования Files.walkFileTree ,потому что не знал про его существование
Применил метод рекурсивного вызова для реализации алгоритма поиска.

Созданы три класса:


Для поиска необходимых файлов -класс FileFinder 


Для сортировки найденных файлов -класс FileSorter 



Класс для создания экземпляров этих классов и запуска программа -Finder 


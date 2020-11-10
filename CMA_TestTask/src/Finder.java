import java.io.*;
import java.util.List;

public class Finder {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        FileFinder fileFinder = new FileFinder(); //экземпляр для поиска
        System.out.println("Введите путь к корневой папке");
        String startPath = reader.readLine(); // путь к корневой папке
        try {
            List result = fileFinder.findFiles(startPath); // находим нужные файлы в лист
            FileSorter fileSorter = new FileSorter(); // экземпляр для сортировки
            result = fileSorter.sort(result); // сортируем ранее найденный список файлов
            for (int i = 0; i < result.size(); i++) { // проходим по списку и выводим имена найденных файлов
                File currentObject = (File)result.get(i);
                System.out.println("Найден файл: " + currentObject.getName());
            }
            //Иттерируемся по списку файлов
            for(Object f : result) {
                FileInputStream in = new FileInputStream((File) f); // берем объекты из списка
                //записываем данные из предыдущего файла в result файл.
                FileOutputStream out = new FileOutputStream("C://Users//Jarvis//Desktop//result.txt", true);
                byte[] buffer = new byte[in.available()];
                in.read(buffer, 0, buffer.length);
                out.write(buffer, 0, buffer.length);
            }
            System.out.println("Всего найдено " + fileFinder.getFilesNumber() + "файлов");
        }
        catch (Exception err) {
            System.out.println(err.getMessage());
        }
    }
}
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileFinder {

    private long FilesNumber = 0;//число файлов
    private long DirectoriesNumber = 0; // число папок

    private final int FILES = 0; // константное значение для поиска
    private final int DIRECTORIES = 1;


/*
метод для поиска всех файлов
startPath-корневая папка
 */
    public List findFiles(String startPath)
            throws Exception {
        return find(startPath, FILES);
    }

    public long getFilesNumber() {
        return FilesNumber;
    }
// здесь я сделал поиск и по папкам,пусть будет:D
    public List findDirectories(String startPath)
            throws Exception {
        return find(startPath, DIRECTORIES);
    }

    public long getDirectoriesNumber() {
        return DirectoriesNumber;
    }

/*
Этот метод выполняет и проверяет условия поиска,затем вызывает метод search
для выполнения поиска
 */
    private List find(String startPath, int objectType)
            throws Exception {
        if (startPath == null) {
            throw new Exception("Не задана корневая папка");
        }
        File topDirectory = new File(startPath); //корневая папка
        if (!topDirectory.exists()) {
            throw new Exception("Указанный путь не существует");
        }
        FilesNumber = 0;
        ArrayList result = new ArrayList(); // Список результатов
        search(topDirectory, result, objectType); // выполняем поиск
        return result;
    }

    /*
    Метод выполняет поиск объектов заданного типа (Параметр objectType)
    Если встречается вложенная папка -метод рекурсивно вызывает сам себя
    текущая директорая-topDirectory
     */
    private void search(File topDirectory, List result, int objectType) {
        File[] list = topDirectory.listFiles(); // получаем список объектов в текущей директории
        for (int i = 0; i < list.length; i++) {
            if (list[i].isDirectory()) {
                if (objectType != FILES) {
                    DirectoriesNumber++; //если это директория -увеличиваем счетчик
                    result.add(list[i]);
                }
                search(list[i], result, objectType);
            } else {
                if (objectType != DIRECTORIES) { // счетчик для файлов.
                    FilesNumber++;
                    result.add(list[i]);
                }
            }
        }
    }
}
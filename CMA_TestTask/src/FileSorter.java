import java.io.File;
import java.text.Collator;
import java.util.*;
import java.util.regex.Pattern;

public class FileSorter implements Comparator {
    Pattern pattern = null;
    Collator collator = null; // для работы со строками на разных языках

    public FileSorter() {
        String separator = File.separator;
        if(separator.equals("\\")) { // определяем системный разделитель
            separator = "\\\\";
        }
        // создаем шаблон на основе системного разделителя ,получаем системную страну и язык.
        pattern = Pattern.compile(separator, Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
        String country = System.getProperty("user.country");
        String language = System.getProperty("user.language");
        collator = Collator.getInstance(new Locale(country, language)); //на основе региональных настроек экземпляр класса

    }

    @Override
    public int compare(Object o1, Object o2) {
        if(o1 != null && o2 != null && o1 instanceof File && o2 instanceof File) {
            File file1 = (File)o1; // если объекты не null и имеют тип файл-приводим к нему
            File file2 = (File)o2;
            //получаем полный путь к имени файла
            String file1Path = file1.getAbsolutePath();
            String file2Path = file2.getAbsolutePath();
            if(file1Path.equals(file2Path)) {
                return 0;
            }
            //определяем глубину размещения путем разбивания на части и сравниваем длину.
            String[] result1 = pattern.split(file1Path);
            String[] result2 = pattern.split(file2Path);
            if(result1.length > result2.length) {
                return 1; // если глубина вложения 1 файла > глубины 2
            }
            if(result1.length < result2.length) {
                return -1; // если 2 > 1
            }
            // если файлы на одном уровне,сортируем по алфавиту
            if(result1.length == result2.length) {
                return collator.compare(file1Path, file2Path);
            }
        }
        return 0;
    }

    public List sort(List FileList) {
        ArrayList result = new ArrayList(FileList.size());
        result.addAll(FileList);
        Collections.sort(result, this);
        return result;
    }
}
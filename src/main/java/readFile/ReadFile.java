package readFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReadFile {

    public static void main(String[] args) throws IOException {

        String path = "C:\\Users\\Inspirion 7577\\.IntelliJIdea2018.3\\WarAndPeace\\Book.txt";
        File file = new File(path);

        List<String> list = Files.lines(Paths.get(file.getAbsolutePath()))
                .flatMap(s -> Stream.of(s.split("[^A-Za-zА-Яа-я]+")))
                .map(String::toLowerCase)
//                .sorted()
                .collect(Collectors.toList());
//                .forEach(System.out::println);

        HashMap<String, Integer> words = new HashMap<String, Integer>();
        // Инициализирую счетчик
        Integer item;
        // прогоняем по циклу List и
        // закидываем слова в HashMap как ключ, а значение(Value) создаем или увеличиваем на 1
        for (String word : list) {
            item = words.get(word);
            words.merge(word, 1, Integer::sum);
//            if (item == null) words.put(word, 1); // если нет в списке то добавить со значением 1
//            else words.put(word, item + 1); // если есть такое слово(Key), то +1
        }

        Map.Entry<String, Integer> maxEntry = null;
        for (Map.Entry<String, Integer> entry : words.entrySet()) {
            if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
                maxEntry = entry;
            }
        }
        for (Map.Entry<String, Integer> entry : words.entrySet()) {
            if (entry.getValue() == maxEntry.getValue()) {
                System.out.println("The most common word is \"" + entry.getKey() + "\". It is found " + entry.getValue() + " times");
            }
        }
    }
}

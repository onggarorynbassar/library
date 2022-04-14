import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;

public class BookTest {

    @Test
    public void addBookTest() {
        new BookService().addBook(new Book(5, "book5"), 8);
    }

    public static List<String> readFile(String fileName) throws Exception {
        BufferedReader fileReader = new BufferedReader(new FileReader(fileName));
        return fileReader.lines().collect(Collectors.toList());
    }
}

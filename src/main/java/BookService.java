import java.io.*;

public class BookService {
    public void addBook(Book book, int count) {
        try {
            int id = 0;

            StringBuilder inputBuffer = new StringBuilder();

            BufferedReader readerBooks = new BufferedReader(new FileReader("books.txt"));
            BufferedReader readerBookCount = new BufferedReader(new FileReader("book_count.txt"));

            BufferedWriter writerBooks = new BufferedWriter(new FileWriter("books.txt", true));
            BufferedWriter writerBookCount = new BufferedWriter(new FileWriter("book_count.txt", true));

            String currentLineOfBooks;
            while ((currentLineOfBooks = readerBooks.readLine()) != null) {
                Book currentBook = new Book(Integer.parseInt(currentLineOfBooks.split(":", 2)[0]), currentLineOfBooks.split(":", 2)[1]);

                if (currentBook.name.equals(book.name)) {
                    id = currentBook.id;
                    break;
                }
            }
            readerBooks.close();

            if (id == 0) {
                writerBooks.write(book.id + ":" + book.name + "\n");
                writerBookCount.write(book.id + ":" + count + "\n");
            } else {
                String currentLineOfBookCount;
                int currentBookId = 0;
                int currentBookCount = 0;
                while ((currentLineOfBookCount = readerBookCount.readLine()) != null) {
                    inputBuffer.append(currentLineOfBookCount);
                    inputBuffer.append('\n');
                    currentBookId = Integer.parseInt(currentLineOfBookCount.split(":")[0]);
                    currentBookCount = Integer.parseInt(currentLineOfBookCount.split(":")[1]);
                }

                int booksCount = currentBookCount + count;
                String inputStr = inputBuffer.toString();
                inputStr = inputStr.replace(currentBookId + ":" + currentBookCount, currentBookId + ":" + booksCount);
                FileOutputStream fileOut = new FileOutputStream("book_count.txt");
                fileOut.write(inputStr.getBytes());
                fileOut.close();
            }
            readerBookCount.close();
            writerBooks.close();
            writerBookCount.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

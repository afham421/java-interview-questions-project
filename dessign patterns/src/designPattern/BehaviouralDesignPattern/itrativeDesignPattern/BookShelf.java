package designPattern.BehaviouralDesignPattern.itrativeDesignPattern;

public class BookShelf implements BookCollection {
    private Book[] books;
    private int index = 0;

    public BookShelf(int size) {
        books = new Book[size];
    }

    public void addBook(Book book) {
        if (index < books.length) {
            books[index++] = book;
        }
    }

    public Iterator createIterator() {
        return new BookShelfIterator(books);
    }
}

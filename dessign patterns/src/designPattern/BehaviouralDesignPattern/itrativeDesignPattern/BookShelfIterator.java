package designPattern.BehaviouralDesignPattern.itrativeDesignPattern;

public class BookShelfIterator implements Iterator {
    private Book[] books;
    private int position = 0;

    public BookShelfIterator(Book[] books) {
        this.books = books;
    }

    public boolean hasNext() {
        return position < books.length && books[position] != null;
    }

    public Object next() {
        return books[position++];
    }
}


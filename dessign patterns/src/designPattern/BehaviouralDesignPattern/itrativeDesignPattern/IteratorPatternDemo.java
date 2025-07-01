package designPattern.BehaviouralDesignPattern.itrativeDesignPattern;

public class IteratorPatternDemo {
    public static void main(String[] args) {
        BookShelf shelf = new BookShelf(3);
        shelf.addBook(new Book("Design Patterns"));
        shelf.addBook(new Book("Clean Code"));
        shelf.addBook(new Book("Refactoring"));

        Iterator iterator = shelf.createIterator();
        while (iterator.hasNext()) {
            Book book = (Book) iterator.next();
            System.out.println("Book: " + book.getTitle());
        }
    }
}


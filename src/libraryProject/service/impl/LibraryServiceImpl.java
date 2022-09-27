package libraryProject.service.impl;

import libraryProject.dao.Dao;
import libraryProject.model.Book;
import libraryProject.model.LibraryMember;
import libraryProject.service.LibraryService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collector;

public class LibraryServiceImpl implements LibraryService {
    private static final Scanner scannerN = new Scanner(System.in);
    private static final Scanner scannerS = new Scanner(System.in);
    private Dao dao;

    public LibraryServiceImpl(Dao dao) {
        this.dao = dao;
    }

    @Override
    public List<LibraryMember> getLibraryMembers() {
        return dao.getLibrary().getLibraryMembers();
    }

    @Override
    public void addLibraryMember(LibraryMember member) {
       dao.getLibrary().getLibraryMembers().add(member);
    }

    @Override
    public LibraryMember findLibraryMemberById(Long id) {
        return null;
    }

    public void searchMemberID(){
        System.out.println("Напишите ID:");
        int id = scannerN.nextInt();
        dao.getLibrary().getLibraryMembers().stream().filter(x -> x.getMemberId() == id)
                .findFirst().stream()
                .forEach(x -> System.out.println("Member ID = " + x.getMemberId() +
                        "\nName = " + x.getName() + "\nCurrently reading = " +
                        x.getCurrentlyReading() + "\nRead books: " + x.getFinishedBooks()));
    }

    @Override
    public void deleteLibraryMemberByID(Long id) {
        Optional<LibraryMember> optionalLibraryMember = dao.getLibrary().getLibraryMembers().stream().filter(x -> x.getMemberId() == id).findFirst();
        optionalLibraryMember.ifPresent(x ->  dao.getLibrary().getLibraryMembers().remove(x));
    }

    @Override
    public void addBookToLibrary(Book book) {
        dao.getLibrary().getBooks().add(book);
    }

    @Override
    public List<Book> getLibraryBooks() {
        return dao.getLibrary().getBooks();
    }

    @Override
    public void deleteLibraryBookByID(Long id) {
        Optional<Book> optionalLibraryMember = dao.getLibrary().getBooks().stream().filter(x -> x.getBookId() == id).findFirst();
        optionalLibraryMember.ifPresent(x ->  dao.getLibrary().getBooks().remove(x));
    }

    @Override
    public void findLibraryBookById() {
        System.out.println("Напишите ID:");
        int id = scannerN.nextInt();
        dao.getLibrary().getBooks().stream().filter(x -> x.getBookId() == id)
                .findFirst().stream()
                .forEach(x -> System.out.println("Book ID = " + x.getBookId() +
                        "\ntitle = " + x.getTitle() + "\nCurrently Holding = " + x.getCurrentHolder().getName()));}

    @Override
    public void addBookToMember() {
        System.out.println("Введите ID читателя:");
        long number = scannerN.nextLong();
        System.out.println("Введите ID книги:");
        long number1 = scannerN.nextLong();
        Optional<LibraryMember> optionalLibraryMember = dao.getLibrary().
                getLibraryMembers().stream().filter(x -> x.getMemberId() == number && x.getCurrentlyReading() == null).findFirst();

        Optional<Book> optionalBook = dao.getLibrary().getBooks().stream().filter(x -> x.getBookId() == number1 && x.getCurrentHolder() == null).findFirst();

        if (optionalBook.isPresent() && optionalLibraryMember.isPresent()){
            optionalLibraryMember.ifPresent(x -> x.setCurrentlyReading(optionalBook.get()));
            optionalBook.ifPresent(x -> x.setCurrentHolder(optionalLibraryMember.get()));
        }else{
            System.out.println("Читатель уже читает книгу или книга занята");
        }
    }

    @Override
    public void removeBookFromReading(){
        System.out.println("Введите ID читателя:");
        long number = scannerN.nextLong();
        System.out.println("Введите ID книги:");
        long number1 = scannerS.nextLong();
        Optional<LibraryMember> optionalLibraryMember = dao.getLibrary().
                getLibraryMembers().stream().filter(x -> x.getMemberId() == number && x.getCurrentlyReading() != null).findFirst();

        Optional<Book> optionalBook = dao.getLibrary().getBooks().stream().filter(x -> x.getBookId() == number1 && x.getCurrentHolder() != null).findFirst();

        if (optionalBook.isPresent() && optionalLibraryMember.isPresent()){
            optionalLibraryMember.ifPresent(x -> x.setCurrentlyReading(null));
            optionalLibraryMember.ifPresent(x -> x.getFinishedBooks().add(optionalBook.get()));
            optionalBook.ifPresent(x -> x.setCurrentHolder(null));
        }else{
            System.out.println("Такой книги или читателя нету");
        }
    }
}

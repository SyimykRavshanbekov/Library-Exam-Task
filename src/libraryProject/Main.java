package libraryProject;

import libraryProject.dao.Dao;
import libraryProject.model.Book;
import libraryProject.model.Library;
import libraryProject.model.LibraryMember;
import libraryProject.service.impl.LibraryServiceImpl;

import java.util.Scanner;

public class Main {
    static Scanner scannerN = new Scanner(System.in);
    static Scanner scannerS = new Scanner(System.in);
    static Scanner scannerB = new Scanner(System.in);
    public static void main(String[] args) {
        Dao dao = new Dao(new Library());
        LibraryServiceImpl libraryService = new LibraryServiceImpl(dao);

        String operation = "null";

        while(!operation.equals("x")){
            buttons();
            System.out.println("Напишите команду: ");
            operation = scannerB.nextLine();

            switch (operation){
                case "1" -> libraryService.addLibraryMember(new LibraryMember(scannerS.nextLong(), scannerN.nextLine()));
                case "2" -> libraryService.getLibraryMembers().forEach(System.out::println);
                case "3" -> libraryService.searchMemberID();
                case "4" -> libraryService.deleteLibraryMemberByID(scannerS.nextLong());
                case "5" -> {
                    Book book = new Book();
                    System.out.println("Напишите ID книги:");
                    book.setBookId(scannerS.nextLong());
                    scannerS.nextLine();
                    System.out.println("Напишите заголовок книги:");
                    book.setTitle(scannerS.nextLine());
                    libraryService.addBookToLibrary(book);
                }
                case "6" -> libraryService.getLibraryBooks().forEach(System.out::println);
                case "7" -> libraryService.findLibraryBookById();
                case "8" -> libraryService.deleteLibraryBookByID(scannerN.nextLong());
                case "9" -> libraryService.addBookToMember();
                case "10" -> libraryService.removeBookFromReading();
            }
        }
    }

    public static void  buttons(){
        System.out.println("\n<><><><><><><><><><><><><><><><><><><><><><><><><><><><>");
        System.out.println("Нажмите 1, чтобы добавить нового участника в библиотеку.");
        System.out.println("Нажмите 2, чтобы увидеть всех участников библиотеки.");
        System.out.println("Нажмите 3, чтобы найти участника по ID и увидеть данные участника, читаемая книга и прочитанные.");
        System.out.println("Нажмите 4, чтобы удалить участника по ID.");
        System.out.println("<><><><><><><><><><><><><><><><><><><><><><><><><><><><>");
        System.out.println("Нажмите 5, чтобы добавить книгу в библиотеку.");
        System.out.println("Нажмите 6, чтобы увидеть все книги в библиотеке.");
        System.out.println("Нажмите 7, чтобы найти книгу по ID.");
        System.out.println("Нажмите 8, чтобы удалить книгу по ID.");
        System.out.println("<><><><><><><><><><><><><><><><><><><><><><><><><><><><>");
        System.out.println("Нажмите 9, чтобы ввести memberId участника и bookId книги, добавить в читаемые");
        System.out.println("Нажмите 10, чтобы ввести memberId участника и bookId книги, добавить в прочитанные");
        System.out.println("Нажмите x, чтобы завершить программу.");
    }
}

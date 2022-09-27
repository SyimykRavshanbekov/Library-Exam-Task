package libraryProject.service;

import libraryProject.model.Book;
import libraryProject.model.LibraryMember;

import java.util.List;

public interface LibraryService {
    List<LibraryMember> getLibraryMembers();
    void addLibraryMember(LibraryMember member);

    LibraryMember findLibraryMemberById(Long id);
    void deleteLibraryMemberByID(Long id);

    void addBookToLibrary(Book book);

    List<Book> getLibraryBooks();

    void findLibraryBookById();

    void deleteLibraryBookByID(Long id);

    void addBookToMember();

    void removeBookFromReading();
}

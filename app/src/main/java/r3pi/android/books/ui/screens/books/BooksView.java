package r3pi.android.books.ui.screens.books;

import java.util.List;

import r3pi.android.books.model.Book;

/**
 * Created by konpach on 15/12/2017.
 */

public interface BooksView {

    void showBooks(List<Book> books);


    void error(Throwable e);

    void error();

    void showErrorMessage();
}

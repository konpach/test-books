package r3pi.android.books.ui.screens.bookDetail;

import java.util.List;

import r3pi.android.books.model.Book;

/**
 * Created by konpach on 18/12/2017.
 */

public interface BookDetailView {
    void showBook(Book book);

    void error(Throwable e);

    void error();

    void showErrorMessage();
}

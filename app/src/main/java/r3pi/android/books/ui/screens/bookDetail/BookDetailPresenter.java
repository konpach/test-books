package r3pi.android.books.ui.screens.bookDetail;

import r3pi.android.books.ui.screens.books.BooksView;

/**
 * Created by konpach on 18/12/2017.
 */

public interface BookDetailPresenter {
    void getBook(String id);
    void setView(BookDetailView view);
    void dropView();
}

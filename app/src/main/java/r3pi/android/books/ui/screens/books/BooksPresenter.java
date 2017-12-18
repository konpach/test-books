package r3pi.android.books.ui.screens.books;

/**
 * Created by konpach on 15/12/2017.
 */

public interface BooksPresenter {
    void getBooks(String queryString, int page,int maxResults);
    void setView(BooksView view);
    void dropView();
}

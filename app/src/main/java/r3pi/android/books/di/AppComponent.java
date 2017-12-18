package r3pi.android.books.di;

import javax.inject.Singleton;

import dagger.Component;
import r3pi.android.books.ui.screens.bookDetail.BookDetailActivity;
import r3pi.android.books.ui.screens.bookDetail.BookDetailPresenter;
import r3pi.android.books.ui.screens.bookDetail.BookDetailPresenterImpl;
import r3pi.android.books.ui.screens.books.BooksActivity;
import r3pi.android.books.ui.screens.books.BooksPresenterImpl;

/**
 * Created by konpach on 15/12/2017.
 */
@Singleton
@Component(modules = {AppModule.class,PresenterModule.class,GoogleBooksNetwork.class})
public interface AppComponent {
    void inject (BooksPresenterImpl booksPresenter);
    void inject (BooksActivity booksActivity);

    void inject (BookDetailPresenterImpl bookDetailPresenter);
    void inject (BookDetailActivity bookDetailActivity);
}

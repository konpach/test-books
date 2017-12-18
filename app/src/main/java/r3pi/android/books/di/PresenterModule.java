package r3pi.android.books.di;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import r3pi.android.books.ui.screens.bookDetail.BookDetailPresenter;
import r3pi.android.books.ui.screens.bookDetail.BookDetailPresenterImpl;
import r3pi.android.books.ui.screens.books.BooksPresenter;
import r3pi.android.books.ui.screens.books.BooksPresenterImpl;

/**
 * Created by konpach on 15/12/2017.
 */
@Module
public class PresenterModule {

    @Provides
    @Singleton
    BooksPresenter provideBooksPresenter(Context context){
        return new BooksPresenterImpl(context);
    }


    @Provides
    @Singleton
    BookDetailPresenter provideBookDetailPresenter(Context context){
        return new BookDetailPresenterImpl(context);
    }
}

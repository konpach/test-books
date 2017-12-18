package r3pi.android.books.ui.screens.bookDetail;

import android.content.Context;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import r3pi.android.books.app.BooksApplication;
import r3pi.android.books.network.GoogleBooksApi;
import r3pi.android.books.ui.screens.books.BooksView;

/**
 * Created by konpach on 18/12/2017.
 */

public class BookDetailPresenterImpl implements BookDetailPresenter {

    private BookDetailView view;

    protected CompositeDisposable compositeDisposable;

    @Inject
    GoogleBooksApi api;

    public BookDetailPresenterImpl(Context context) {
        ((BooksApplication)context).getAppComponent().inject(this);
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void getBook(String id) {
        compositeDisposable.add(api.getBook(id)
                .subscribeOn(Schedulers.computation())
                .map(booksResponse -> booksResponse)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bookResponse -> view.showBook(bookResponse), throwable -> view.error(throwable)));
    }

    @Override
    public void setView(BookDetailView view) {
        this.view=view;
    }

    @Override
    public void dropView() {
        this.view=null;
    }
}

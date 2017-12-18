package r3pi.android.books.ui.screens.books;

import android.content.Context;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import r3pi.android.books.app.BooksApplication;
import r3pi.android.books.model.Book;
import r3pi.android.books.network.GoogleBooksApi;

/**
 * Created by konpach on 15/12/2017.
 */

public class BooksPresenterImpl implements BooksPresenter {

    private BooksView view;

    protected CompositeDisposable compositeDisposable;

    @Inject
    GoogleBooksApi api;

    public BooksPresenterImpl(Context context) {
        ((BooksApplication)context).getAppComponent().inject(this);
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void getBooks(String queryString, int page,int maxResults) {
        compositeDisposable.add(api.getBooks(queryString, page,maxResults)
                .subscribeOn(Schedulers.computation())
                .map(booksResponse -> booksResponse.getBooks())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<List<Book>>() {
                    @Override
                    public void onNext(List<Book> booksResponse) {
                        if(view!=null)
                            view.showBooks(booksResponse);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        if(view!=null)
                            view.error(throwable);
                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }

    @Override
    public void setView(BooksView view) {
        this.view=view;
    }

    @Override
    public void dropView() {
        this.view=null;
    }
}

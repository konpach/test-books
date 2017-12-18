package r3pi.android.books.ui.screens.bookDetail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import r3pi.android.books.R;
import r3pi.android.books.app.BooksApplication;
import r3pi.android.books.model.Book;
import r3pi.android.books.ui.screens.books.BooksActivity;
import r3pi.android.books.ui.screens.books.BooksPresenter;

public class BookDetailActivity extends AppCompatActivity implements BookDetailView {

    private static final String TAG=BooksActivity.class.getSimpleName();
    private final static int ITEMS_PER_PAGE=15;
    private Book book;

    @Inject
    BookDetailPresenter presenter;

    @BindView(R.id.text_view_book_details)
    TextView text_view_book_details;

    @BindView(R.id.text_view_title)
    TextView text_view_title;

    @BindView(R.id.text_view_publisher)
    TextView text_view_publisher;

    @BindView(R.id.image_view_thumb)
    ImageView image_view_thumb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((BooksApplication)getApplication()).getAppComponent().inject(this);
        ButterKnife.bind(this);
        presenter.setView(this);
        book=(Book)getIntent().getExtras().get("book");
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(book!=null)
        presenter.getBook(book.getId());
    }

    @Override
    public void showBook(Book book) {
        text_view_title.setText(book.getVolumeInfo().getTitle());
        text_view_book_details.setText(book.getVolumeInfo().getDescription());
        text_view_book_details.setText(book.toString());
    }

    @Override
    public void error(Throwable e) {

    }

    @Override
    public void error() {

    }

    @Override
    public void showErrorMessage() {

    }
}

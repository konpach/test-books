package r3pi.android.books.ui.screens.books;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import r3pi.android.books.R;
import r3pi.android.books.app.BooksApplication;
import r3pi.android.books.model.Book;
import r3pi.android.books.ui.base.OnBottomReachedListener;
import r3pi.android.books.ui.screens.bookDetail.BookDetailActivity;

public class BooksActivity extends AppCompatActivity implements BooksView{


    private static final String TAG=BooksActivity.class.getSimpleName();
    private final static int ITEMS_PER_PAGE=15;

    @Inject
    BooksPresenter presenter;

    @BindView(R.id.recycler_view_books)
    RecyclerView booksRecyclerView;

    @BindView(R.id.search_view)
    SearchView searchView;

    private BooksAdapter booksAdapter;
    private StringBuilder searchQuery;
    private int page=0;
    private List<Book> books=new ArrayList<Book>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        renderView();
        init();
        attachEventsToView();
    }

    private  void  renderView(){
        setContentView(R.layout.activity_books);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ((BooksApplication)getApplication()).getAppComponent().inject(this);
        ButterKnife.bind(this);
        presenter.setView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.dropView();
    }

    private  void  init(){
        booksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private  void  attachEventsToView(){
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                page=0;
                searchQuery=new StringBuilder(newText);
                presenter.getBooks(searchQuery.toString(),page,ITEMS_PER_PAGE);
                return false;
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_book, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showBooks(List<Book> booksFetched) {
        if(page>0)
        {
            addBooks(booksFetched);
            return;
        }
        books=booksFetched;
        booksAdapter=new BooksAdapter(books,BooksActivity.this);
        booksAdapter.setOnItemClickListener(item -> startActivity(new Intent(BooksActivity.this,BookDetailActivity.class).putExtra("book",item)));
        booksAdapter.setOnBottomReachedListener(new OnBottomReachedListener() {
            @Override
            public void onBottomReached(int position) {
                presenter.getBooks(searchQuery.toString(),page++,ITEMS_PER_PAGE);
                Toast.makeText(BooksActivity.this,R.string.msg_fetching_more_books,Toast.LENGTH_SHORT).show();
            }
        });
        booksRecyclerView.setAdapter(booksAdapter);
        booksRecyclerView.getAdapter().notifyDataSetChanged();
    }

    public void addBooks(List<Book> booksFetched) {
        this.books.addAll(booksFetched);
        booksAdapter.addItems(books);
        booksAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("page_key",page);
        outState.putString("searchQuery_key",searchQuery.toString());
        outState.putParcelableArrayList("books_key", (ArrayList<? extends Parcelable>) books);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState!=null){
            searchQuery = new StringBuilder(savedInstanceState.getString("searchQuery_key"));
            page=savedInstanceState.getInt("page_key");
        }
    }

    @Override
    public void error(Throwable e) {
        //TODO handle throwable
    }

    @Override
    public void error() {
        //TODO handle error
    }

    @Override
    public void showErrorMessage() {
        //TODO handle error message
    }
}

package r3pi.android.books.network;

import io.reactivex.Observable;
import r3pi.android.books.model.Book;
import r3pi.android.books.model.BooksVolumesResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by konpach on 15/12/2017.
 */

public interface GoogleBooksApi {

    @GET("volumes")
    Observable<BooksVolumesResponse> getBooks(@Query("q") String searchTerm, @Query("startIndex") int startIndex, @Query("maxResults") int maxResults);

    @GET("volumes/{id}")
    Observable<Book> getBook(@Path("id") String id);
}

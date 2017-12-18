package r3pi.android.books.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by konpach on 15/12/2017.
 */

public class BooksVolumesResponse {

    @SerializedName("kind")
    private String kind;

    @SerializedName("totalItems")
    private int totalItems;

    @SerializedName("items")
    private List<Book> books = new ArrayList<>();

    public int getTotalItems() {
        return totalItems;
    }

    public List<Book> getBooks() {
        return books;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }


}

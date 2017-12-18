package r3pi.android.books.ui.screens.books;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import r3pi.android.books.R;
import r3pi.android.books.model.Book;
import r3pi.android.books.ui.base.OnBottomReachedListener;

/**
 * Created by konpach on 15/12/2017.
 */

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.AdapterViewHolder> {

    private OnItemClickListener onItemClickListener;
    private List<Book> items;
    private Context context;
    private OnBottomReachedListener onBottomReachedListener;

    BooksAdapter(List<Book> items, Context context) {
        this.items = items;
        this.context=context;
    }

    public void setOnBottomReachedListener(OnBottomReachedListener onBottomReachedListener){

        this.onBottomReachedListener = onBottomReachedListener;
    }

    public void setItems(List<Book> items){
        this.items=items;
    }
    public void addItems(List<Book> items){
        this.items.addAll(items);
    }
    @Override
    public AdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        AdapterViewHolder item= new AdapterViewHolder(inflater.inflate(R.layout.row_books, parent, false));
        return item;
    }

    @Override
    public void onBindViewHolder(AdapterViewHolder holder, int position) {
        Book item=items.get(position);
        holder.getText_view_title().setText(item.getVolumeInfo().getTitle());
        holder.getText_view_publisher().setText(item.getVolumeInfo().getPublisher());
        if(item.getVolumeInfo().getImageLinks()!=null)
            Picasso.with(context).load(item.getVolumeInfo().getImageLinks().getThumbnail()).into(holder.getImage_view_thumb());
        if (position == items.size() - 1){
            onBottomReachedListener.onBottomReached(position);
        }
    }

    public interface OnItemClickListener{
        void onItemClick(Book item);
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    public class AdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener   {

        private TextView text_view_title,text_view_publisher;
        private ImageView image_view_thumb;

        public AdapterViewHolder(View view) {
            super(view);
            text_view_title = (TextView) view.findViewById(R.id.text_view_title);
            text_view_publisher= (TextView) view.findViewById(R.id.text_view_publisher);
            image_view_thumb= (ImageView) view.findViewById(R.id.image_view_thumb);
            view.setOnClickListener(this);
        }

        public ImageView getImage_view_thumb() {
            return image_view_thumb;
        }

        public void setImage_view_thumb(ImageView image_view_thumb) {
            this.image_view_thumb = image_view_thumb;
        }

        public TextView getText_view_title() {
            return text_view_title;
        }

        public void setText_view_title(TextView text_view_title) {
            this.text_view_title = text_view_title;
        }

        public TextView getText_view_publisher() {
            return text_view_publisher;
        }

        public void setText_view_publisher(TextView text_view_publisher) {
            this.text_view_publisher = text_view_publisher;
        }

        @Override
        public void onClick(View v) {
            Book item = items.get(getAdapterPosition());
            onItemClickListener.onItemClick(item);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

}

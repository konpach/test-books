package r3pi.android.books.di;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import r3pi.android.books.BuildConfig;
import r3pi.android.books.network.GoogleBooksApi;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by konpach on 15/12/2017.
 */

@Module
public class GoogleBooksNetwork {

    private static final String NAME_BASE_URL = "BOOKS_BASE_URL";

    @Provides
    @Named(NAME_BASE_URL)
    String provideBaseUrlString() {
        return BuildConfig.BOOKS_BASE_URL;}

    @Provides
    @Singleton
    Converter.Factory provideGsonConverter() {
        return GsonConverterFactory.create();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Converter.Factory converter, @Named(NAME_BASE_URL) String baseUrl) {
        Retrofit.Builder retrofitBuilder= new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(converter);
        if(BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            retrofitBuilder.client(new OkHttpClient.Builder().addInterceptor(interceptor).build());
        }
        return retrofitBuilder.build();
    }

    @Provides
    @Singleton
    GoogleBooksApi provideAPI(Retrofit retrofit) {
        return retrofit.create(GoogleBooksApi.class);
    }
}

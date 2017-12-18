package r3pi.android.books.app;

import android.app.Application;

import r3pi.android.books.di.AppComponent;
import r3pi.android.books.di.AppModule;
import r3pi.android.books.di.DaggerAppComponent;

/**
 * Created by konpach on 15/12/2017.
 */

public class BooksApplication extends Application {
    private AppComponent appComponent;

    public AppComponent getAppComponent() {
        return appComponent;
    }

    protected AppComponent initDagger(BooksApplication application) {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(application))
                .build();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = initDagger(this);
    }
}

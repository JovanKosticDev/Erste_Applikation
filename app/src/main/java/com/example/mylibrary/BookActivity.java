package com.example.mylibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {

    public static final String BOOK_ID_KEY = "bookId";

    private TextView txtBookName, txtAuthor, txtPages, txtDescription;
    private Button btnAddToWantToRead, btnAddToAlreadyRead, btnAddToCurrentlyReading, btnAddToFavorite;
    private ImageView bookImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        initViews();

//        String longDescription = "Morbi commodo velit in diam porta, sit amet cursus sem porttitor. Vestibulum in risus sed dui convallis mattis egestas vel turpis. Vivamus a ornare elit. Vestibulum malesuada rutrum augue. Aenean leo dui, rhoncus id enim id, euismod pellentesque dui. Cras ac purus a nulla mattis pharetra nec in lorem. Aenean eget purus nec nunc venenatis lobortis eget in nulla. Aenean mollis nunc diam, vel interdum augue hendrerit at. In maximus non risus at auctor. Aliquam erat volutpat. Praesent id auctor erat. Integer posuere tincidunt rutrum. Donec ullamcorper augue sapien. Nam luctus, enim ultricies aliquam luctus, diam mi scelerisque odio, id convallis felis metus ut mi. Mauris et magna vel lectus posuere condimentum ac at dolor. Nunc mollis nec ligula eu ultrices. ";
//
//        // TODO: Get the data from recycler view in here
//        Book book = new Book(1, "Die Drei ???", "Robert Arthur", 128, "https://bilder.buecher.de/produkte/26/26359/26359455z.jpg",
//                "Bereits im ersten Band Panik im Paradies machen die drei berühmten Detektive ihrem Namen alle Ehre.",
//                longDescription);

        Intent intent = getIntent();
        if(null != intent){
            int bookId = intent.getIntExtra(BOOK_ID_KEY, -1);
            if(bookId != -1){
                Book incomingBook = Utils.getInstance().getBookById(bookId);
                if(null != incomingBook){
                    setData(incomingBook);

                    handleAlreadyRead(incomingBook);
                    handleWantToReadBooks(incomingBook);
                    handleCurrentlyReadingBooks(incomingBook);
                    handleFavoriteBooks(incomingBook);
                }
            }
        }
    }

    private void handleFavoriteBooks(final Book book){
        ArrayList<Book> favoriteBooks = Utils.getInstance().getFavoriteBooks();

        boolean existInFavoriteBooks = false;

        for(Book b : favoriteBooks){
            if(b.getId() == book.getId()){
                existInFavoriteBooks = true;
            }
        }

        if(existInFavoriteBooks){
            btnAddToFavorite.setEnabled(false);
        }else{
            btnAddToFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Utils.getInstance().addToFavorite(book)){
                        Toast.makeText(BookActivity.this, "Book added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, FavoriteActivity.class);
                        startActivity(intent);

                    }else{
                        Toast.makeText(BookActivity.this, "Something wrong happend, try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handleCurrentlyReadingBooks(final Book book) {
        ArrayList<Book> currentlyReadingBooks = Utils.getInstance().getCurrentlyReadingBooks();

        boolean existinCurrentlyReadingBooks = false;

        for(Book b : currentlyReadingBooks){
            if(b.getId() == book.getId()){
                existinCurrentlyReadingBooks = true;
            }
        }

        if(existinCurrentlyReadingBooks){
            btnAddToCurrentlyReading.setEnabled(false);
        }else{
            btnAddToCurrentlyReading.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Utils.getInstance().addToCurrentlyReading(book)){
                        Toast.makeText(BookActivity.this, "Book added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, CurrentlyReadingActivity.class);
                        startActivity(intent);

                    }else{
                        Toast.makeText(BookActivity.this, "Something wrong happend, try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    private void handleWantToReadBooks(final Book book){
        ArrayList<Book> wantToReadBooks = Utils.getInstance().getWantToReadBooks();

        boolean existInWantToReadBooks = false;

        for(Book b : wantToReadBooks){
            if(b.getId() == book.getId()){
                existInWantToReadBooks = true;
            }
        }

        if(existInWantToReadBooks){
            btnAddToWantToRead.setEnabled(false);
        }else{
            btnAddToWantToRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Utils.getInstance().addToWantToRead(book)){
                        Toast.makeText(BookActivity.this, "Book added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, WantToReadActivity.class);
                        startActivity(intent);

                    }else{
                        Toast.makeText(BookActivity.this, "Something wrong happend, try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }


    /**
     * Aktiviere und deaktiviere den Button,
     * Füge das Buch in der AlreadyReadBook ArrayListe hinzu
     * @param book
     */
    private void handleAlreadyRead(Book book){
        ArrayList<Book> alreadyReadBooks = Utils.getInstance().getAlreadyReadBooks();

        boolean existInAlreadyReadBooks = false;

        for(Book b : alreadyReadBooks){
            if(b.getId() == book.getId()){
                existInAlreadyReadBooks = true;
            }
        }

        if(existInAlreadyReadBooks){
            btnAddToAlreadyRead.setEnabled(false);
        }else{
            btnAddToAlreadyRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Utils.getInstance().addToAlreadyRead(book)){
                        Toast.makeText(BookActivity.this, "Book added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, AlreadyReadBookActivity.class);
                        startActivity(intent);

                    }else{
                        Toast.makeText(BookActivity.this, "Something wrong happend, try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void setData(Book book){
        txtBookName.setText(book.getName());
        txtAuthor.setText(book.getAuthor());
        txtPages.setText(String.valueOf(book.getPages()));
        txtDescription.setText(book.getLongDesc());
        Glide.with(this)
                .asBitmap().load(book.getImageURL())
                .into(bookImage);
    }

    private void initViews() {
        txtAuthor = findViewById(R.id.nameAuthorTxt);
        txtBookName = findViewById(R.id.txtNameBook);
        txtPages = findViewById(R.id.txtPagesNumber);
        txtDescription = findViewById(R.id.txtLongDescription);

        btnAddToAlreadyRead = findViewById(R.id.btnAddToAlreadyRead);
        btnAddToCurrentlyReading = findViewById(R.id.btnAddToCurrentlyReading);
        btnAddToFavorite = findViewById(R.id.btnAddToFavourites);
        btnAddToWantToRead = findViewById(R.id.btnAddToWantToRead);

        bookImage = findViewById(R.id.imgBookCover);
    }
}
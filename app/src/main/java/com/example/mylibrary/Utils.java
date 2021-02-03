package com.example.mylibrary;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Utils {

    private static Utils instance;

    private static ArrayList<Book> allBooks;
    private static ArrayList<Book> alreadyReadBooks;
    private static ArrayList<Book> wantToReadBooks;
    private static ArrayList<Book> currentlyReadingBooks;
    private static ArrayList<Book> favoriteBooks;

    private Utils() {
        if(null == allBooks){
            allBooks = new ArrayList<>();
            initData();
        }
        if(null == alreadyReadBooks){
            alreadyReadBooks = new ArrayList<>();
        }
        if(null == wantToReadBooks){
            wantToReadBooks = new ArrayList<>();
        }
        if(null == currentlyReadingBooks){
            currentlyReadingBooks = new ArrayList<>();
        }
        if(null == favoriteBooks){
            favoriteBooks = new ArrayList<>();
        }
    }

    private void initData() {
        // TODO: Add initial Data

        allBooks.add(new Book(1, "Die Drei ???", "Robert Arthur", 128, "https://bilder.buecher.de/produkte/26/26359/26359455z.jpg",
                "Bereits im ersten Band Panik im Paradies machen die drei berühmten Detektive ihrem Namen alle Ehre.",
                "Long Description"));

        allBooks.add(new Book(2, "Alex Rider Scorpia", "Anthony Horowitz", 352, "https://images-na.ssl-images-amazon.com/images/I/71oa22bIIrL.jpg",
                "Für seine Mitschüler ist die Fahrt nach Venedig eine ganz normale Klassenfahrt. Für Alex Rider ist es die Chance, endlich mehr über seinen Vater zu erfahren.",
                "Long Description"));
    }

    public static Utils getInstance() {
        if(null != instance){
            return instance;
        }else{
            instance = new Utils();
            return instance;
        }
    }

    public static ArrayList<Book> getAllBooks() {
        return allBooks;
    }

    public static ArrayList<Book> getAlreadyReadBooks() {
        return alreadyReadBooks;
    }

    public static ArrayList<Book> getWantToReadBooks() {
        return wantToReadBooks;
    }

    public static ArrayList<Book> getCurrentlyReadingBooks() {
        return currentlyReadingBooks;
    }

    public static ArrayList<Book> getFavoriteBooks() {
        return favoriteBooks;
    }

    public Book getBookById(int id){
        for (Book b : allBooks){
            if(b.getId() == id){
                return b;
            }
        }

        return null;
    }

    public boolean addToAlreadyRead(Book book){
        return alreadyReadBooks.add(book);
    }

    public boolean addToWantToRead(Book book){
        return wantToReadBooks.add(book);
    }

    public boolean addToFavorite(Book book){
        return favoriteBooks.add(book);
    }

    public boolean addToCurrentlyReading(Book book){
        return currentlyReadingBooks.add(book);
    }

}

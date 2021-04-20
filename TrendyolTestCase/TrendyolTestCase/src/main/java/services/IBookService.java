package services;

import models.Book;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import java.util.List;

public interface IBookService {
    @GET("api/books")
    Call<List<Book>> getBooks();

    @GET("api/books/{book_id}")
    Call<Book> getBookById(@Path("book_id") int id);

    @POST("api/books")
    Call<Book> insertBook(@Body Book book);
}

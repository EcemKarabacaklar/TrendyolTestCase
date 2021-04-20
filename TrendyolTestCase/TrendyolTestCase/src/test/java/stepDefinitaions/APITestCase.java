package stepDefinitaions;

import models.Book;
import org.junit.Assert;
import retrofit2.Call;
import retrofit2.Response;
import services.IBookService;
import services.RetrofitClient;

import java.util.List;

public class APITestCase {
    public synchronized Response<List<Book>> getBooks() {
        Call<List<Book>> call = RetrofitClient.getInstance().create(IBookService.class).getBooks();
        try {
            Response<List<Book>> response = call.execute();
            return isValid(response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public synchronized Response<Book> insertBook(Book book) {
        Call<Book> call = RetrofitClient.getInstance().create(IBookService.class).insertBook(book);
        try {
            Response<Book> response = call.execute();
            return isValid(response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public synchronized Response<Book> getBook(int bookId) {
        Call<Book> call = RetrofitClient.getInstance().create(IBookService.class).getBookById(bookId);
        try {
            Response<Book> response = call.execute();
            return isValid(response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Response isValid(Response response) {
        if (response.code() == 500) {
            Assert.fail("HTTP 500 Internal Server Error.");
        } else {
            return response;
        }
        return null;
    }
}

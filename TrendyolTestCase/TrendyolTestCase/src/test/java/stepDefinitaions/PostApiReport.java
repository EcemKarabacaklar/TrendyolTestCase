package stepDefinitaions;

import com.google.gson.Gson;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.Book;
import models.Error;
import org.junit.Assert;
import retrofit2.Response;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class PostApiReport extends APITestCase {
    private String answer = "";
    private Book book = null;
    private Response<Book> response = null;
    private Response<Book> insertedBook;
    private Response<Book> getInsertedBook;

    @Given("Title Is Not Null AND Author Is Null")
    public void title_ıs_not_null_and_author_ıs_null() {
        book = new Book();
        book.setTitle("title");
        answer = "author";
    }

    @Given("Title Is Null AND Author Is Not Null")
    public void title_ıs_null_and_author_ıs_not_null() {
        book = new Book();
        book.setAuthor("author");
        answer = "title";
    }

    @Given("Title Is Not Empty AND Author Is Empty")
    public void title_ıs_not_empty_and_author_ıs_empty() {
        book = new Book();
        book.setTitle("title");
        book.setAuthor("");
        answer = "author";
    }

    @Given("Title Is Empty AND Author Is Not Empty")
    public void title_ıs_empty_and_author_ıs_not_empty() {
        book = new Book();
        book.setTitle("");
        book.setAuthor("author");
        answer = "title";
    }

    @Given("Title Is Not Empty AND Author Is Not Empty")
    public void title_ıs_not_empty_and_author_ıs_not_empty() {
        book = new Book();
        book.setTitle("title");
        book.setAuthor("author");
        answer = "";
    }

    @When("Insert Book Request")
    public void ınsert_book_request() {
        response = insertBook(book);
    }

    @Then("Field {string} is required")
    public void field_is_required(String string) {
        if (response != null && response.code() == 400) {
            try {
                Error error = new Gson().fromJson(response.errorBody().string(), Error.class);
                assertEquals(string, error.getError());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Assert.fail("Unexpected Error");
        }
    }

    @Then("Field {string} cannot be empty")
    public void field_cannot_be_empty(String string) {
        if (response != null && response.code() == 400) {
            try {
                Error error = new Gson().fromJson(response.errorBody().string(), Error.class);
                assertEquals(string, error.getError());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Assert.fail("Unexpected Error");
        }
    }

    @Then("Success")
    public void success() {
        if (response != null && response.isSuccessful()) {
            Assert.assertTrue("Succesfully added.", response.body() != null);
        } else {
            Assert.fail("Unexpected Error");
        }
    }

    @Given("Valid Book Model")
    public void valid_book_model() {
        book = new Book();
        book.setAuthor("ecem");
        book.setTitle("ecem book");
    }

    @When("Insert Book Request AND Get Book Request By Id")
    public void ınsert_book_request_and_get_book_request_by_ıd() {
        insertedBook = insertBook(book);
        if (insertedBook != null && insertedBook.isSuccessful()) {
            getInsertedBook = getBook(insertedBook.body().getId());
        }
    }

    @Then("IsSame")
    public void ıs_same() {
        Assert.assertEquals("is same", true, insertedBook.body().equals(getInsertedBook.body()));
    }
}

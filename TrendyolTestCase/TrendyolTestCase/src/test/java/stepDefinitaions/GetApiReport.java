package stepDefinitaions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.Book;
import org.junit.Assert;
import retrofit2.Response;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class GetApiReport extends APITestCase {
    private Response<List<Book>> response;
    private String answer = "";
    private int bookId;
    private Response<Book> getBookByIdResponse;

    @Given("Get Book Request")
    public void get_book_request() {
        response = getBooks();
    }

    @When("Is Repository Empty")
    public void ıs_repository_empty() {
        if (response != null && response.isSuccessful()) {
            answer = response.body().isEmpty() ? "Yes" : "No";
        }
    }

    @Then("{string} Repository Is Empty")
    public void repository_ıs_empty(String string) {
        assertEquals(string, answer);
    }

    @Given("Book True")
    public void book_true() {
        bookId = 1;
    }

    @Given("Book False")
    public void book_false() {
        bookId = 666666;
    }

    @When("Get Book Request By Id")
    public void get_book_request_by_ıd() {
        getBookByIdResponse = getBook(bookId);
    }

    @Then("Book found")
    public void book_found() {
        if (getBookByIdResponse != null && getBookByIdResponse.isSuccessful()) {
            Assert.assertTrue("Succesfully founded.", getBookByIdResponse.body() != null);
        } else {
            Assert.fail("Unexpected Error");
        }
    }

    @Then("Book not found")
    public void book_not_found() {
        if (getBookByIdResponse != null && getBookByIdResponse.code() == 404) {
            Assert.assertTrue("Not founded.", getBookByIdResponse.body() == null);
        } else {
            Assert.fail("Unexpected Error");
        }
    }
}

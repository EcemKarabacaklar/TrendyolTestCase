package models;

import gherkin.deps.com.google.gson.annotations.SerializedName;

public class Error {
    @SerializedName("error")
    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}

package org.example.userCounting;

public class UserData {

    private final String registrationDate;
    private int requestCount;

    public UserData(String registrationDate, int requestCount) {
        this.registrationDate = registrationDate;
        this.requestCount = requestCount;
    }

    public void incrementRequestCount() {
        requestCount++;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public int getRequestCount() {
        return requestCount;
    }
}

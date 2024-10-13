package de.ait_tr.shop.exception_handling;

import java.util.Objects;

/**
 * @author Sergey Bugaenko
 * {@code @date} 28.08.2024
 */

public class Response {
    private String message;

    public Response(String message) {
        this.message = message;
    }


    @Override
    public String toString() {
        return "Response: message - " + message;
     }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Response response = (Response) o;
        return Objects.equals(message, response.message);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(message);
    }
}

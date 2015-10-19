package io.funraise.we.Request;

public class ApiRequest {
     public static class MalformedRequestException extends Exception {
         private static final long serialVersionUID = 1L;
         public MalformedRequestException(String message) { super(message); }
     }
}

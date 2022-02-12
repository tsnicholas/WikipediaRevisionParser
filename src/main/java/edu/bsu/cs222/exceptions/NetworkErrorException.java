package edu.bsu.cs222.exceptions;

import java.io.IOException;

public class NetworkErrorException extends IOException {
    public NetworkErrorException(String message, Throwable err) {
        super(message, err);
    }
}

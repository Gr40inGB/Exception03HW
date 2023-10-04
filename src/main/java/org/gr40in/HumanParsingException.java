package org.gr40in;

public class HumanParsingException extends RuntimeException {
    public HumanParsingException(String message) {
        super("ERROR! " + message);
    }
}

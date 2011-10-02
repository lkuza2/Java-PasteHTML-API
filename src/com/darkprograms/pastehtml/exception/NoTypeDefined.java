package com.darkprograms.pastehtml.exception;

/**
 * Exception class for a no type defined error.
 * Thrown when a PasteObject has no type
 */
public class NoTypeDefined extends Exception {

    /**
     * Constructor
     *
     * @param msg Message to "throw"
     */
    public NoTypeDefined(String msg) {
        super(msg);
    }

}

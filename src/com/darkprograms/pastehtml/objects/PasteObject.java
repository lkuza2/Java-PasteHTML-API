package com.darkprograms.pastehtml.objects;

import com.darkprograms.pastehtml.exception.NoTypeDefined;
import com.darkprograms.pastehtml.network.PostPaste;
import com.darkprograms.pastehtml.types.PasteHTML;
import com.darkprograms.pastehtml.types.PasteMD;
import com.darkprograms.pastehtml.types.PasteText;
import com.darkprograms.pastehtml.types.PasteType;

import java.io.File;

/**
 * PasteObject class.  Extends PostPaste for network methods.
 */
public class PasteObject extends PostPaste {


    /**
     * Type text
     */
    private String typeText = null;

    /**
     * PasteObject constructor
     *
     * @param type Type you want this object to be
     */
    public PasteObject(PasteType type) {

        setTypeClass(type);
    }

    /**
     * PasteObject constructor.  You must specify the type with setType
     */
    public PasteObject() {

    }


    /**
     * Determines if a class is equal to another class
     *
     * @param classObject Class
     * @param type        PasteType subclass to test
     * @return True if equal, false otherwise
     */
    private boolean determineClass(Class classObject, PasteType type) {
        return type.getClass().equals(classObject);
    }

    /**
     * Sets the type of this object
     *
     * @param type Sublcass of PasteType
     */
    private void setTypeClass(PasteType type) {
        if (determineClass(PasteText.class, type)) {
            setTypeText("text");
        } else if (determineClass(PasteHTML.class, type)) {
            setTypeText("HTML");
        } else if (determineClass(PasteMD.class, type)) {
            setTypeText("Markdown");
        } else {
            setTypeText("unknown");
        }
    }

    private String determineTypeForPost() {
        if (getTypeText().equals("text")) {
            return "txt";
        } else if (getTypeText().equals("HTML")) {
            return "html";
        } else if (getTypeText().equals("Markdown")) {
            return "mrk";
        } else {
            return null;
        }
    }

    /**
     * Gets the string representation of the current type of this object
     *
     * @return Returns a string.  "test", "HTML", and "Markdown" are currently returned
     */
    public String getTypeText() {
        return typeText;
    }

    /**
     * Sets the type text for this object
     *
     * @param typeText Type text.  "test", "HTML", and "Markdown"
     */
    private void setTypeText(String typeText) {
        this.typeText = typeText;
    }

    /**
     * Sets this objects type.  PasteText, PasteMD, and PasteHTML extend of off PasteType
     *
     * @param type Class that is the type you want this object to be
     */
    public void setType(PasteType type) {
        setTypeClass(type);
    }

    /**
     * Paste data and return url as response.
     *
     * @param data Data to post to this object
     * @return Returns String representation of the URL of the posted object
     */
    public String pasteData(String data) throws NoTypeDefined {
        if (getTypeText() == null || getTypeText().equals("unknown")) {
            throw new NoTypeDefined("No Type has been defined or an unknown type has been defined for this object");
        }
        return postPaste(data, determineTypeForPost());
    }

    /**
     * Paste a file and return url as response.
     *
     * @param file File to post to this object
     * @return Returns String representation of the URL of the posted object
     */
    public String pasteFile(File file) throws NoTypeDefined {
        if (getTypeText() == null || getTypeText().equals("unknown")) {
            throw new NoTypeDefined("No Type has been defined or an unknown type has been defined for this object");
        }
        return postPaste(file, determineTypeForPost());
    }


}

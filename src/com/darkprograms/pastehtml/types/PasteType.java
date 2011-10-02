package com.darkprograms.pastehtml.types;

/**
 * PasteType class that all Types extend of off
 */
public class PasteType {

    /**
     * Protected constructor
     */
    protected PasteType() {

    }

    /**
     * Type for posting the object as HTML
     */
    public static final PasteHTML TYPE_HTML = new PasteHTML();
    /**
     * Type for posting the object as text
     */
    public static final PasteText TYPE_TEXT = new PasteText();
    /**
     * Type for posting the object as Markdown
     */
    public static final PasteMD TYPE_MARKDOWN = new PasteMD();


}

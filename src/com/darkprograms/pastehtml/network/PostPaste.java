package com.darkprograms.pastehtml.network;

import com.darkprograms.pastehtml.constants.Constants;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Class that performs the network side handling of the actual paste
 * Classes that need these methods will extend of this class.
 */
public class PostPaste {


    /**
     * Constructor. Protected
     */
    protected PostPaste() {

    }

    /**
     * Posts a paste and retrieves the URL as a response.
     *
     * @param data     Data to post
     * @param postType Post type, txt,html, or mrk
     * @return Returns URL of posted data.
     */
    protected String postPaste(String data, String postType) {
        try {
            URL url = new URL(Constants.MAIN_URL + postType + Constants.RESULT_ARG);
            String postData = URLEncoder.encode("txt", "UTF-8") + "=" + URLEncoder.encode(data, "UTF-8");

            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);

            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(postData.getBytes("UTF-8"));
            outputStream.close();

            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String response = br.readLine();

            br.close();

            return response;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * Posts a paste from data from a file and retrieves the URL as a response.
     * Data is buffered, so any size of file can be posted.
     *
     * @param file     File to post
     * @param postType Post type, txt,html, or mrk
     * @return Returns URL of posted data.
     */
    protected String postPaste(File file, String postType) {
        try {
            URL url = new URL(Constants.MAIN_URL + postType + Constants.RESULT_ARG);
            String param = URLEncoder.encode("txt", "UTF-8") + "=";

            InputStream inputStream = new FileInputStream(file);


            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);

            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(param.getBytes("UTF-8"));

            byte[] bytes = new byte[256];

            int read;

            while ((read = inputStream.read(bytes, 0, 256)) != -1) {
                outputStream.write(bytes, 0, read);
            }


            outputStream.close();

            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String response = br.readLine();

            br.close();

            return response;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }


}

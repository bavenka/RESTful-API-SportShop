package com.test.model.api;

import java.awt.*;
import java.io.IOException;
import java.net.URI;

/**
 * Created by Pavel on 02.12.2016.
 */
public class Vk {

    private static final String REDIRECT_URI = "https://oauth.vk.com/blank.html";
    private static final String API_VERSION = "5.60";
    private static final String DISPLAY = "page";
    private static final String APP_ID = "5754721";
    private static final String PERMISSIONS = "notify";


    private static final String AUTH_URL = "https://oauth.vk.com/authorize"
            + "?client_id={APP_ID}"
            + "&scope={PERMISSIONS}"
            + "&redirect_uri={REDIRECT_URI}"
            + "&display={DISPLAY}"
            + "&v={API_VERSION}"
            + "&response_type=token";

    public static void auth() throws IOException {
        String reqUrl = AUTH_URL
                .replace("{APP_ID}", APP_ID)
                .replace("{PERMISSIONS}", PERMISSIONS)
                .replace("{REDIRECT_URI}", REDIRECT_URI)
                .replace("{DISPLAY}", DISPLAY)
                .replace("{API_VERSION}", API_VERSION);
        try {
            if (Desktop.isDesktopSupported()){
                Desktop desktop = Desktop.getDesktop();
                if (desktop.isSupported(Desktop.Action.BROWSE)) {
                    desktop.browse(URI.create(reqUrl));
                }
            }
        } catch (IOException e) {
            e.fillInStackTrace();
        }
    }

}

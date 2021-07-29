package edu.labIV.mail;

public class RegisterMail extends Mail {

    private static String subject = "Devs Social Network - Registro";
    private static String USER_REPLACEMENT = "$USER$";
    private static String URL_REPLACEMENT = "$URL$";
    
    private static boolean isInitialized;
    private static String htmlContent;

    private static void init() {
        htmlContent = getHTMLContent("res/mail/register.html");
        isInitialized = true;
    }

    public static String getBody(String userName, String url) {
        if (!isInitialized) {
            init();
        }

        String html = htmlContent.replace(USER_REPLACEMENT, userName);
        return html.replace(URL_REPLACEMENT, url);
    }

    public static String getSubject() {
        return subject;
    }

}

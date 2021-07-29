package edu.labIV.mail;

public class BlockedAccountMail extends Mail{

    private static String subject = "Devs Social Network - Cuenta Bloqueada";
    private static boolean isInitialized;
    private static String htmlContent;

    private static void init() {
        htmlContent = getHTMLContent("res/mail/blocked.html");
        isInitialized = true;
    }

    public static String getBody() {
        if (!isInitialized) {
            init();
        }

        return htmlContent;
    }

    public static String getSubject() {
        return subject;
    }

}

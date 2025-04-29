package com.gag.model;

public class ModelMessageRapport {
    private String yourName;
    private String yourSubject;
    private String yourEmail;
    private String yourPhone;
    private String yourMessage;

    public ModelMessageRapport() {
    }

    public ModelMessageRapport(String yourName, String yourSubject, String yourEmail, String yourPhone, String yourMessage) {
        this.yourName = yourName;
        this.yourSubject = yourSubject;
        this.yourEmail = yourEmail;
        this.yourPhone = yourPhone;
        this.yourMessage = yourMessage;
    }

    public String getYourName() {
        return yourName;
    }

    public void setYourName(String yourName) {
        this.yourName = yourName;
    }

    public String getYourSubject() {
        return yourSubject;
    }

    public void setYourSubject(String yourSubject) {
        this.yourSubject = yourSubject;
    }

    public String getYourEmail() {
        return yourEmail;
    }

    public void setYourEmail(String yourEmail) {
        this.yourEmail = yourEmail;
    }

    public String getYourPhone() {
        return yourPhone;
    }

    public void setYourPhone(String yourPhone) {
        this.yourPhone = yourPhone;
    }

    public String getYourMessage() {
        return yourMessage;
    }

    public void setYourMessage(String yourMessage) {
        this.yourMessage = yourMessage;
    }
}

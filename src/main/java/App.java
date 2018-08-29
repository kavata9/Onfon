package main.java;

import java.util.HashMap;


public class App {
    public static void main(String[] args_)
    {
        // Specify your login credentials
        String username = "ONFON";
        String apiKey   = "dd8a7ca108d6b00376ff76a17093877f8f90566c4aecb41d65f8accc76e317d1";
        // Specify the numbers that you want to send to in a comma-separated list
        // Please ensure you include the country code (+254 for Kenya in this case)
        String recipients = "+254724971796";
        // And of course we want our recipients to know what we really do
        String message = "MPENZI";
        // Create a new instance of our awesome gateway class
        AfricasTalkingGateway gateway  = new AfricasTalkingGateway(username, apiKey);
        /*************************************************************************************
            NOTE: If connecting to the sandbox:
            1. Use "sandbox" as the username
            2. Use the apiKey generated from your sandbox application
                https://account.africastalking.com/apps/sandbox/settings/key
            3. Add the "sandbox" flag to the constructor
            AfricasTalkingGateway gateway = new AfricasTalkingGateway(username, apiKey, "sandbox");
        **************************************************************************************/
        // Thats it, hit send and we'll take care of the rest. Any errors will
        // be captured in the Exception class below
        try {
            JSONArray results = gateway.sendMessage(recipients, message);
            for( int i = 0; i < results.length(); ++i ) {
                JSONObject result = results.getJSONObject(i);
                System.out.print(result.getString("status") + ","); // status is either "Success" or "error message"
                System.out.print(result.getString("statusCode") + ",");
                System.out.print(result.getString("number") + ",");
                System.out.print(result.getString("messageId") + ",");
                System.out.println(result.getString("cost"));
            }
        } catch (Exception e) {
            System.out.println("Encountered an error while sending " + e.getMessage());
        }
    }
}
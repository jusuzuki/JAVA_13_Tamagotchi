import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.Date;
import java.util.TimerTask;
import java.util.Timer;


public class App {
  public static Timer myAppTimer = new Timer();
  static String statusMessage;
  static Integer foodLevel = 10; //ideally this should come from our Tamagotchi object.

  //get the start time to have something to compare to.
  static Long creationTime = System.currentTimeMillis();

  public static void main(String[] args) {

        staticFileLocation("/public");
        String layout = "templates/layout.vtl";
        Tamagotchi myTamagotchi;

        get("/", (request, response) -> {
          HashMap<String, Object> model = new HashMap<String, Object>();
          model.put("username", request.session().attribute("username"));

          //set initial welcome message
          String inValidUser= "Hi. Please set your Tamagotchi's name below.";
          model.put("inValidUser", inValidUser);


          model.put("template", "templates/input.vtl");
          return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        post("/output", (request, response) -> {

            //get the name of the tamagotchi.
            String inputtedUsername = request.queryParams("userInput");
            HashMap<String, Object> model = new HashMap<String, Object>();

            //if username is not null or consists of whitespace let the user proceed
            if (inputtedUsername != null && inputtedUsername.trim().length() > 0){
              request.session().attribute("inputtedUsername", inputtedUsername);
              model.put("inputtedUsername", inputtedUsername);
              String inValidUser= "You are ready to play!";
              model.put("inValidUser", inValidUser);
            }

            else{ //let the user know they need to set a username.
              String inValidUser= "You need to set your Tamagotchi's name first!";
              model.put("inValidUser", inValidUser);

            }
              //output
              model.put("template", "templates/output.vtl");
              return new ModelAndView(model, layout);
            }, new VelocityTemplateEngine());

        post("/play", (request, response) -> {
          HashMap<String, Object> model = new HashMap<String, Object>();

            //set up an arraylist for the messages.This may be null the first time the page is loaded.
              ArrayList<String> messages = request.session().attribute("messages");
                    Long currentTime = System.currentTimeMillis();

                    //if the messages arraylist is null create it.
                    if (messages == null) {
                    messages = new ArrayList<String>();
                    //store the messages in a session so they are accessible after we reload the page.
                    request.session().attribute("messages", messages);
                    }

                    //if more than 5 seconds have passed create a statusmessage
                    if (currentTime - creationTime > 5000) {
                      statusMessage = "Feed Me!";

                      //add the message to the arraylist
                      messages.add(statusMessage);
                    }

                      //calculate the level of food available depending on the size of the array.
                      foodLevel = foodLevel - messages.size();
                      System.out.println("I have: " + foodLevel);
                      model.put("foodLevel", foodLevel);



            model.put("messages", messages);


            //storing the name of the tamagotchi so it is accessible from different pages.
            String inputtedUsername = request.queryParams("tamagotchiName");
            request.session().attribute("inputtedUsername", inputtedUsername);
            model.put("inputtedUsername", inputtedUsername);


          model.put("template", "templates/play.vtl");
          return new ModelAndView(model, "templates/play.vtl");
        }, new VelocityTemplateEngine());

      } //close main method

    } //close main class

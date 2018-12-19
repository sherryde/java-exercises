package com.example.hellospring.controllers;

import com.sun.java.util.jar.pack.Instruction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.support.AbstractMultipartHttpServletRequest;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@Controller //to tell springboot we wanted this class to be recognized as the controller class

// set requestMapping at the controller level when working with larger applications.
//@RequestMapping("hello") // every request into this controller to preceded by the segment hello
// group controllers by functionality with hello as the starting path.
// It is handy to group controllers by functionality in an application.
// Frequently put all controller methods in a controller class live below a certain URL segment.

public class HelloController {  //one controller configured to the @Controller
    //each handler was configured with the request Mapping annotation to tell
    // springboot which URL/path our handlers should be receiving
    @RequestMapping(value = "") //
    @ResponseBody               // handler to specify we are returning plan text (directly from controller)
    public String index(HttpServletRequest request) { // Gets the data out of the request
        /* indexed localhost:8080 path is now changed with @RequestMapping("hello") added
         localhost:8080/hello with the global addition of @RequestMapping("hello")
         */
        // local userName var = that is passed in the query string and match the key(name) in the parameter
        String name = request.getParameter("name"); //pulls date passed in the http server request query string

        // Provides a  Null SafeGuard
        if (name == null) {
            name = "World";
        }

        //return "Hello World!"; // instead of saying Hello World!
        return "Hello " + name + "!"; // Hello ?!
    }

    //Display Form for User - Method Get, input data
    // handler to be at /hello
    @RequestMapping(value = "hello", method = RequestMethod.GET)
    @ResponseBody
    //request handler
    public String helloForm() {
        //build a basic string with html and the form method to post
        ArrayList<String> language = new ArrayList<>();
            language.add("Hello");
            language.add("Bonjour");
            language.add("Hola");
            language.add("Cioa");
            language.add("Hallo");

        //model.addAttribute(languages);

        return "hello"; //the request is sent to /hello because there was not action URL specified
    }


    //handle request submitted from form - Method Post, to receive and process data
    // logic in a separate handler
    @RequestMapping(value = "hello", method = RequestMethod.POST)
    @ResponseBody
    //access the request object as input parameter
    public String helloPost(HttpServletRequest request) {
        String name = request.getParameter("name");
        String language = request.getParameter("language");
        String greeting;
        switch(language){
            case "English": greeting = "Hello";
                break;
            case "Spanish": greeting = "Hola";
                break;
            case "French": greeting = "Bonjour";
                break;
            case "Italian": greeting = "Ciao";
                break;
            case "German": greeting = "Hallo";
                break;
            default:
                greeting ="Hi";

        }
        //return "Hello " + name;


        return "<span style='color:red'>" + greeting + " " + name + "</span>" +
                ""; // " + writeCookie() + "
    }

    //https://docs.oracle.com/javaee/6/api/javax/servlet/SessionTrackingMode.html#URL
    //https://viralpatel.net/blogs/spring-mvc-cookie-example/
    //http://www.kscodes.com/spring-mvc/spring-mvc-cookie-handling/
    @RequestMapping(value = "hello/cookie")
    @ResponseBody
    private static String writeCookie(@CookieValue(value = "hitCounter", defaultValue = "0") Long hitCounter, HttpServletResponse response){
        hitCounter++;
        Cookie cookie = new Cookie("hitCounter", hitCounter.toString());
        response.addCookie(cookie);
        System.out.println("cookie.getValue() = " + cookie.getValue());
        return cookie.getValue().toString();
    }




    //name should be a parameter for the handler Method
    @RequestMapping(value = "hello/{name}")
    @ResponseBody
    //get the value of the variable in the path- URL segment
    public String helloUrlSegment(@PathVariable String name) {
        // String name = request.getParameter("name");
        // String language = request.getParameter("language");
        // return language + " " + name;
        return "Hello " + name;
    }


    @RequestMapping(value = "goodbye") // path localhost:8080/goodbye will be change to
    // localhost:8080/hello/goodbye with the addition of @RequestMapping("hello")
    //@ResponseBody // take off ResponseBody (text)
    public String goodbye() {
        return "redirect:/";  // redirect status code to the index

    }
}

// localhost:8080
// localhost:8080/goodbye
// controllers/routing/parameter passing in Spring Boot

/* TODO Query Parameters: localhost:8080/?name=Easton (Data Transfer by GET)
GET the parameters that are passed into the server via the query string: public String index()
to do that you have to add input parameter to the index method: public String index(HttpServletRequest request)
Now there is an object in the Method. Spring will pass this query.
Spring is actually calling the index method when a proper request is inputted to this handler.
Spring is going to give my Method this request.
The request is an object that represents that's in the Http server request that the server receives.
The request is received and then GET's data out (of the block: String name = ).
*/
/*TODO Display Form for User (Data Transfer by GET) & (Data Transfer by POST)
A Display form that will allow the user to submit data.
A new request handler that returns a string of HTML.
Another handler that will handle POST requests and access the request object.
*/
/*TODO URL segment localhost:8080/hello/chris (data passed into handler instead of part of route)
A new handler that will access data via a URL segment:
http://outbottle.com/spring-3-web-mvc-friendly-url-using-requestmapping-variable-uri/
*/
/* TODO Redirect
    return "redirect:/"; //to index
*/



/* IntelliJ
* keyboard cheatsheet shortcuts ==> https://www.jetbrains.com/help/idea/2017.1/keyboard-shortcuts-you-cannot-miss.html
* alt/option + enter/return => provides suggested solution


Command 	                    Description
Ctrl + / 	                    This command is used to Comment Line
Ctrl + Shift + / 	            It is used to Comment Block
Ctrl + D 	                    It is to Duplicate Line
Alt + Shift + Down/Up 	        To Move line
Ctrl + Alt + Shift + Down/Up 	To Move Whole statement
Ctrl + – 	                    It is to Collapse block
Ctrl + + 	                    It is used to Expand block
Ctrl + Shift + +/- 	            When we need to Collapse/ Expand whole file
Ctrl + A 	                    Need to Select Whole life
Ctrl + W 	                    When to Extend the selection to the next block
Ctrl + Shift + W 	            It is used to Shrinks selection
Alt + J 	                    When to Select next occurrence
Alt + Shift + J 	            When to Deselect next occurrence
Ctrl + shift + F7 	            Helps in Highlight all usages in file
Ctrl +Alt + Shift + J 	        It is to Select all occurrence in the file
Ctrl + Space 	                Suggest
Ctrl + Space X2 	            Getting the Deeper level suggestion
Ctrl + shift + space 	        Helps in getting the List of matching suggestion
Ctrl + shift + space x2 	    A suggestion in return statement
Ctrl + Shift + enter 	        Complete statement
Shift + F6 	                    Rename
Ctrl + Alt + V 	                Extract variable or field
Ctrl + Alt + M 	                Encapsulate code in a new method
Ctrl + Alt + C 	                Extract selected expression in a context
Ctrl + Alt + P 	                Extract selected expression in a parameter
Ctrl + Alt + T 	                Surround selected code

There are some other commands available on code assistance and navigation, please find below:

Command 	                    DESCRIPTION
Ctrl + P 	                    It is used to see the method signature
Ctrl + J 	                    It is used insert live template
Ctrl + Q 	                    It is to see the method documentation
Ctrl + Alt + L 	                Used to Reformat code
Ctrl + shift + i 	            It is to see the definition
F2 	                            It helps in finding the next highlighted error in a file
Ctrl + F1 	                    It is used to see the description error
Alt + Enter 	                It provides suggested solution
Ctrl + N 	                    When it is required to search class
Ctrl + Shift +N 	            It is used to search file
F4 	                            It helps in Jump to source
Ctrl + B 	                    Jump to declaration
Ctrl + Alt + B 	                Jump to implementation
Ctrl + F12 	                    It is used to see a file structure


Tips and Tricks of using IntelliJ Cheat Sheet for commands: –

    IntelliJ IDEA comes with two very useful preconfigured quick-lists that are as below

    Refactor This (Ctrl + Alt + Shift + T on Windows/Linux and Ctrl + T on Mac OS)
    VCS Operations (Alt+` on Windows/Linux and Ctrl + V on Mac OS).

    With help of intelliJ idea resize of tool windows can be done with command Ctrl + shift + arrows on windows/ Linux and shift + cmd + arrows on Mac OS.
    To maximize the window, the following command can be used ‘Win + up’ on windows and ‘Ctrl + cmd + =’ on Mac OS
    To move to the next project window, the following command can be used ‘Ctrl + Alt + J’ on Windows or Linux and Cmd + ‘ on Mac OS.
    In IntelliJ, there are many keyboard settings. Smart keys can be used to toggle automatic insertion of pair brackets, quotes, and curly braces and enable camel humps. Smart keys can be enabled through Settings -> Editor -> general -> Smart keys.
    There is one important thing make sure that chosen keymap does not conflict with OS. The following are the Ubuntu commands:

    Shade Window, assigned to Ctrl + Alt + S, clashes with Settings
    Lock Screen, assigned to Ctrl + Alt + L, clashes with Reformat Code
    Launch Terminal, assigned to Ctrl + Alt + T, clashes with Surround With
    Switch to Workspace, assigned to Ctrl + Alt + Arrows, clashes with in-IDE navigation
    Move Window, assigned to Alt + F7, clashes with Find Usages
    Resize Window, assigned to Alt + F8, clashes with Evaluate Expression
    Virtual Consoles, assigned to Ctrl + Alt + F12, clashes with Commit

https://www.educba.com/intellij-cheat-sheet/
* */
package org.solent.spring.map.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import java.util.Optional;

import org.solent.spring.map.model.User;
import org.solent.spring.map.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


/**
 *
 * @author Camponio
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/test")
    public String sayHello() {
        return "Hello, World! This is users page.";
    }
    
    @Autowired
    UserRepository userRepository;

    @Operation(summary = "Get a list of users")
    @RequestMapping("/get")
    public Iterable<User> list() {
        return userRepository.findAll();
    }

    @Operation(summary = "Get a specific map point by id")
    @RequestMapping("/get/{id}")
    public User getById(@Parameter(description = "id if point to be retreived") @PathVariable(value = "id") long id){
        Optional<User> mpo = userRepository.findById(id);
        return (mpo.isEmpty()) ? null : mpo.get();
    }
    
    
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView createUser(@RequestParam String username,@RequestParam String role, @RequestParam String password) {
    // Create a new User entity
    User newUser = new User();
    newUser.setUsername(username);
    newUser.setRole(role);
    newUser.setPassword(password);

    // Save the new user to the database using the UserRepository
    userRepository.save(newUser);

    // Redirect to a confirmation page
    ModelAndView modelAndView = new ModelAndView("/UserResult");
    modelAndView.addObject("message", username);
    return modelAndView; // Return the ModelAndView
}
    
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ModelAndView deleteUser(@RequestParam String id) {
    ModelAndView modelAndView = new ModelAndView("UserResult");

    try {
        // Parse the id to long if needed
        long userId = Long.parseLong(id);

        // Perform user deletion logic using userId
        // For demonstration purposes, we'll assume you have a deleteUserById method in your repository
        userRepository.deleteById(userId);

        // Set the "deleteMessage" attribute with the deleted user ID
        modelAndView.addObject("deleteMessage", userId);
    } catch (NumberFormatException e) {
        // Handle the case where id is not a valid long
        modelAndView.addObject("deleteError", "Invalid user ID format. Please provide a valid numeric user ID.");
    } catch (Exception e) {
        // Handle other exceptions
        modelAndView.addObject("deleteError", "Error deleting user. Please try again.");
        e.printStackTrace(); // Log the exception (you might want to use a logging framework)
    }

    return modelAndView;
}
    

    // Define more API endpoints as needed
}

 
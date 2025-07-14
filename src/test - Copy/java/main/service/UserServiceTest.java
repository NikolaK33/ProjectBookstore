package main.service;

import main.model.Bookstore;
import main.model.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class UserServiceTest {

    private Bookstore bookstore;
    private UserService userService;

    @BeforeMethod
    public void setUp() {
        bookstore = new Bookstore("Test Bookstore");
        userService = new UserService(bookstore);
    }

    @Test
    public void testAddUser() {
        User user = new User("Nikola", "nikola33", "password123", "nikola@gmail.com");
        userService.addUser(user);

        List<User> users = bookstore.getUsers();

        Assert.assertEquals(users.size(), 1);
        Assert.assertEquals(users.get(0).getUsername(), "nikola33");
        Assert.assertEquals(users.get(0).getEmail(), "nikola@gmail.com");
    }

    @Test
    public void testRemoveUser() {
        User user1 = new User("Nikola", "nikola33", "password123", "nikola@gmail.com");
        User user2 = new User("Sanja", "sanja22", "sifra456", "sanja@gmail.com");

        userService.addUser(user1);
        userService.addUser(user2);

        userService.removeUser("nikola33");

        List<User> users = bookstore.getUsers();

        Assert.assertEquals(users.size(), 1);
        Assert.assertEquals(users.get(0).getUsername(), "sanja22");
    }
}

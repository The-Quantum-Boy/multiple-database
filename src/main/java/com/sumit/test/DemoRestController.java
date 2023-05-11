package com.sumit.test;

import com.sumit.db1.entities.User;
import com.sumit.db1.repositories.UserRepo;
import com.sumit.db2.entities.Book;
import com.sumit.db2.repositories.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DemoRestController {
    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/geUsers")
    public List<User> getUser(){
        return  userRepo.findAll();
    }

    @GetMapping("/getBooks")
    public List<Book> getBooks(){
        return bookRepo.findAll();
    }


}

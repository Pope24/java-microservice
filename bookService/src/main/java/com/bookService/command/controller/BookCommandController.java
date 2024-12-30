package com.bookService.command.controller;

import com.bookService.command.command.CreateBookCommand;
import com.bookService.command.model.BookRequestModel;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/books")
public class BookCommandController {
    @Autowired
    private CommandGateway commandGateway;
    @PostMapping
    public String createBook(@RequestBody BookRequestModel request) {
        CreateBookCommand command = new CreateBookCommand(UUID.randomUUID().toString(), request.getName(), request.getAuthor(), true);
        return commandGateway.sendAndWait(command);
    }
}

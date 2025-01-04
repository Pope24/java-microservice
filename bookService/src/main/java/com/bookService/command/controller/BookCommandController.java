package com.bookService.command.controller;

import com.bookService.command.command.CreateBookCommand;
import com.bookService.command.command.DeleteBookCommand;
import com.bookService.command.command.UpdateBookCommand;
import com.bookService.command.model.BookRequestModel;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @PutMapping("/{bookId}")
    public String updateBook(@RequestBody BookRequestModel request, @PathVariable String bookId) {
        UpdateBookCommand command = new UpdateBookCommand(bookId, request.getName(), request.getAuthor(), request.getIsReady());
        return commandGateway.sendAndWait(command);
    }
    @DeleteMapping("/{bookId}")
    public String deleteBook(@PathVariable String bookId) {
        DeleteBookCommand command = new DeleteBookCommand(bookId);
        return commandGateway.sendAndWait(command);
    }
}

package com.bookService.command.event;

import com.bookService.command.data.Book;
import com.bookService.command.data.IBookRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class BookEventHandler {
    @Autowired
    private IBookRepository bookRepository;
    @EventHandler
    @Transactional
    public void on(BookCreatedEvent event) {
        Book book = new Book(event.getName(), event.getAuthor(), event.getIsReady());
        bookRepository.save(book);
    }
}

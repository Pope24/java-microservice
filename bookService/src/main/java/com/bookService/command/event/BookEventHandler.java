package com.bookService.command.event;

import com.bookService.command.data.Book;
import com.bookService.command.data.IBookRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class BookEventHandler {
    @Autowired
    private IBookRepository bookRepository;

    @EventHandler
    @Transactional
    public void on(BookCreatedEvent event) {
        Book book = new Book(event.getId(), event.getName(), event.getAuthor(), event.getIsReady());
        bookRepository.save(book);
    }

    @EventHandler
    @Transactional
    public void on(BookUpdatedEvent event) {
        Optional<Book> book = bookRepository.findById(event.getId());
        if (book.isPresent()) {
            Book updateBook = book.get();
            updateBook.setName(event.getName());
            updateBook.setAuthor(event.getAuthor());
            updateBook.setIsReady(event.getIsReady());
            bookRepository.save(updateBook);
        }
    }

    @EventHandler
    @Transactional
    public void on(BookDeletedEvent event) {
        Optional<Book> book = bookRepository.findById(event.getId());
        book.ifPresent(value -> bookRepository.delete(value));
    }
}

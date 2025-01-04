package com.bookService.command.aggregate;

import com.bookService.command.command.CreateBookCommand;
import com.bookService.command.command.DeleteBookCommand;
import com.bookService.command.command.UpdateBookCommand;
import com.bookService.command.data.Book;
import com.bookService.command.event.BookCreatedEvent;
import com.bookService.command.event.BookDeletedEvent;
import com.bookService.command.event.BookUpdatedEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
@Getter
@Setter
@NoArgsConstructor
public class BookAggregate {
    @AggregateIdentifier
    private String id;
    private String name;
    private String author;
    private Boolean isReady;
    @CommandHandler
    public BookAggregate(CreateBookCommand cmd) {
        BookCreatedEvent event = new BookCreatedEvent();
        BeanUtils.copyProperties(cmd, event);
        AggregateLifecycle.apply(event);
    }
    @CommandHandler
    public void update(UpdateBookCommand cmd) {
        BookUpdatedEvent event = new BookUpdatedEvent();
        BeanUtils.copyProperties(cmd, event);
        AggregateLifecycle.apply(event);
    }
    @CommandHandler
    public void delete(DeleteBookCommand cmd) {
        BookDeletedEvent event = new BookDeletedEvent();
        BeanUtils.copyProperties(cmd, event);
        AggregateLifecycle.apply(event);
    }
    @EventSourcingHandler
    public void on(BookCreatedEvent event) {
        this.id = event.getId();
        this.name = event.getName();
        this.author = event.getAuthor();
        this.isReady = event.getIsReady();
    }
    @EventSourcingHandler
    public void on(BookUpdatedEvent event) {
        this.id = event.getId();
        this.name = event.getName();
        this.author = event.getAuthor();
        this.isReady = event.getIsReady();
    }
    @EventSourcingHandler
    public void on(BookDeletedEvent event) {
        this.id = event.getId();
    }
}

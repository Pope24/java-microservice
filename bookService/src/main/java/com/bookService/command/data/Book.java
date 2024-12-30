package com.bookService.command.data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "books")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String author;
    private Boolean isReady;
    @Version
    private Long version = 0L;
    public Book(String name, String author, Boolean isReady) {
        this.name = name;
        this.author = author;
        this.isReady = isReady;
    }
}

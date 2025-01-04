package com.bookService.query.projection;

import com.bookService.command.data.Book;
import com.bookService.command.data.IBookRepository;
import com.bookService.query.model.BookResponseModel;
import com.bookService.query.queries.GetAllBookQuery;
import com.bookService.query.queries.GetDetailBookQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookProjection {
    @Autowired
    private IBookRepository bookRepository;

    @QueryHandler
    private List<BookResponseModel> handleGetAllBook(GetAllBookQuery query) {
        List<BookResponseModel> res = new ArrayList<>();
        bookRepository.findAll().forEach(book -> {
            BookResponseModel bookResponse = new BookResponseModel();
            BeanUtils.copyProperties(book, bookResponse);
            res.add(bookResponse);
        });
        return res;
    }

    @QueryHandler
    private BookResponseModel handleGetDetailBook(GetDetailBookQuery query) throws Exception {
        BookResponseModel response = new BookResponseModel();
        Book book = bookRepository.findById(query.getId()).orElseThrow(() ->
                new Exception("Can not found Book Entity with ID: " + query.getId())
        );
        BeanUtils.copyProperties(book, response);
        return response;
    }
}

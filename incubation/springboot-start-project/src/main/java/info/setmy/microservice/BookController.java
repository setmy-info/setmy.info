package info.setmy.microservice;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

/* http://localhost:8080/graphiql?path=/graphql

POST (Content-Type: ation/json; Accept: application/json) : http://localhost:8080/graphql
BODY: {"query":"query bookDetails {\n  bookById(id: \"book-1\") {\n    id\n    name\n    pageCount\n    author {\n      id\n      firstName\n      lastName\n    }\n  }\n}","operationName":"bookDetails"}

 */
@Controller
class BookController {

    @QueryMapping
    public Book bookById(@Argument String id) {
        return Book.getById(id);
    }

    @SchemaMapping
    public Author author(Book book) {
        return Author.getById(book.authorId());
    }
}

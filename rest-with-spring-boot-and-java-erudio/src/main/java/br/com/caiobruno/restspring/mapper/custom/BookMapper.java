package br.com.caiobruno.restspring.mapper.custom;

import br.com.caiobruno.restspring.data.vo.v1.BookVO;
import br.com.caiobruno.restspring.model.Book;

import java.util.Date;

public class BookMapper {


    public Book convertVoToEntity(BookVO bookVO) {

        Book entity = new Book();

        entity.setId(bookVO.getKey());
        entity.setAuthor(bookVO.getAuthor());
        //entity.setLauchDate(new Date());
        entity.setPrice(bookVO.getPrice());
        entity.setTitle(bookVO.getTitle());


        return entity;

    }


    public BookVO convertEntityToVo(Book entity) {

        BookVO bookVO = new BookVO();

        bookVO.setKey(entity.getId());
        bookVO.setAuthor(entity.getAuthor());
        bookVO.setLauchDate(new Date());
        bookVO.setPrice(entity.getPrice());
        bookVO.setTitle(entity.getTitle());

        return bookVO;

    }
}

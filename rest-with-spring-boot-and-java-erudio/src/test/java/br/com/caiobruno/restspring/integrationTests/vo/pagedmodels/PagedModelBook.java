package br.com.caiobruno.restspring.integrationTests.vo.pagedmodels;


import br.com.caiobruno.restspring.integrationTests.vo.BookVO;
import br.com.caiobruno.restspring.integrationTests.vo.PersonVO;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement
public class PagedModelBook {

    @XmlElement
    private List<BookVO> content;

    public PagedModelBook(List<BookVO> content) {
        this.content = content;
    }

    public PagedModelBook() {
    }

    public List<BookVO> getContent() {
        return content;
    }

    public void setContent(List<BookVO> content) {
        this.content = content;
    }
}

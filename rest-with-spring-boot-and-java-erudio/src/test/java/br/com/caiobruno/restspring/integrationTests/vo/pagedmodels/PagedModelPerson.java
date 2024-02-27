package br.com.caiobruno.restspring.integrationTests.vo.pagedmodels;


import br.com.caiobruno.restspring.integrationTests.vo.PersonVO;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement
public class PagedModelPerson {

    @XmlElement
    private List<PersonVO> content;

    public PagedModelPerson() {
    }

    public PagedModelPerson(List<PersonVO> content) {
        this.content = content;
    }

    public List<PersonVO> getContent() {
        return content;
    }

    public void setContent(List<PersonVO> content) {
        this.content = content;
    }
}

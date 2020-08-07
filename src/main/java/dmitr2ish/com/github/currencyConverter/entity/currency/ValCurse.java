package dmitr2ish.com.github.currencyConverter.entity.currency;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "cc_curse")
public class ValCurse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Date date;

    @OneToMany
    private List<Currency> currencyList;

    public ValCurse(String name, Date date, List<Currency> currencyList) {
        this.name = name;
        this.date = date;
        this.currencyList = currencyList;
    }

    public ValCurse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Currency> getCurrencyList() {
        return currencyList;
    }

    public void setCurrencyList(List<Currency> currencyList) {
        this.currencyList = currencyList;
    }
}

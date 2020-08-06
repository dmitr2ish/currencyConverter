package dmitr2ish.com.github.currencyConverter.entity.currency;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import java.util.Date;


@Entity
@Table(name = "cc_curse")
public class ValCurse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Date date;

    public ValCurse(String name, Date date) {
        this.name = name;
        this.date = date;
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
}

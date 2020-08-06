package dmitr2ish.com.github.currencyConverter.performanceXml;

import dmitr2ish.com.github.currencyConverter.entity.currency.Currency;

import javax.xml.bind.annotation.*;
import java.util.Date;
import java.util.List;

@XmlRootElement(name = "ValCurs")
@XmlAccessorType(XmlAccessType.NONE)
public class ValCurseXml {

    @XmlAttribute(name="name")
    private String name;

    @XmlAttribute(name="Date")
    private String date;

    public ValCurseXml(String name, String date) {
        this.name = name;
        this.date = date;
    }

    public ValCurseXml() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

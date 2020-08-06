package dmitr2ish.com.github.currencyConverter.performanceXml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement(name = "Valute")
@XmlAccessorType(XmlAccessType.NONE)
public class CurrencyXml {

    @XmlAttribute(name="ID")
    private String id;

    @XmlAttribute(name="NumCode")
    private Long numCode;

    @XmlAttribute(name="CharCode")
    private String charCode;

    @XmlAttribute(name="Nominal")
    private Integer nominal;

    @XmlAttribute(name="Name")
    private String name;

    @XmlAttribute(name="Value")
    private BigDecimal value;

    public CurrencyXml(String id, Long numCode, String charCode, Integer nominal, String name, BigDecimal value) {
        this.id = id;
        this.numCode = numCode;
        this.charCode = charCode;
        this.nominal = nominal;
        this.name = name;
        this.value = value;
    }

    public CurrencyXml() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getNumCode() {
        return numCode;
    }

    public void setNumCode(Long numCode) {
        this.numCode = numCode;
    }

    public String getCharCode() {
        return charCode;
    }

    public void setCharCode(String charCode) {
        this.charCode = charCode;
    }

    public Integer getNominal() {
        return nominal;
    }

    public void setNominal(Integer nominal) {
        this.nominal = nominal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}

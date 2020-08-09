package dmitr2ish.com.github.currencyConverter.entity.currency;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "cc_currency")
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String idInXml;

    private Long numCode;

    private String charCode;

    private Integer nominal;

    private String name;

    private BigDecimal value;

    @ManyToOne
    @JsonIgnore
    private Course course;

    public Currency() {
    }

    public Currency(String idInXml, Long numCode, String charCode, Integer nominal, String name, BigDecimal value, Course course) {
        this.idInXml = idInXml;
        this.numCode = numCode;
        this.charCode = charCode;
        this.nominal = nominal;
        this.name = name;
        this.value = value;
        this.course = course;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdInXml() {
        return idInXml;
    }

    public void setIdInXml(String idInXml) {
        this.idInXml = idInXml;
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

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}

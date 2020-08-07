package dmitr2ish.com.github.currencyConverter.entity.currency;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String initialNameCurrency;
    private String targetNameCurrency;
    private BigDecimal initialValueCurrency;
    private BigDecimal targetValueCurrency;
    private Date date;

    public History(String initialNameCurrency, String targetNameCurrency, BigDecimal initialValueCurrency, BigDecimal targetValueCurrency, Date date) {
        this.initialNameCurrency = initialNameCurrency;
        this.targetNameCurrency = targetNameCurrency;
        this.initialValueCurrency = initialValueCurrency;
        this.targetValueCurrency = targetValueCurrency;
        this.date = date;
    }

    public History() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInitialNameCurrency() {
        return initialNameCurrency;
    }

    public void setInitialNameCurrency(String initialNameCurrency) {
        this.initialNameCurrency = initialNameCurrency;
    }

    public String getTargetNameCurrency() {
        return targetNameCurrency;
    }

    public void setTargetNameCurrency(String targetNameCurrency) {
        this.targetNameCurrency = targetNameCurrency;
    }

    public BigDecimal getInitialValueCurrency() {
        return initialValueCurrency;
    }

    public void setInitialValueCurrency(BigDecimal initialValueCurrency) {
        this.initialValueCurrency = initialValueCurrency;
    }

    public BigDecimal getTargetValueCurrency() {
        return targetValueCurrency;
    }

    public void setTargetValueCurrency(BigDecimal targetValueCurrency) {
        this.targetValueCurrency = targetValueCurrency;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

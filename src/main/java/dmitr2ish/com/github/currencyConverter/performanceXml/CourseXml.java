package dmitr2ish.com.github.currencyConverter.performanceXml;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "ValCurs")
@XmlAccessorType(XmlAccessType.NONE)
public class CourseXml {

    @XmlAttribute(name="name")
    private String name;

    @XmlAttribute(name="Date")
    private String date;

    public CourseXml(String name, String date) {
        this.name = name;
        this.date = date;
    }

    public CourseXml() {
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

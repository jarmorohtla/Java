
package eu.x_road.fie_andmed;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SelfEmployedPerson complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SelfEmployedPerson"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="isikukood" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="algus" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="lopp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SelfEmployedPerson", propOrder = {
    "isikukood",
    "algus",
    "lopp"
})
public class SelfEmployedPerson {

    @XmlElement(required = true)
    protected String isikukood;
    @XmlElement(required = true)
    protected String algus;
    protected String lopp;

    /**
     * Gets the value of the isikukood property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsikukood() {
        return isikukood;
    }

    /**
     * Sets the value of the isikukood property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsikukood(String value) {
        this.isikukood = value;
    }

    /**
     * Gets the value of the algus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAlgus() {
        return algus;
    }

    /**
     * Sets the value of the algus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlgus(String value) {
        this.algus = value;
    }

    /**
     * Gets the value of the lopp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLopp() {
        return lopp;
    }

    /**
     * Sets the value of the lopp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLopp(String value) {
        this.lopp = value;
    }

}

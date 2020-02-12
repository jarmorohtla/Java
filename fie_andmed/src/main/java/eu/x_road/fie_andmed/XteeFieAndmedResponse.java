
package eu.x_road.fie_andmed;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="vastus" type="{http://fie_andmed.x-road.eu}XteeFieAndmedResponseType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "vastus"
})
@XmlRootElement(name = "xteeFieAndmedResponse")
public class XteeFieAndmedResponse {

    @XmlElement(required = true)
    protected XteeFieAndmedResponseType vastus;

    /**
     * Gets the value of the vastus property.
     * 
     * @return
     *     possible object is
     *     {@link XteeFieAndmedResponseType }
     *     
     */
    public XteeFieAndmedResponseType getVastus() {
        return vastus;
    }

    /**
     * Sets the value of the vastus property.
     * 
     * @param value
     *     allowed object is
     *     {@link XteeFieAndmedResponseType }
     *     
     */
    public void setVastus(XteeFieAndmedResponseType value) {
        this.vastus = value;
    }

}


package eu.x_road.fie_andmed;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for XteeFieAndmedResponseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="XteeFieAndmedResponseType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="kokku" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="lisainfo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="fieJada" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="item" type="{http://fie_andmed.x-road.eu}SelfEmployedPerson" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "XteeFieAndmedResponseType", propOrder = {
    "kokku",
    "lisainfo",
    "fieJada"
})
public class XteeFieAndmedResponseType {

    protected String kokku;
    protected String lisainfo;
    protected XteeFieAndmedResponseType.FieJada fieJada;

    /**
     * Gets the value of the kokku property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKokku() {
        return kokku;
    }

    /**
     * Sets the value of the kokku property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKokku(String value) {
        this.kokku = value;
    }

    /**
     * Gets the value of the lisainfo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLisainfo() {
        return lisainfo;
    }

    /**
     * Sets the value of the lisainfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLisainfo(String value) {
        this.lisainfo = value;
    }

    /**
     * Gets the value of the fieJada property.
     * 
     * @return
     *     possible object is
     *     {@link XteeFieAndmedResponseType.FieJada }
     *     
     */
    public XteeFieAndmedResponseType.FieJada getFieJada() {
        return fieJada;
    }

    /**
     * Sets the value of the fieJada property.
     * 
     * @param value
     *     allowed object is
     *     {@link XteeFieAndmedResponseType.FieJada }
     *     
     */
    public void setFieJada(XteeFieAndmedResponseType.FieJada value) {
        this.fieJada = value;
    }


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
     *         &lt;element name="item" type="{http://fie_andmed.x-road.eu}SelfEmployedPerson" maxOccurs="unbounded" minOccurs="0"/&gt;
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
        "item"
    })
    public static class FieJada {

        protected List<SelfEmployedPerson> item;

        /**
         * Gets the value of the item property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the item property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getItem().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link SelfEmployedPerson }
         * 
         * 
         */
        public List<SelfEmployedPerson> getItem() {
            if (item == null) {
                item = new ArrayList<SelfEmployedPerson>();
            }
            return this.item;
        }

    }

}

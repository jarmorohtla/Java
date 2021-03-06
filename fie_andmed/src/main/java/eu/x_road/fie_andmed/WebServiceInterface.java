package eu.x_road.fie_andmed;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.2.0
 * 2020-02-12T14:40:12.999+02:00
 * Generated source version: 3.2.0
 * 
 */
@WebService(targetNamespace = "http://fie_andmed.x-road.eu", name = "webServiceInterface")
@XmlSeeAlso({eu.x_road.xsd.identifiers.ObjectFactory.class, eu.x_road.xsd.xroad.ObjectFactory.class, ObjectFactory.class})
public interface WebServiceInterface {

    /**
     * 
     *             
     */
    @WebMethod
    @RequestWrapper(localName = "xteeFieAndmed", targetNamespace = "http://fie_andmed.x-road.eu", className = "eu.x_road.fie_andmed.XteeFieAndmed")
    @ResponseWrapper(localName = "xteeFieAndmedResponse", targetNamespace = "http://fie_andmed.x-road.eu", className = "eu.x_road.fie_andmed.XteeFieAndmedResponse")
    @WebResult(name = "vastus", targetNamespace = "")
    public eu.x_road.fie_andmed.XteeFieAndmedResponseType xteeFieAndmed(
        @WebParam(name = "keha", targetNamespace = "")
        eu.x_road.fie_andmed.PersonalIdentityCode keha,
        @WebParam(mode = WebParam.Mode.INOUT, name = "client", targetNamespace = "http://x-road.eu/xsd/xroad.xsd", header = true)
        javax.xml.ws.Holder<eu.x_road.xsd.identifiers.XRoadClientIdentifierType> client,
        @WebParam(mode = WebParam.Mode.INOUT, name = "service", targetNamespace = "http://x-road.eu/xsd/xroad.xsd", header = true)
        javax.xml.ws.Holder<eu.x_road.xsd.identifiers.XRoadServiceIdentifierType> service,
        @WebParam(mode = WebParam.Mode.INOUT, name = "id", targetNamespace = "http://x-road.eu/xsd/xroad.xsd", header = true)
        javax.xml.ws.Holder<java.lang.String> id,
        @WebParam(mode = WebParam.Mode.INOUT, name = "protocolVersion", targetNamespace = "http://x-road.eu/xsd/xroad.xsd", header = true)
        javax.xml.ws.Holder<java.lang.String> protocolVersion,
        @WebParam(mode = WebParam.Mode.INOUT, name = "userId", targetNamespace = "http://x-road.eu/xsd/xroad.xsd", header = true)
        javax.xml.ws.Holder<java.lang.String> userId,
        @WebParam(mode = WebParam.Mode.INOUT, name = "issue", targetNamespace = "http://x-road.eu/xsd/xroad.xsd", header = true)
        javax.xml.ws.Holder<java.lang.String> issue
    );
}

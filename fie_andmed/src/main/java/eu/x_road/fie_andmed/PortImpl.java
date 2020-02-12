package eu.x_road.fie_andmed;

import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * Veebiteenus
 * 
 * @author Jarmo Rohtla
 * 
 */

@javax.jws.WebService(
		serviceName = "webService",
		portName = "port",
		targetNamespace = "http://fie_andmed.x-road.eu",
		wsdlLocation = "classpath:services.wsdl",
		endpointInterface = "eu.x_road.fie_andmed.WebServiceInterface")

public class PortImpl implements WebServiceInterface {

	private static final Logger LOG = Logger.getLogger(PortImpl.class.getName());

	/* (non-Javadoc)
	 * @see eu.x_road.fie_andmed.WebServiceInterface#xteeFieAndmed(eu.x_road.fie_andmed.PersonalIdentityCode keha, eu.x_road.xsd.identifiers.XRoadClientIdentifierType client, eu.x_road.xsd.identifiers.XRoadServiceIdentifierType service, java.lang.String id, java.lang.String protocolVersion, java.lang.String userId, java.lang.String issue)*
	 */
	public eu.x_road.fie_andmed.XteeFieAndmedResponseType xteeFieAndmed(eu.x_road.fie_andmed.PersonalIdentityCode keha, javax.xml.ws.Holder<eu.x_road.xsd.identifiers.XRoadClientIdentifierType> client, javax.xml.ws.Holder<eu.x_road.xsd.identifiers.XRoadServiceIdentifierType> service, javax.xml.ws.Holder<java.lang.String> id, javax.xml.ws.Holder<java.lang.String> protocolVersion, javax.xml.ws.Holder<java.lang.String> userId, javax.xml.ws.Holder<java.lang.String> issue) { 
		LOG.info("Executing operation xteeFieAndmed");
		System.out.println(keha);
		System.out.println(client.value);
		System.out.println(service.value);
		System.out.println(id.value);
		System.out.println(protocolVersion.value);
		System.out.println(userId.value);
		System.out.println(issue.value);
		try {
			XteeFieAndmedResponseType _return = new XteeFieAndmedResponseType();

			_return.setKokku("Kokku");
			_return.setLisainfo("Lisainfo");

			SelfEmployedPerson sep1 = new SelfEmployedPerson();
			sep1.setIsikukood(keha.getIsikukood());
			sep1.setAlgus("01.01.1981");
			sep1.setLopp("31.12.1990");

			SelfEmployedPerson sep2 = new SelfEmployedPerson();
			sep2.setIsikukood(keha.getIsikukood());
			sep2.setAlgus("01.01.1991");
			sep2.setLopp("31.12.2000");

			SelfEmployedPerson sep3 = new SelfEmployedPerson();
			sep3.setIsikukood(keha.getIsikukood());
			sep3.setAlgus("01.01.2001");
			sep3.setLopp("31.12.2010");

			XteeFieAndmedResponseType.FieJada fieJada = new XteeFieAndmedResponseType.FieJada();
			fieJada.getItem().add(sep1);
			fieJada.getItem().add(sep2);
			fieJada.getItem().add(sep3);
			_return.setFieJada(fieJada);
			return _return;
		} catch (java.lang.Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
	}

}

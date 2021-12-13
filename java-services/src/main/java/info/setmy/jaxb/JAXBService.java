package info.setmy.jaxb;

import info.setmy.exceptions.ParsingException;
import info.setmy.exceptions.RenderingException;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Marshaller.Listener;
import jakarta.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.xml.XMLConstants;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class JAXBService {

    private final Logger log = LogManager.getLogger(getClass());

    public <T> T parse(
            final String xmlFileName,
            final String xsdFileName,
            final Class<T> clazz) {
        try {
            final JAXBContext context = JAXBContext.newInstance(clazz);
            final Unmarshaller unmarshaller = context.createUnmarshaller();
            if (xsdFileName != null) {
                final Schema schema = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI).newSchema(new File(xsdFileName));
                unmarshaller.setSchema(schema);
            }
            return clazz.cast(unmarshaller.unmarshal(new File(xmlFileName)));
        } catch (JAXBException | SAXException ex) {
            throw new ParsingException(ex);
        }
    }

    public <T> void render(
            final PrintWriter writer,
            final Schema xsd,
            final String xsl,
            final String location,
            final T object) {
        try {
            final JAXBContext context = JAXBContext.newInstance(object.getClass());
            final StringWriter swriter = new StringWriter();
            final Marshaller marshaller = context.createMarshaller();
            Listener listener = null;
            marshaller.setListener(listener);
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
            if (xsd != null) {
                marshaller.setSchema(xsd);
            }
            swriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            if (location != null) {
                marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, location);
            }
            if (xsl != null) {
                swriter.write("<?xml-stylesheet type=\"text/xsl\" href=\"" + xsl + "\" ?>\n");
            }
            marshaller.marshal(object, swriter);
            String str = swriter.toString();
            writer.write(str);
        } catch (JAXBException ex) {
            throw new RenderingException(ex);
        }
    }

    public <T> void render(
            final PrintWriter writer,
            final String xsd,
            final String xsl,
            final String location,
            final T object) {
        try {
            if (xsd != null) {
                Schema schema = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI).newSchema(new File(xsd));
                render(writer, schema, xsl, location, object);
            } else {
                render(writer, (Schema) null, xsl, location, object);
            }
        } catch (SAXException ex) {
            throw new RenderingException(ex);
        }
    }

    public <T> void render(
            final String fileName,
            final String xsd,
            final String xsl,
            final String location,
            final T object) {
        PrintWriter pw = null;
        FileOutputStream fout = null;
        try {
            final JAXBContext context = JAXBContext.newInstance(object.getClass());
            final File file = new File(fileName);
            file.delete();
            //http://ws.apache.org/jaxme/release-0.3/manual/ch02s02.html
            StringWriter swriter = new StringWriter();
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
            if (xsd != null) {
                Schema schema = SchemaFactory.newInstance(
                        XMLConstants.W3C_XML_SCHEMA_NS_URI).newSchema(new File(xsd));
                marshaller.setSchema(schema);
            }
            swriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            if (location != null) {
                marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, location);
            }
            if (xsl != null) {
                swriter.write("<?xml-stylesheet type='text/xsl' href=\"" + xsl + "\" ?>\n");
            }
            marshaller.marshal(object, swriter);
            final String str = swriter.toString();
            fout = new FileOutputStream(fileName);
            pw = new PrintWriter(fout);
            pw.write(str);
            pw.flush();
        } catch (IOException | JAXBException | SAXException e) {
            throw new RenderingException(e);
        } finally {
            try {
                if (pw != null) {
                    pw.close();
                }
            } catch (Exception e) {
            }
            try {
                if (fout != null) {
                    fout.close();
                }
            } catch (IOException e) {
            }
        }
    }
    /*
     * For transforming with XSL public void transformToFile(JAXBContext
     * pContext, Object pObject, TransformerFactory pFactory, String
     * pStylesheetFileName, String pOutputFileName) throws JAXBException,
     * TransformerConfigurationException {
     *
     * javax.xml.transform.Source stylesheetSource = new
     * javax.xml.transform.StreamSource( new java.io.File(pStylesheetFileName));
     *
     * javax.xml.transform.Result outputResult = new
     * javax.xml.transform.StreamResult( new java.io.File(pOutputFileName));
     *
     * javax.xml.transform.sax.TransformerHandler handler =
     * ((SAXTransformerFactory)
     * pFactory).newTransformerHandler(stylesheetSource);
     *
     * handler.setResult(outputResult);
     * handler.getTransformer().setOutputProperty("output.encoding", "UTF-8");
     *
     * Marshaller marshaller = pContext.createMarshaller();
     * marshaller.marshal(pObject, handler); }
     *
    public class JAXBListener extends Listener {

        private final Logger log = LogManager.getLogger(getClass());

        @Override
        public void afterMarshal(Object source) {
            log.debug("Object: " + source.getClass().getName());
        }

        @Override
        public void beforeMarshal(Object source) {
            log.debug("Object: " + source.getClass().getName());
        }

    }
     */
}

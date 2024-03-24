package info.setmy.jena;

import org.apache.jena.query.Dataset;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ReadWrite;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.ResIterator;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.tdb.TDBFactory;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.VCARD;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.StringWriter;

public class JenaIT {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Test
    public void test() {
        log.info("Hello Jenna!!!");

        String personURI = "http://setmy.info/JohnSmith";
        String givenName = "John";
        String familyName = "Smith";
        String fullName = givenName + " " + familyName;

        Model model = ModelFactory.createDefaultModel();

        Resource johnSmith
            = model.createResource(personURI)
            .addProperty(VCARD.FN, fullName)
            .addProperty(VCARD.N,
                model.createResource()
                    .addProperty(VCARD.Given, givenName)
                    .addProperty(VCARD.Family, familyName));

        model.write(System.out);

        log.info("Model: {}", w(model));

        ResIterator iter = model.listSubjectsWithProperty(VCARD.FN);
        if (iter.hasNext()) {
            log.info("The database contains vcards for:");
            while (iter.hasNext()) {
                log.info("{}", iter.nextResource()
                    .getProperty(VCARD.FN)
                    .getString());
            }
        } else {
            System.out.println("No vcards were found in the database");
        }


        String directory = "target/";

        Dataset dataset = TDBFactory.createDataset(directory);
        dataset.begin(ReadWrite.WRITE);
        dataset.getDefaultModel().add(model);
        dataset.commit();
        dataset.end();

        Dataset dataset2 = TDBFactory.createDataset(directory);
        dataset2.begin(ReadWrite.READ);
        String queryString = "SELECT ?givenName ?familyName WHERE { " +
            "<http://setmy.info/JohnSmith> <http://www.w3.org/2001/vcard-rdf/3.0#N> ?name ." +
            "?name <http://www.w3.org/2001/vcard-rdf/3.0#Given> ?givenName ." +
            "?name <http://www.w3.org/2001/vcard-rdf/3.0#Family> ?familyName }";
        Query query = QueryFactory.create(queryString);
        try (QueryExecution qexec = QueryExecutionFactory.create(query, model)) {
            ResultSet results = qexec.execSelect();
            while (results.hasNext()) {
                QuerySolution soln = results.nextSolution();
                RDFNode givenNameNode = soln.get("givenName");
                RDFNode familyNameNode = soln.get("familyName");
                System.out.println("Given Name: " + givenNameNode);
                System.out.println("Family Name: " + familyNameNode);
            }
        }
        dataset2.end();
    }

    @Test
    public void second() {
        Model model = ModelFactory.createDefaultModel();
        Resource subject = model.createResource("http://setmy.info/subject");
        Property predicate = model.createProperty("http://setmy.info/predicate");
        RDFNode object = model.createResource("http://setmy.info/object");
        model.add(subject, predicate, object);
        model.write(System.out, "Turtle");
        log.info("Model: {}", w(model));
    }

    @Test
    public void third() {
        Model model = ModelFactory.createDefaultModel();
        Resource fragment = model.createResource("http://setmy.info/fragment1");
        Property textProperty = model.createProperty("http://setmy.info/hasText");
        Property coordinatesProperty = model.createProperty("http://setmy.info/hasCoordinates");
        String fragmentText = "Siin on fragmenttekst...";
        String fragmentCoordinates = "(x: 100, y: 200)";
        Resource textResource = model.createResource().addProperty(RDF.value, fragmentText);
        Resource coordinatesResource = model.createResource().addProperty(RDF.value, fragmentCoordinates);
        model.add(fragment, textProperty, textResource);
        model.add(fragment, coordinatesProperty, coordinatesResource);
        model.write(System.out, "Turtle");
    }

    private StringWriter w(Model model) {
        StringWriter writer = new StringWriter();
        model.write(writer, "RDF/XML");
        return writer;
    }
}

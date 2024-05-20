package E1;

//necessary imports are inserted

import org.apache.jena.rdf.model.*;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.rdf.model.*;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.vocabulary.RDF;

import static org.apache.jena.vocabulary.RDF.uri;

public class E1 {

    //Variables are created so that the code remains dynamic and clear
    //static String mainNamespace = "http://www.uni-trier.de";
    static String uriOfWebsite = "http://www.uni-trier.de/index.php?id=1890";
    static String lectureURI = "http://www.uni-trier.de/SemanticTechnologies";
    static String fnBergmann = "http://www.uni-trier.de/RalphBergmann";
    static String emailBergmann = "mailto:bergmann@uni-trier.de";
    static String fnHoffmann = "http://www.uni-trier.de/MaximilianHoffmann";
    static String emailHoffmann = "mailto:hoffmann@uni-trier.de";
    static String creatorURI = "http://www.uni-trier.de/creator";


    public static void main(String[] args) {

        //Create the model
        Model model = ModelFactory.createDefaultModel();

        RDFNode[] assignments = new RDFNode[3];
        assignments[0] = model.createLiteral("1.Assignment");
        assignments[1] = model.createLiteral("2.Assignment");
        assignments[2] = model.createLiteral("3.Assignment");
        RDFList listOfAssignments = model.createList(assignments);

        //Resources are created in chronological order, which are linked directly
        //Properties are created immediately and not in a separate location for a fast unterstanding of the code
        Resource creator = model.createResource(creatorURI)
                .addLiteral(model.createProperty("http://www.uni-trier.de/fullName"), fnBergmann)
                .addLiteral(model.createProperty("http://www.uni-trier.de/email"), emailBergmann);

        Resource tutor = model.createResource("http://www.uni-trier.de/tutor")
                .addLiteral(model.createProperty( "http://www.uni-trier.de/tutorFullName"), fnHoffmann)
                .addLiteral(model.createProperty("http://www.uni-trier.de/tutorMail"), emailHoffmann);

        Resource websiteUniTrier = model.createResource(uriOfWebsite)
                .addProperty(model.createProperty("http://www.uni-trier.de/createdBy"), creator);

//        Resource websiteUniTrier = model.createResource()
//                .addProperty(RDF.uri, model.createResource(uriOfWebsite))
//                .addProperty(model.createProperty(mainNamespace + "/createdBy"), creator);

        Resource exercise = model.createResource("http://www.uni-trier.de/exercise")
                .addProperty(model.createProperty("http://www.uni-trier.de/consistsOf"), listOfAssignments)
                .addProperty(model.createProperty("http://www.uni-trier.de/hasTutor"), tutor);

        Resource lecture = model.createResource(lectureURI)
                .addProperty(model.createProperty("http://www.uni-trier.de/isHeldBy"), creator)
                .addProperty(model.createProperty("http://www.uni-trier.de/hasExercise"), exercise);

        //Writing the model to the console
        RDFDataMgr.write(System.out, model, Lang.RDFXML);
    }
}

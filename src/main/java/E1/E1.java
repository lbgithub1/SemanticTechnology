package E1;

//necessary imports are inserted
import org.apache.jena.rdf.model.*;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;

public class E1 {

    //Variables are created so taht the code remains dynamic and clear
    static String mainNamespace = "http://www.uni-trier.de/index.php?id=1890";
    static String lectureURI = mainNamespace + "/SemanticTechnologies";
    static String fnBergmann = mainNamespace + "/RalphBergmann";
    static String emailBergmann = "mailto:bergmann@uni-trier.de";
    static String fnHoffmann = mainNamespace + "/MaximilianHoffmann";
    static String emailHoffmann = "mailto:hoffmann@uni-trier.de";
    static String creatorURI = mainNamespace + "/creator";


    public static void main(String[] args) {

        //Create the model
        Model model = ModelFactory.createDefaultModel();

        //A (closed) list is created, which represents the three assignments
        //The list is created at this early stage so that it can be immediately accessed at a later point
        RDFNode[] assignments = new RDFNode[3];
        assignments[0] = model.createLiteral("1.Assignment");
        assignments[1] = model.createLiteral("2.Assignment");
        assignments[2] = model.createLiteral("3.Assignment");
        RDFList listOfAssignments = model.createList(assignments);

        //Resources are created in chronological order, which are linked directly
        //Properties are created immediately and not in a separate location for a fast unterstanding of the code
        Resource creator = model.createResource(creatorURI)
                .addLiteral(model.createProperty(mainNamespace + "/fullName"), fnBergmann)
                .addLiteral(model.createProperty(mainNamespace + "/email"), emailBergmann);


        Resource tutor = model.createResource(mainNamespace + "/tutor")
                .addLiteral(model.createProperty(mainNamespace + "/tutorFullName"), fnHoffmann)
                .addLiteral(model.createProperty(mainNamespace + "tutorMail"), emailHoffmann);

        Resource websiteUniTrier = model.createResource(mainNamespace)
                .addProperty(model.createProperty(mainNamespace + "/createdBy"), creator);

        Resource exercise = model.createResource(mainNamespace + "/exercise")
                .addProperty(model.createProperty(mainNamespace + "/consistsOf"), listOfAssignments)
                .addProperty(model.createProperty(mainNamespace + "/hasTutor"), tutor);

        Resource lecture = model.createResource(lectureURI)
                .addProperty(model.createProperty(mainNamespace + "/isHeldBy"), creator)
                .addProperty(model.createProperty(mainNamespace + "/hasExercise"), exercise);

        //Writing the model to the console
        RDFDataMgr.write(System.out, model, Lang.RDFXML);
    }
}

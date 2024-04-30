package E1;

import org.apache.jena.rdf.model.*;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;

public class E1 {

    static String mainNamespace = "http://www.uni-trier.de/index.php?id=1890";
    static String lectureURI = mainNamespace + "/SemanticTechnologies";
    static String fnBergmann = mainNamespace + "/RalphBergmann";
    static String emailBergmann = "mailto:bergmann@uni-trier.de";
    static String fnHoffmann = mainNamespace + "/MaximilianHoffmann";
    static String emailHoffmann = "mailto:hoffmann@uni-trier.de";
    static String creatorURI = mainNamespace + "/creator";


    public static void main(String[] args) {

        Model model = ModelFactory.createDefaultModel();

        Property isCreator = model.createProperty(mainNamespace + "/creator");
        Property propFnBergmann = model.createProperty(mainNamespace + "/fullName");
        Property propMailBergmann = model.createProperty(mainNamespace + "/email");

        RDFNode[] assignment = new RDFNode[3];
        assignment[0] = model.createLiteral("Assignment 1");
        assignment[1] = model.createLiteral("Assignment 2");
        assignment[2] = model.createLiteral("Assignment 3");
        RDFList closedList = model.createList(assignment);

        Resource creator = model.createResource(creatorURI)
                .addLiteral(propFnBergmann, fnBergmann)
                .addLiteral(propMailBergmann, emailBergmann);

        Resource tutor = model.createResource(mainNamespace + "/tutor")
                .addLiteral(model.createProperty(mainNamespace + "/tutorFullName"), fnHoffmann)
                .addLiteral(model.createProperty(mainNamespace + "tutorMail"), emailHoffmann);

        Resource websiteUniTrier = model.createResource(mainNamespace)
                .addProperty(isCreator, creator);

        Resource exercise = model.createResource(mainNamespace + "/exercise")
                .addProperty(model.createProperty(mainNamespace + "/consistsOf"), closedList)
                .addProperty(model.createProperty(mainNamespace + "/hasTutor"), tutor);

        Resource lecture = model.createResource(lectureURI)
                .addProperty(model.createProperty(mainNamespace + "/isTeachedBy"), creator)
                .addProperty(model.createProperty(mainNamespace + "/hasExercise"), exercise);
        
        RDFDataMgr.write(System.out, model, Lang.RDFXML);


    }
}

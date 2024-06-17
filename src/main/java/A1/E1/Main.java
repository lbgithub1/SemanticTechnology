package A1.E1;

//necessary imports are inserted
import org.apache.jena.rdf.model.*;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;

public class Main {

    //Variablen werden separat erstellt, damit der Code dynamisch und übersichtlich bleibt
    static String websiteURI = "http://www.uni-trier.de/index.php?id=1890";
    static String mainNamespace = "http://www.uni-trier.de/";
    static String lectureURI = mainNamespace + "SemanticTechnologies";
    static String fnBergmann = mainNamespace + "RalphBergmann";
    static String emailBergmann = "mailto:bergmann@uni-trier.de";
    static String fnHoffmann = mainNamespace + "MaximilianHoffmann";
    static String emailHoffmann = "mailto:hoffmann@uni-trier.de";
    static String creatorURI = mainNamespace + "creator";


    public static void main(String[] args) {

        //Modell wird erstellt
        Model model = ModelFactory.createDefaultModel();

        //Eine (geschlossene) Liste wird erstellt, welche die drei Aufgaben repräsentieren
        //Die Liste wird bereits hier erstellt, damit sie direkt zu einem späteren Zeitpunkt verfügbar ist
        RDFNode[] assignments = new RDFNode[3];
        assignments[0] = model.createLiteral("1.Assignment");
        assignments[1] = model.createLiteral("2.Assignment");
        assignments[2] = model.createLiteral("3.Assignment");
        RDFList listOfAssignments = model.createList(assignments);

        //Ressourcen werden in chronologischer Reihenfolge erstellt und direkt miteinander verbunden
        //Eigenschaften werden erstellt und direkt verwendet, ohne diese zuerst in einer anderen Position zu definieren
        Resource creator = model.createResource(creatorURI)
                .addLiteral(model.createProperty(mainNamespace + "fullName"), fnBergmann)
                .addLiteral(model.createProperty(mainNamespace + "email"), emailBergmann);


        Resource tutor = model.createResource(mainNamespace + "tutor")
                .addLiteral(model.createProperty(mainNamespace + "tutorFullName"), fnHoffmann)
                .addLiteral(model.createProperty(mainNamespace + "tutorMail"), emailHoffmann);

        Resource websiteUniTrier = model.createResource(websiteURI)
                .addProperty(model.createProperty(mainNamespace + "createdBy"), creator);

        Resource exercise = model.createResource(mainNamespace + "exercise")
                .addProperty(model.createProperty(mainNamespace + "consistsOf"), listOfAssignments)
                .addProperty(model.createProperty(mainNamespace + "hasTutor"), tutor);

        Resource lecture = model.createResource(lectureURI)
                .addProperty(model.createProperty(mainNamespace + "isHeldBy"), creator)
                .addProperty(model.createProperty(mainNamespace + "hasExercise"), exercise);

        //Das Modell wird auf die Konsole geschrieben
        //Anschließend wird das Modell in den vorgegebenen Validator eingesetzt, um den Graphen zu generieren
        RDFDataMgr.write(System.out, model, Lang.RDFXML);
    }
}

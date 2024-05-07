package E2;

import org.apache.jena.rdf.model.*;

public class Main {
    public static void main(String[] args) {

        Model m = ModelFactory.createDefaultModel()
                .read("E2.ttl", "TURTLE");
        m.write(System.out);
    }
}
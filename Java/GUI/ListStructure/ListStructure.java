package GUI.ListStructure;

import Database.Database;

public class ListStructure {

    public ListStructure(Database database) {
        new Controller(new Model(database), new View());
    }

}

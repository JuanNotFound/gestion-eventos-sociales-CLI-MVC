package list;

import entities.Entity;

import java.util.ArrayList;
import java.util.Iterator;

public class EntityList<T extends Entity> extends ArrayList<T> {

    public T searchByName(String name) {
        assert name != null;

        Iterator<T> iterator = this.iterator();
        T result = null;
        boolean found = false;

        if (!isEmpty()) {
            while (iterator.hasNext() && !found) {
                T current = iterator.next();

                if (current.checkNameMatches(name)) {
                    result = current;
                }
            }
        }

        return result;
    }


}
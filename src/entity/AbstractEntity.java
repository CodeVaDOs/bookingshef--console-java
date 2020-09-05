package entity;

import java.io.Serializable;
import java.util.Objects;

public abstract class AbstractEntity implements Serializable {
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public AbstractEntity(long id) {
        this.id = id;
    }

}

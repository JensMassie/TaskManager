package pe.task.domain;

import java.io.Serializable;
import java.util.UUID;


public class SubTask implements Serializable {
    String name;
    UUID uuid;
    String description;

    public SubTask(String name, String description) {
        this.name = name;
        this.uuid = UUID.randomUUID();
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

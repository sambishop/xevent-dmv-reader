package com.clearwateranalytics.xevent.dmv.reader;

import java.util.Objects;
import java.util.UUID;

public final class Action {
    private final UUID guid;
    private final String name;
    private final String description;
    private final TypeId typeId;
    private final int hashCode;

    public Action(UUID guid, String name, String description, TypeId typeId) {
        this.guid = guid;
        this.name = name;
        this.description = description;
        this.typeId = typeId;
 
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.guid);
        hash = 41 * hash + Objects.hashCode(this.name);
        hash = 41 * hash + Objects.hashCode(this.description);
        hash = 41 * hash + Objects.hashCode(this.typeId);
        this.hashCode = hash;
    }

    public UUID getGuid() {
        return guid;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public TypeId getTypeId() {
        return typeId;
    }

    @Override
    public int hashCode() {
        return hashCode;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Action other = (Action) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.guid, other.guid)) {
            return false;
        }
        return Objects.equals(this.typeId, other.typeId);
    }

    @Override
    public String toString() {
        return "Action{"
                + "guid=" + guid
                + ", name=" + name
                + ", description=" + description
                + ", typeId=" + typeId
                + '}';
    }
}

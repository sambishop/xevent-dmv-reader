package com.clearwateranalytics.xevent.dmv.reader;

import java.util.Objects;
import java.util.UUID;

public abstract class ObjectId {
    private final UUID guid;
    private final String name;
    private final int hashCode;

    ObjectId(UUID guid, String name) {
        this.guid = guid;
        this.name = name;

        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.guid);
        hash = 37 * hash + Objects.hashCode(this.name);
        this.hashCode = hash;
    }

    public UUID getGuid() {
        return guid;
    }

    public String getName() {
        return name;
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
        final ObjectId other = (ObjectId) obj;
        if (!Objects.equals(this.guid, other.guid)) {
            return false;
        }
        return Objects.equals(this.name, other.name);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{"
                + "guid=" + guid
                + ", name=" + name
                + '}';
    }
}

package com.clearwateranalytics.xevent.dmv.reader;

import java.util.Objects;
import java.util.UUID;

public final class Type {
    private final UUID guid;
    private final String name;
    private final String description;
    private final Integer capabilities;
    private final String capabilitiesDesc;
    private final int typeSize;
    private final int hashCode;

    public Type(UUID guid, String name, String description, Integer capabilities, String capabilitiesDesc, int typeSize) {
        this.guid = guid;
        this.name = name;
        this.description = description;
        this.capabilities = capabilities;
        this.capabilitiesDesc = capabilitiesDesc;
        this.typeSize = typeSize;

        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.guid);
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + Objects.hashCode(this.description);
        hash = 97 * hash + Objects.hashCode(this.capabilities);
        hash = 97 * hash + Objects.hashCode(this.capabilitiesDesc);
        hash = 97 * hash + this.typeSize;
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

    public Integer getCapabilities() {
        return capabilities;
    }

    public String getCapabilitiesDesc() {
        return capabilitiesDesc;
    }

    public int getTypeSize() {
        return typeSize;
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
        final Type other = (Type) obj;
        if (this.typeSize != other.typeSize) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.capabilitiesDesc, other.capabilitiesDesc)) {
            return false;
        }
        if (!Objects.equals(this.guid, other.guid)) {
            return false;
        }
        return Objects.equals(this.capabilities, other.capabilities);
    }

    @Override
    public String toString() {
        return "Type{"
                + "guid=" + guid
                + ", name=" + name
                + ", description=" + description
                + ", capabilities=" + capabilities
                + ", capabilitiesDesc=" + capabilitiesDesc
                + ", typeSize=" + typeSize
                + '}';
    }
}

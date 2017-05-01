package com.clearwateranalytics.xevent.dmv.reader;

import java.util.Objects;
import java.util.UUID;

public final class Event {
    private final UUID guid;
    private final String name;
    private final String description;
    private final Integer capabilities;
    private final String capabilitiesDesc;
    private final int hashCode;

    public Event(UUID guid, String name, String description, Integer capabilities, String capabilitiesDesc) {
        this.guid = guid;
        this.name = name;
        this.description = description;
        this.capabilities = capabilities;
        this.capabilitiesDesc = capabilitiesDesc;

        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.guid);
        hash = 47 * hash + Objects.hashCode(this.name);
        hash = 47 * hash + Objects.hashCode(this.description);
        hash = 47 * hash + Objects.hashCode(this.capabilities);
        hash = 47 * hash + Objects.hashCode(this.capabilitiesDesc);
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
        final Event other = (Event) obj;
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
        return "Event{"
                + "guid=" + guid
                + ", name=" + name
                + ", description=" + description
                + ", capabilities=" + capabilities
                + ", capabilitiesDesc=" + capabilitiesDesc
                + '}';
    }
}

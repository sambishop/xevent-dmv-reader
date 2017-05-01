package com.clearwateranalytics.xevent.dmv.reader;

import java.util.Objects;

public final class EventColumn {
    private final String name;
    private final int columnId;
    private final EventId eventId;
    private final TypeId typeId;
    private final String columnType;
    private final String columnValue;
    private final int capabilities;
    private final String capabilitiesDesc;
    private final String description;
    private final int hashCode;

    public EventColumn(String name, int columnId, EventId eventId, TypeId typeId, String columnType, String columnValue, int capabilities, String capabilitiesDesc, String description) {
        this.name = name;
        this.columnId = columnId;
        this.eventId = eventId;
        this.typeId = typeId;
        this.columnType = columnType;
        this.columnValue = columnValue;
        this.capabilities = capabilities;
        this.capabilitiesDesc = capabilitiesDesc;
        this.description = description;

        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.name);
        hash = 17 * hash + this.columnId;
        hash = 17 * hash + Objects.hashCode(this.eventId);
        hash = 17 * hash + Objects.hashCode(this.typeId);
        hash = 17 * hash + Objects.hashCode(this.columnType);
        hash = 17 * hash + Objects.hashCode(this.columnValue);
        hash = 17 * hash + this.capabilities;
        hash = 17 * hash + Objects.hashCode(this.capabilitiesDesc);
        hash = 17 * hash + Objects.hashCode(this.description);
        this.hashCode = hash;
    }

    public String getName() {
        return name;
    }

    public int getColumnId() {
        return columnId;
    }

    public EventId getEventId() {
        return eventId;
    }

    public TypeId getTypeId() {
        return typeId;
    }

    public String getColumnType() {
        return columnType;
    }

    public String getColumnValue() {
        return columnValue;
    }

    public int getCapabilities() {
        return capabilities;
    }

    public String getCapabilitiesDesc() {
        return capabilitiesDesc;
    }

    public String getDescription() {
        return description;
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
        final EventColumn other = (EventColumn) obj;
        if (this.columnId != other.columnId) {
            return false;
        }
        if (this.capabilities != other.capabilities) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.columnType, other.columnType)) {
            return false;
        }
        if (!Objects.equals(this.columnValue, other.columnValue)) {
            return false;
        }
        if (!Objects.equals(this.capabilitiesDesc, other.capabilitiesDesc)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.eventId, other.eventId)) {
            return false;
        }
        return Objects.equals(this.typeId, other.typeId);
    }

    @Override
    public String toString() {
        return "EventColumn{"
                + "name=" + name
                + ", columnId=" + columnId
                + ", eventId=" + eventId
                + ", typeId=" + typeId
                + ", columnType=" + columnType
                + ", columnValue=" + columnValue
                + ", capabilities=" + capabilities
                + ", capabilitiesDesc=" + capabilitiesDesc
                + ", description=" + description
                + '}';
    }
}

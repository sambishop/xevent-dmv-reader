package com.clearwateranalytics.xevent.dmv.reader;

import java.util.Objects;
import java.util.UUID;

public final class Package {
    private final String name;
    private final UUID guid;
    private final String description;
    private final Integer capabilities;
    private final String capabilitiesDesc;
    private final UUID moduleGuid;
    private final int moduleAddress;
    private final int hashCode;

    public Package(String name, UUID guid, String description, Integer capabilities, String capabilitiesDesc, UUID moduleGuid, int moduleAddress) {
        this.name = name;
        this.guid = guid;
        this.description = description;
        this.capabilities = capabilities;
        this.capabilitiesDesc = capabilitiesDesc;
        this.moduleGuid = moduleGuid;
        this.moduleAddress = moduleAddress;

        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.name);
        hash = 53 * hash + Objects.hashCode(this.guid);
        hash = 53 * hash + Objects.hashCode(this.description);
        hash = 53 * hash + Objects.hashCode(this.capabilities);
        hash = 53 * hash + Objects.hashCode(this.capabilitiesDesc);
        hash = 53 * hash + Objects.hashCode(this.moduleGuid);
        hash = 53 * hash + Objects.hashCode(this.moduleAddress);
        this.hashCode = hash;
    }

    public String getName() {
        return name;
    }

    public UUID getGuid() {
        return guid;
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

    public UUID getModuleGuid() {
        return moduleGuid;
    }

    public int getModuleAddress() {
        return moduleAddress;
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
        final Package other = (Package) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.guid, other.guid)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.capabilities, other.capabilities)) {
            return false;
        }
        if (!Objects.equals(this.capabilitiesDesc, other.capabilitiesDesc)) {
            return false;
        }
        if (!Objects.equals(this.moduleGuid, other.moduleGuid)) {
            return false;
        }
        return moduleAddress == moduleAddress;
    }

    @Override
    public String toString() {
        return "Package{"
                + "name=" + name
                + ", guid=" + guid
                + ", description=" + description
                + ", capabilities=" + capabilities
                + ", capabilitiesDesc=" + capabilitiesDesc
                + ", moduleGuid=" + moduleGuid
                + ", moduleAddress=0x" + Integer.toHexString(moduleAddress)
                + '}';
    }
}

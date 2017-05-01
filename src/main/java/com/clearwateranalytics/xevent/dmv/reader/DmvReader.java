package com.clearwateranalytics.xevent.dmv.reader;

import java.nio.ByteBuffer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DmvReader {
        private static final String PACKAGE_QUERY = "SELECT * FROM sys.dm_xe_packages";
        private static final String ACTION_AND_EVENT_QUERY = "SELECT" +
"		dxo.object_type," +
"		object_package_guid = dxo.package_guid," +
"		[object_name] = dxo.name," +
"		object_description = dxo.[description]," +
"		object_capabilities = dxo.capabilities," +
"		object_capabilities_desc = dxo.capabilities_desc," +
"		[type_package_guid] = CASE dxo.object_type WHEN 'action' THEN dxo.[type_package_guid] ELSE dxoc.[type_package_guid] END," +
"		[type_name] = CASE dxo.object_type WHEN 'action' THEN dxo.[type_name] ELSE dxoc.[type_name] END," +
"		column_name = dxoc.name," +
"		column_description = dxoc.[description]," +
"		dxoc.column_id," +
"		dxoc.column_type," +
"		dxoc.column_value," +
"		column_capabilities = dxoc.capabilities," +
"		column_capabilities_desc = dxoc.capabilities_desc" +
"	FROM sys.dm_xe_objects AS dxo" +
"	LEFT JOIN sys.dm_xe_object_columns AS dxoc ON 1=1" +
"		AND dxoc.object_package_guid = dxo.package_guid" +
"		AND dxoc.object_name = dxo.name" +
"	WHERE dxo.object_type IN ('action', 'event')" +
"	ORDER BY dxo.object_type, dxo.package_guid, dxo.name";

    public void read(Connection conn, DmvReaderListener listener) throws SQLException {
        Map<UUID, Package> packages = readPackages(conn);
        UUID prevPackage = null;
        String prevObjectType = null;
        String prevEventName = null;
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(ACTION_AND_EVENT_QUERY)) {
            while (rs.next()) {
                String objectType = rs.getString(1);
                UUID curPackage = readUUID(rs, 2);
                if (objectType.equals("action")) {
                    listener.visitAction(readAction(rs));
                } else if (objectType.equals("event")) {
                    if (!curPackage.equals(prevPackage)) {
                        if (!"action".equals(prevObjectType)) {
                            listener.endEventPackageVisit();
                        }
                        listener.beginEventPackageVisit(packages.get(curPackage));
                    }
                    String eventName = rs.getString(3);
                    if (!curPackage.equals(prevPackage) || !eventName.equals(prevEventName)) {
                        if ("event".equals(prevObjectType)) {
                            listener.endEventVisit();
                        }
                        listener.beginEventVisit(readEvent(rs));
                        prevEventName = eventName;
                    }
                    listener.visitEventColumn(readEventColumn(rs));
                }
                prevPackage = curPackage;
                prevObjectType = objectType;
            }
            if ("event".equals(prevObjectType)) {
                listener.endEventVisit();
                listener.endEventPackageVisit();
            }
        }
    }

    private Map<UUID, Package> readPackages(Connection conn) throws SQLException {
        Map<UUID, Package> pkgs = new HashMap<>();
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(PACKAGE_QUERY)) {
            while (rs.next()) {
                Package pkg = new Package(
                        rs.getString(1),
                        readUUID(rs, 2),
                        rs.getString(3),
                        readCapabilities(rs, 4),
                        rs.getString(5),
                        readUUID(rs, 6),
                        ByteBuffer.wrap(rs.getBytes(7)).getInt()
                    );
                pkgs.put(pkg.getGuid(), pkg);
            }
        }
        return pkgs;
    }

    private Action readAction(ResultSet rs) throws SQLException {
        return new Action(
                readUUID(rs, 2),
                rs.getString(3),
                rs.getString(4),
                new TypeId(readUUID(rs, 7), rs.getString(8))
            );
    }

    private Event readEvent(ResultSet resultSet) throws SQLException {
        return new Event(
                readUUID(resultSet, 2),
                resultSet.getString(3),
                resultSet.getString(4),
                readCapabilities(resultSet, 5),
                resultSet.getString(6)
            );
    }

    private EventColumn readEventColumn(ResultSet resultSet) throws SQLException {
        EventId eventId = new EventId(readUUID(resultSet, 2), resultSet.getString(3));
        TypeId typeId = new TypeId(readUUID(resultSet, 7), resultSet.getString(8));
        return new EventColumn(
                resultSet.getString(9),
                resultSet.getInt(11),
                eventId,
                typeId,
                resultSet.getString(12),
                resultSet.getString(13),
                readCapabilities(resultSet, 14),
                resultSet.getString(15),
                resultSet.getString(10)
            );
    }

    private Integer readCapabilities(ResultSet resultSet, int columnIndex) throws SQLException {
        Integer capabilities = resultSet.getInt(columnIndex);
        return resultSet.wasNull() ? null : capabilities;
    }

    private UUID readUUID(ResultSet resultSet, int columnIndex) throws SQLException {
        return UUID.fromString(resultSet.getString(columnIndex));
    }
}

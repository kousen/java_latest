package com.kousenit.datetime;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;

import static java.util.Comparator.comparingInt;

public class AntarcticaTimeZones {
    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
        System.out.println("There are " + availableZoneIds.size() + " zoneids around the world");
        List<ZonedDateTime> antarcticZones =
                availableZoneIds.stream()  // Stream<String>
                        .filter(regionId -> regionId.contains("Antarctica"))
                        .map(ZoneId::of)  // Stream<ZoneId>
                        .map(now::atZone) // Stream<ZonedDateTime>
                        .sorted(comparingInt(zoneId -> zoneId.getOffset().getTotalSeconds()))
                        .toList();

        antarcticZones.forEach(zdt ->
                System.out.printf("%7s: %25s %7s%n", zdt.getOffset(), zdt.getZone(),
                        zdt.getZone().getRules().isDaylightSavings(zdt.toInstant())));
    }
}

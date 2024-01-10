package si.fri.rso.location.version.models.converters;

import si.fri.rso.location.version.lib.Location;
import si.fri.rso.location.version.models.entities.LocationEntity;

public class LocationConverter {

    public static Location toDto(LocationEntity entity) {

        Location dto = new Location();
        dto.setId(entity.getId());
        dto.setname(entity.getname());
        dto.setdescription(entity.getdescription());
        dto.settoilets(entity.gettoilets());
        dto.setfood(entity.getfood());
        dto.setsleep(entity.getsleep());
        dto.setLongtitude(entity.getLongtitude());
        dto.setLatitude(entity.getLatitude());

        return dto;

    }

    public static LocationEntity toEntity(Location dto) {

        LocationEntity entity = new LocationEntity();
        entity.setId(dto.getId());
        entity.setname(dto.getname());
        entity.setdescription(dto.getdescription());
        entity.settoilets(dto.gettoilets());
        entity.setfood(dto.getfood());
        entity.setsleep(dto.getsleep());
        entity.setLongtitude(dto.getLongtitude());
        entity.setLatitude(dto.getLatitude());

        return entity;

    }

}

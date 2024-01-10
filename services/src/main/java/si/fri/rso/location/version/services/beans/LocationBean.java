package si.fri.rso.location.version.services.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;

import si.fri.rso.location.version.lib.Location;
import si.fri.rso.location.version.models.converters.LocationConverter;
import si.fri.rso.location.version.models.entities.LocationEntity;


@RequestScoped
public class LocationBean {

    private Logger log = Logger.getLogger(LocationBean.class.getName());

    @Inject
    private EntityManager em;

    public List<Location> getLocation() {

        TypedQuery<LocationEntity> query = em.createNamedQuery(
                "LocationEntity.getAll", LocationEntity.class);

        List<LocationEntity> resultList = query.getResultList();

        return resultList.stream().map(LocationConverter::toDto).collect(Collectors.toList());

    }

    public List<Location> getLocationFilter(UriInfo uriInfo) {

        QueryParameters queryParameters = QueryParameters.query(uriInfo.getRequestUri().getQuery()).defaultOffset(0)
                .build();

        return JPAUtils.queryEntities(em, LocationEntity.class, queryParameters).stream()
                .map(LocationConverter::toDto).collect(Collectors.toList());
    }

    public Location getLocation(Integer id) {

        LocationEntity LocationEntity = em.find(LocationEntity.class, id);

        if (LocationEntity == null) {
            throw new NotFoundException();
        }

        Location Location = LocationConverter.toDto(LocationEntity);

        return Location;
    }

    public Location createLocation(Location Location) {

        LocationEntity LocationEntity = LocationConverter.toEntity(Location);

        try {
            beginTx();
            em.persist(LocationEntity);
            commitTx();
        }
        catch (Exception e) {
            rollbackTx();
        }

        if (LocationEntity.getId() == null) {
            throw new RuntimeException("Entity was not persisted");
        }

        return LocationConverter.toDto(LocationEntity);
    }

    public Location putLocation(Integer id, Location Location) {

        LocationEntity c = em.find(LocationEntity.class, id);

        if (c == null) {
            return null;
        }

        LocationEntity updatedLocationEntity = LocationConverter.toEntity(Location);

        try {
            beginTx();
            updatedLocationEntity.setId(c.getId());
            updatedLocationEntity = em.merge(updatedLocationEntity);
            commitTx();
        }
        catch (Exception e) {
            rollbackTx();
        }

        return LocationConverter.toDto(updatedLocationEntity);
    }

    public boolean deleteLocation(Integer id) {

        LocationEntity Location = em.find(LocationEntity.class, id);

        if (Location != null) {
            try {
                beginTx();
                em.remove(Location);
                commitTx();
            }
            catch (Exception e) {
                rollbackTx();
            }
        }
        else {
            return false;
        }

        return true;
    }

    private void beginTx() {
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
    }

    private void commitTx() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().commit();
        }
    }

    private void rollbackTx() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
    }
}

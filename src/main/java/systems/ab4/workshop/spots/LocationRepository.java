package systems.ab4.workshop.spots;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by mariu on 6/22/2017.
 */
public interface LocationRepository extends CrudRepository<Location,Long>{

    List<Location > findByName(String nume);
}

package be.iccbxl.pid.reservations2026.repository;

import java.util.List;
import be.iccbxl.pid.reservations2026.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
    List<Artist> findByLastname(String lastname);
    Artist findById(long id);
}
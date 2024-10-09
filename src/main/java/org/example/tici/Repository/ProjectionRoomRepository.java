package org.example.tici.Repository;
import org.example.tici.Model.Entities.ProjectionRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectionRoomRepository extends JpaRepository<ProjectionRoom, Integer> {

    ProjectionRoom findByRoomNumber(int roomNumber);
}

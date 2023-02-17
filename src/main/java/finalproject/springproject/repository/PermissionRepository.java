package finalproject.springproject.repository;

import finalproject.springproject.models.Permission;
import finalproject.springproject.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface PermissionRepository extends JpaRepository<Permission, Long> {

    Permission findByName(String name);


}

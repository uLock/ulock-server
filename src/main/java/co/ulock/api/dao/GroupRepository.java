package co.ulock.api.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import co.ulock.api.data.Group;

@Transactional
public interface GroupRepository extends JpaRepository<Group, String> {

	
}

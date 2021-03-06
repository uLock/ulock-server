package co.ulock.api.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import co.ulock.api.data.User;

@Transactional
public interface UserRepository extends JpaRepository<User, String> {

	User findOneByAccountId(String accountId);
	
	List<User> findByEmail(String email);
	
}

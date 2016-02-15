package co.ulock.api.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import co.ulock.api.data.Password;

@Transactional
public interface PasswordRepository extends JpaRepository<Password, String> {
	List<Password> findByAccountId(String accountId);
}

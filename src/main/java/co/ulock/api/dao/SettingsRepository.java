package co.ulock.api.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import co.ulock.api.data.Settings;

@Transactional
public interface SettingsRepository extends JpaRepository<Settings, String> {

	Settings findByAccountId(String accountId);
	
}

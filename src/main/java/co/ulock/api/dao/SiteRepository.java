package co.ulock.api.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import co.ulock.api.data.Site;

@Transactional
public interface SiteRepository extends JpaRepository<Site, String> {
	List<Site> findByAccountId(String accountId);
}

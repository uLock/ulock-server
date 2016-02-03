package co.ulock.api.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.ulock.api.data.Site;

public interface SiteRepository extends JpaRepository<Site, String> {
	List<Site> findByAccountId(String accountId);
}

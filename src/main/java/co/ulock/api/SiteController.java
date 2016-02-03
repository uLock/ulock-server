package co.ulock.api;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.ulock.api.dao.SiteRepository;
import co.ulock.api.data.Site;

@CrossOrigin
@RestController
public class SiteController {

	@Autowired
	private SiteRepository dao;

	@RequestMapping(path = "/site")
	public List<Site> sites(Principal principal) {
		return dao.findByAccountId(principal.getName());
	}

	@Transactional
	@RequestMapping(path = "/site", method = RequestMethod.POST)
	public Site create(@RequestBody Site site, Principal principal) {
		site.setAccountId(principal.getName());
		return dao.save(site);
	}
	
	@Transactional
	@RequestMapping(path = "/site/{siteId}", method = RequestMethod.PUT)
	public Site update(@RequestBody Site site,@PathVariable String siteId, Principal principal) {
		site.setId(siteId);
		site.setAccountId(principal.getName());
		return dao.save(site);
	}

}

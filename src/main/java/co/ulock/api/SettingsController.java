package co.ulock.api;

import static org.torpedoquery.jpa.Torpedo.from;
import static org.torpedoquery.jpa.Torpedo.select;
import static org.torpedoquery.jpa.Torpedo.where;

import java.security.Principal;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.ulock.api.data.Settings;

@CrossOrigin
@RestController
public class SettingsController {

	@Autowired
	private EntityManager manager;

	@RequestMapping(path = "/settings", method = RequestMethod.GET)
	public Settings getUserSettings(Principal principal) {
		Settings from = from(Settings.class);
		where(from.getAccountId()).eq(principal.getName());
		return select(from).get(manager);
	}

	@Transactional
	@RequestMapping(path = "/settings", method = RequestMethod.POST)
	public Settings create(@RequestBody Settings settings, Principal principal) {
		settings.setAccountId(principal.getName());
		return manager.merge(settings);
	}

	@Transactional
	@RequestMapping(path = "/settings", method = RequestMethod.PUT)
	public Settings update(@RequestBody Settings settings, Principal principal) {
		settings.setAccountId(principal.getName());
		return manager.merge(settings);
	}

}

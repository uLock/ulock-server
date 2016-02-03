package co.ulock.api;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.ulock.api.dao.SettingsRepository;
import co.ulock.api.data.Settings;

@CrossOrigin
@RestController
public class SettingsController {

	@Autowired
	private SettingsRepository dao;

	@RequestMapping(path = "/settings", method = RequestMethod.GET)
	public Settings getUserSettings(Principal principal) {
		return dao.findByAccountId(principal.getName());
	}

	@Transactional
	@RequestMapping(path = "/settings", method = RequestMethod.POST)
	public Settings create(@RequestBody Settings settings, Principal principal) {
		settings.setAccountId(principal.getName());
		return dao.save(settings);
	}

	@Transactional
	@RequestMapping(path = "/settings", method = RequestMethod.PUT)
	public Settings update(@RequestBody Settings settings, Principal principal) {
		settings.setAccountId(principal.getName());
		return dao.save(settings);
	}

}

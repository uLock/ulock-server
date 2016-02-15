package co.ulock.api;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.ulock.api.dao.UserRepository;
import co.ulock.api.data.User;

@CrossOrigin
@RestController
public class UserController {

	@Autowired
	private UserRepository dao;

	@RequestMapping(path = "/user", method = RequestMethod.GET)
	public User getUserSettings(Principal principal) {
		return dao.findOneByAccountId(principal.getName());
	}

	@Transactional
	@RequestMapping(path = "/user", method = RequestMethod.POST)
	public User create(@RequestBody User settings, Principal principal) {
		settings.setAccountId(principal.getName());	
		return dao.save(settings);
	}


}

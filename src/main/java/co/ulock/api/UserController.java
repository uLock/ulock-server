package co.ulock.api;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.ulock.api.dao.UserRepository;
import co.ulock.api.data.User;
import co.ulock.api.view.PublicUser;

@CrossOrigin
@RestController
public class UserController {

	@Autowired
	private UserRepository dao;

	@Transactional
	@RequestMapping(path = "/users", method = RequestMethod.GET)
	public List<PublicUser> findPublicUsers(Principal principal, @RequestParam("email") String email) {
		List<User> users = dao.findByEmail(email);
		return users.stream().map(User::getPublicKey).map(PublicUser::new).collect(Collectors.toList());
	}

	@Transactional
	@RequestMapping(path = "/user", method = RequestMethod.GET)
	public User getUserSettings(Principal principal) {
		User user = dao.findOneByAccountId(principal.getName());
		String email = getEmail(principal);

		// lazy update
		if (!email.equals(user.getEmail())) {
			user.setEmail(email);
		}

		return user;
	}

	@Transactional
	@RequestMapping(path = "/user", method = RequestMethod.POST)
	public User create(@RequestBody User settings, Principal principal) {
		String email = getEmail(principal);
		settings.setEmail(email);
		settings.setAccountId(principal.getName());
		return dao.save(settings);
	}

	private String getEmail(Principal principal) {
		KeycloakPrincipal<KeycloakSecurityContext> keycloakPrincipal = (KeycloakPrincipal<KeycloakSecurityContext>) principal;
		String email = keycloakPrincipal.getKeycloakSecurityContext().getToken().getEmail();
		return email;
	}

}

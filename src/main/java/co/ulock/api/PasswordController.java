package co.ulock.api;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.ulock.api.dao.PasswordRepository;
import co.ulock.api.data.Password;

@CrossOrigin
@RestController
public class PasswordController {

	@Autowired
	private PasswordRepository dao;

	@RequestMapping(path = "/passwords")
	public List<Password> get(Principal principal) {
		return dao.findByAccountId(principal.getName());
	}

	@RequestMapping(path = "/passwords/{passwordId}", method = RequestMethod.GET)
	public ResponseEntity<?> getById(Principal principal, @PathVariable String passwordId) {
		Password findOne = dao.findOne(passwordId);
		if (findOne != null && findOne.getAccountId().equals(principal.getName())) {
			return ResponseEntity.ok(findOne);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@Transactional
	@RequestMapping(path = "/passwords", method = RequestMethod.POST)
	public Password create(@RequestBody Password site, Principal principal) {
		site.setAccountId(principal.getName());
		return dao.save(site);
	}

	@Transactional
	@RequestMapping(path = "/passwords/{passwordId}", method = RequestMethod.PUT)
	public ResponseEntity<?> update(@RequestBody Password site, @PathVariable String passwordId, Principal principal) {
		Password findOne = dao.findOne(passwordId);
		if (findOne != null && findOne.getAccountId().equals(principal.getName())) {
			findOne.setData(site.getData());
			return ResponseEntity.ok(dao.save(findOne));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}

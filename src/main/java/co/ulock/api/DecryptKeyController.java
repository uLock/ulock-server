package co.ulock.api;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.ulock.api.dao.DecryptKeyRepository;
import co.ulock.api.data.DecryptKey;

@CrossOrigin
@RestController
public class DecryptKeyController {

	@Autowired
	private DecryptKeyRepository dao;

	@RequestMapping(path = "/decrypt/{deviceId}", method = RequestMethod.GET)
	public ResponseEntity<?> getDecryptKey(Principal principal, @PathVariable String deviceId) {
		List<DecryptKey> activeKeys = dao.findActivesKey(principal.getName(), deviceId);
		if (!activeKeys.isEmpty()) {
			return ResponseEntity.ok(activeKeys.get(0));
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@RequestMapping(path = "/decrypt/{deviceId}", method = RequestMethod.POST)
	public DecryptKey createKey(Principal principal, @PathVariable("deviceId") String deviceId) {
		return dao.create(principal.getName(), deviceId);
	}

}

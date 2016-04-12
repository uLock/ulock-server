package co.ulock.api.dao;

import static org.torpedoquery.jpa.Torpedo.from;
import static org.torpedoquery.jpa.Torpedo.select;
import static org.torpedoquery.jpa.Torpedo.where;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.UUID;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.ulock.api.data.DecryptKey;

@Repository
@Transactional
public class DecryptKeyRepository {

	@Autowired
	private EntityManager manager;

	public DecryptKey findActiveKey(String accountId, String deviceId) {
		DecryptKey from = from(DecryptKey.class);
		where(from.getAccountId()).eq(accountId).and(from.getDeviceId()).eq(deviceId).and(from.getExpireAt())
				.gt(new Date());
		return select(from).get(manager);
	}
	

	public DecryptKey create(String accountId, String deviceId) {
		DecryptKey decryptKey = new DecryptKey();
		decryptKey.setAccountId(accountId);
		decryptKey.setDeviceId(deviceId);
		decryptKey.setKey(UUID.randomUUID().toString());
		LocalDateTime expire = LocalDateTime.now().plusMonths(1);
		decryptKey.setExpireAt(Date.from(expire.toInstant(ZoneOffset.UTC)));
		return manager.merge(decryptKey);
	}

}

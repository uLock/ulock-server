package co.ulock.api.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.ulock.api.data.Group;
import co.ulock.api.data.Member;

import static org.torpedoquery.jpa.Torpedo.*;

@Service
public class GroupService {

	@Autowired
	private EntityManager entityManager;

	public List<Group> findAccountGroups(String accountId) {
		Group from = from(Group.class);
		Member members = innerJoin(from.getMembers());
		where(members.getUser().getAccountId()).eq(accountId);
		return select(from).list(entityManager);
	}

}

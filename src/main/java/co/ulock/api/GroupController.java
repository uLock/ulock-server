package co.ulock.api;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Predicates;

import co.ulock.api.dao.GroupRepository;
import co.ulock.api.dao.GroupService;
import co.ulock.api.dao.UserRepository;
import co.ulock.api.data.Group;
import co.ulock.api.data.Member;
import co.ulock.api.mapper.GroupMapper;
import co.ulock.api.view.FullGroupView;
import co.ulock.api.view.GroupView;

@CrossOrigin
@RestController
public class GroupController {

	@Autowired
	private GroupRepository groupRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private GroupMapper mapper;

	@Autowired
	private GroupService groupService;

	@Transactional
	@RequestMapping(path = "/groups", method = RequestMethod.POST)
	public GroupView createGroup(Principal principal, @RequestBody FullGroupView groupView) {
		Group group = mapper.map(groupView);
		group.getMembers().add(new Member(userRepository.findOneByAccountId(principal.getName())));
		Group save = groupRepository.save(group);
		return mapper.map(save);
	}

	@Transactional
	@RequestMapping(path = "/groups", method = RequestMethod.GET)
	public List<GroupView> listGroups(Principal principal) {
		return groupService.findAccountGroups(principal.getName()).stream().map(mapper::map)
				.collect(Collectors.toList());
	}

	@Transactional
	@RequestMapping(path = "/groups/{groupId}", method = RequestMethod.GET)
	public FullGroupView getGroup(Principal principal, @PathVariable("groupId") String groupId) {
		Group findOne = groupRepository.findOne(groupId);
		Optional<Member> findFirst = findOne.getMembers().stream()
				.filter(member -> member.getUser().getAccountId().equals(principal.getName())).findFirst();

		if (findFirst.isPresent()) {
			return mapper.detail(findOne);
		} else {
			return null;
		}
	}

}

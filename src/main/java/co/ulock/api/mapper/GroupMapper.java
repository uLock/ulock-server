package co.ulock.api.mapper;

import org.mapstruct.Mapper;

import co.ulock.api.data.Group;
import co.ulock.api.view.FullGroupView;
import co.ulock.api.view.GroupView;

@Mapper
public interface GroupMapper {

	Group map(FullGroupView groupView);
	
	GroupView map(Group group);

	FullGroupView detail(Group findOne);
	
}

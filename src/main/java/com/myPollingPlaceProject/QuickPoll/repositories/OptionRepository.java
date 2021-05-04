package com.myPollingPlaceProject.QuickPoll.repositories;

import com.myPollingPlaceProject.QuickPoll.domain.Option;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionRepository extends CrudRepository <Option, Long> {
}

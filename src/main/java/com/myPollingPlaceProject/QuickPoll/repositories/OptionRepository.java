package com.myPollingPlaceProject.QuickPoll.repositories;

import com.myPollingPlaceProject.QuickPoll.domain.Option;
import org.springframework.data.repository.CrudRepository;

public interface OptionRepository extends CrudRepository <Option, Long> {
}

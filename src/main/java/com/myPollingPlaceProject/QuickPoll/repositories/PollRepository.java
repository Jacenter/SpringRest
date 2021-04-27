package com.myPollingPlaceProject.QuickPoll.repositories;

import com.myPollingPlaceProject.QuickPoll.domain.Poll;
import org.springframework.data.repository.CrudRepository;

public interface PollRepository extends CrudRepository <Poll, Long> {
}

package com.myPollingPlaceProject.QuickPoll.repositories;

import com.myPollingPlaceProject.QuickPoll.domain.Vote;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends CrudRepository <Vote, Long> {
    @Query(value="select v.* FROM Option o, Vote v WHERE o.POLL_ID = ?1 AND o.OPTION_ID = v.OPTION_ID", nativeQuery = true)
    Iterable<Vote> findByPoll(Long pollId);

}

package com.myPollingPlaceProject.QuickPoll.services;
import com.myPollingPlaceProject.QuickPoll.domain.Vote;
import com.myPollingPlaceProject.QuickPoll.repositories.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@Service
public class VoteService {

    @Autowired
    private VoteRepository voteRepository;

    //create a vote
    public void createVote (Long pollId, Vote vote) {
        vote = voteRepository.save(vote);

        //set the headers
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(vote.getId()).toUri());
    }

    //get all votes from a poll
    public Iterable<Vote> getAllVotes(Long pollId) {
        return voteRepository.findByPoll(pollId);
    }

    //deleting a vote
    public void deleteVote(Long voteId){
        voteRepository.deleteById(voteId);
    }
}

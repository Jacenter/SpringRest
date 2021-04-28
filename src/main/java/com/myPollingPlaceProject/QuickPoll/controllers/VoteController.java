package com.myPollingPlaceProject.QuickPoll.controllers;
import com.myPollingPlaceProject.QuickPoll.domain.Vote;
import com.myPollingPlaceProject.QuickPoll.repositories.VoteRepository;
import com.myPollingPlaceProject.QuickPoll.services.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class VoteController {

    @Autowired
    private VoteService voteService;

    @Autowired
    private VoteRepository voteRepository;

    //create a vote
    @RequestMapping(value = "/polls/{pollId}/votes", method = RequestMethod.POST)
    public ResponseEntity<?> createVote (@PathVariable Long pollId, @RequestBody Vote vote) {
        voteService.createVote(pollId, vote);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //get all votes from a poll
    @RequestMapping(value = "/polls/{pollId}/votes", method = RequestMethod.GET)
    public ResponseEntity<?> getAllVotes(@PathVariable Long pollId) {
        voteService.getAllVotes(pollId);
        return new ResponseEntity<>(voteRepository.findByPoll(pollId), HttpStatus.OK);

        }
}

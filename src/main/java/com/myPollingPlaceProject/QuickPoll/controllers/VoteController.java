package com.myPollingPlaceProject.QuickPoll.controllers;

import com.myPollingPlaceProject.QuickPoll.domain.Vote;
import com.myPollingPlaceProject.QuickPoll.repositories.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Optional;

@RestController
public class VoteController {

    @Autowired
    private VoteRepository voteRepository;

    //create a vote
    @RequestMapping(value = "/polls/{pollId}/votes", method = RequestMethod.POST)
    public ResponseEntity<?> createVote (@PathVariable Long pollId, @RequestBody Vote vote) {
        vote = voteRepository.save(vote);

        //set the headers
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(vote.getId()).toUri());
        return new ResponseEntity<>( responseHeaders, HttpStatus.CREATED);
    }

    //get all votes from a poll
    @RequestMapping(value = "/polls/{pollId}/votes", method = RequestMethod.GET)
    public Optional<Vote> getAllVotes(@PathVariable Long pollId) {
        return voteRepository.findById(pollId);
    }
}

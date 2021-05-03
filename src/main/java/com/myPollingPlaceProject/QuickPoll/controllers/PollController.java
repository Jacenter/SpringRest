package com.myPollingPlaceProject.QuickPoll.controllers;
import com.myPollingPlaceProject.QuickPoll.domain.Poll;
import com.myPollingPlaceProject.QuickPoll.repositories.PollRepository;
import com.myPollingPlaceProject.QuickPoll.services.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class PollController {

    @Autowired
    private PollService pollService;

    @Autowired
    private PollRepository pollRepository;

    //get all polls
    @RequestMapping(value = "/polls", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Poll>> getAllPolls() {
        pollService.getAllPolls();
        return new ResponseEntity<>(pollRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/polls", method = RequestMethod.POST)
    public ResponseEntity<?> createPoll (@Valid @RequestBody Poll poll) {
        pollService.createPoll(poll);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //get an individual poll
    @RequestMapping(value = "/polls/{pollId}", method = RequestMethod.GET)
    public ResponseEntity<?> getPoll (@PathVariable Long pollId) {
        pollService.getPoll(pollId);
        return new ResponseEntity<>(pollRepository.findById(pollId), HttpStatus.OK);
    }

    //updating a poll
    @RequestMapping(value = "/polls/{pollId}", method = RequestMethod.PUT)
    public ResponseEntity<?> updatePoll (@RequestBody Poll poll, @PathVariable Long pollId) {
        pollService.updatePoll(poll, pollId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //delete a poll
    @RequestMapping(value = "polls/{pollId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletePoll (@PathVariable Long pollId) {
        pollService.deletePoll(pollId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

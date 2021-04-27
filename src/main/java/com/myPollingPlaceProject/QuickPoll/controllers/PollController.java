package com.myPollingPlaceProject.QuickPoll.controllers;

import com.myPollingPlaceProject.QuickPoll.domain.Poll;
import com.myPollingPlaceProject.QuickPoll.exception.ResourceNotFoundException;
import com.myPollingPlaceProject.QuickPoll.repositories.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
public class PollController {

    @Autowired
    private PollRepository pollRepository;

    protected void verifyPoll(Long pollId) throws ResourceNotFoundException {
        Optional<Poll> poll = pollRepository.findById(pollId);
        if (pollRepository.existsById(pollId) == false) {
            throw new ResourceNotFoundException("Poll with id " + pollId + " not found.");
        }
    }


    //get all polls
    @RequestMapping(value = "/polls", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Poll>> getAllPolls() {
        Iterable<Poll> allPolls = pollRepository.findAll();
        return new ResponseEntity<>(pollRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/polls", method = RequestMethod.POST)
    public ResponseEntity<?> createPoll (@Valid @RequestBody Poll poll) {
        poll = pollRepository.save(poll);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newPollUri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(poll.getId())
                .toUri();
        responseHeaders.setLocation(newPollUri);
        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    //get an individual poll
    @RequestMapping(value = "/polls/{pollId}", method = RequestMethod.GET)
    public ResponseEntity<?> getPoll (@PathVariable Long pollId) {
        Optional<Poll> p = pollRepository.findById(pollId);
        verifyPoll(pollId);
        return new ResponseEntity<>(p, HttpStatus.OK);
    }

    //updating a poll
    @RequestMapping(value = "/polls/{pollId}", method = RequestMethod.PUT)
    public ResponseEntity<?> updatePoll (@RequestBody Poll poll, @PathVariable Long pollId) {
        //save the entity
        verifyPoll(pollId);
        Poll p = pollRepository.save(poll);
        return new ResponseEntity<>(p, HttpStatus.OK);
    }

    //delete a poll
    @RequestMapping(value = "polls/{pollId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletePoll (@PathVariable Long pollId) {
        verifyPoll(pollId);
        pollRepository.deleteById(pollId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

package com.myPollingPlaceProject.QuickPoll.controllers;
import com.myPollingPlaceProject.QuickPoll.domain.Vote;
import com.myPollingPlaceProject.QuickPoll.repositories.VoteRepository;
import com.myPollingPlaceProject.QuickPoll.services.PollService;
import com.myPollingPlaceProject.QuickPoll.services.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class VoteController {

    @Autowired
    private VoteService voteService;

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private PollService pollService;

    //create a vote
    @RequestMapping(value = "/polls/{pollId}/votes", method = RequestMethod.POST)
    public ResponseEntity<?> createVote (@PathVariable Long pollId, @RequestBody Vote vote) {
        pollService.verifyPoll(pollId);
        voteService.createVote(pollId, vote);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //deleting a vote from a poll
    @RequestMapping(value = "/polls/votes/{voteId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteVote(@PathVariable Long voteId, @RequestBody Vote vote){
        voteService.deleteVote(voteId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //get all votes from a poll
    @RequestMapping(value = "/polls/{pollId}/votes", method = RequestMethod.GET)
    public ResponseEntity<?> getAllVotes(@PathVariable Long pollId) {
        pollService.verifyPoll(pollId);
        return new ResponseEntity<>(voteService.getAllVotes(pollId), HttpStatus.OK);
        }
}

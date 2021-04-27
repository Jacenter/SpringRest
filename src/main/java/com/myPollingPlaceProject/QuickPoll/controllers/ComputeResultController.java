package com.myPollingPlaceProject.QuickPoll.controllers;

import com.myPollingPlaceProject.QuickPoll.domain.Vote;
import com.myPollingPlaceProject.QuickPoll.dto.OptionCount;
import com.myPollingPlaceProject.QuickPoll.dto.VoteResult;
import com.myPollingPlaceProject.QuickPoll.repositories.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ComputeResultController {

    @Autowired
    private VoteRepository voteRepository;

    @RequestMapping(value = "/computeresult", method = RequestMethod.GET)
    public ResponseEntity<?> computeResult (@RequestParam Long pollId) {
        VoteResult voteResult = new VoteResult();
        Iterable<Vote> allVotes = voteRepository.findByPoll(pollId);
        //algorithm to count votes
        int totalVotes = 0;
        Map<Long, OptionCount> tempMap = new HashMap<>();
        for (Vote v : allVotes) {
            totalVotes ++;
            OptionCount optionCount = tempMap.get(v.getOption().getId());
            if (optionCount == null) {
                optionCount = new OptionCount();
                optionCount.setOptionId(v.getOption().getId());
                tempMap.put(v.getOption().getId(), optionCount);
            }
            optionCount.setCount(optionCount.getCount() + 1);
            voteResult.setTotalValues(totalVotes);
            voteResult.setTotalVotes(totalVotes);
            voteResult.setResults(tempMap.values());
        }
        return new ResponseEntity<VoteResult>(voteResult, HttpStatus.OK);
    }

}

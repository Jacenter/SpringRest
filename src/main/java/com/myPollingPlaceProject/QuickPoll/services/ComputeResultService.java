package com.myPollingPlaceProject.QuickPoll.services;
import com.myPollingPlaceProject.QuickPoll.domain.Vote;
import com.myPollingPlaceProject.QuickPoll.dto.OptionCount;
import com.myPollingPlaceProject.QuickPoll.dto.VoteResult;
import com.myPollingPlaceProject.QuickPoll.repositories.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ComputeResultService {

    @Autowired
    private VoteRepository voteRepository;

    public VoteResult computeResult (Long pollId) {
        VoteResult voteResult = new VoteResult();
        Iterable<Vote> allVotes = voteRepository.findByPoll(pollId);
        //algorithm to count votes
        int totalVotes = 0;
        Map<Long, OptionCount> tempMap = new HashMap<Long, OptionCount>();
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
        return voteResult;
    }

}

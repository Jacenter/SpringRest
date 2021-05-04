package com.myPollingPlaceProject.QuickPoll.services;
import com.myPollingPlaceProject.QuickPoll.domain.Poll;
import com.myPollingPlaceProject.QuickPoll.exception.ResourceNotFoundException;
import com.myPollingPlaceProject.QuickPoll.repositories.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.Optional;

@Service
public class PollService {

    @Autowired
    private PollRepository pollRepository;

    public void verifyPoll(Long pollId) throws ResourceNotFoundException {
        Optional<Poll> poll = pollRepository.findById(pollId);
        if (pollRepository.existsById(pollId) == false) {
            throw new ResourceNotFoundException("Poll with id " + pollId + " not found");
        }
    }

    public void getAllPolls() {
        Iterable<Poll> allPolls = pollRepository.findAll();

    }

    public void createPoll(Poll poll) {
        poll = pollRepository.save(poll);
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newPollUri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(poll.getId())
                .toUri();
        responseHeaders.setLocation(newPollUri);
    }

    public void getPoll(Long pollId) {
        Optional<Poll> p = pollRepository.findById(pollId);
        verifyPoll(pollId);
    }

    public void updatePoll(Poll poll, Long pollId) {
        verifyPoll(pollId);
        pollRepository.save(poll);
    }

    public void deletePoll(Long pollId) {
        verifyPoll(pollId);
        pollRepository.deleteById(pollId);
    }
}

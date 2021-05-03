package com.myPollingPlaceProject.QuickPoll.controllers;
import com.myPollingPlaceProject.QuickPoll.dto.VoteResult;
import com.myPollingPlaceProject.QuickPoll.services.ComputeResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ComputeResultController {

    @Autowired
    private ComputeResultService computeResultService;

    @RequestMapping(value = "/computeResult", method = RequestMethod.GET)
    public ResponseEntity<?> computeResult (@RequestParam Long pollId) {
        computeResultService.computeResult(pollId);
        return new ResponseEntity<>(computeResultService.computeResult(pollId), HttpStatus.OK);
    }

}

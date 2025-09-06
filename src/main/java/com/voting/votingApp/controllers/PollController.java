package com.voting.votingApp.controllers;

import com.voting.votingApp.model.Poll;
import com.voting.votingApp.request.Vote;
import com.voting.votingApp.services.PollService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/polls")
public class PollController {
    private final PollService pollService;
    public PollController(PollService pollService){
        this.pollService= pollService;
    }
    @PostMapping
    public Poll createPoll(@RequestBody Poll poll){
        return pollService.createPoll(poll);
    }
    @GetMapping
    public List<Poll> getAllPolls(){
        return pollService.getAllPoll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Poll> getPoll(@PathVariable Long id){
        return pollService.getPoll(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/vote")
    public void vote(@RequestBody Vote vote){
        pollService.vote(vote.getPollId(),vote.getOptionIndex());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String>deletePoll(@PathVariable Long id){
        pollService.deletePoll(id);
        return ResponseEntity.ok("Poll deleted successfully");
    }

}

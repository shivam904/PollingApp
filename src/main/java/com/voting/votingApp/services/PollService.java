package com.voting.votingApp.services;

import com.voting.votingApp.model.Optionvote;
import com.voting.votingApp.model.Poll;
import com.voting.votingApp.repositories.PollRepository;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PollService {
    private final PollRepository pollRepository;
    public PollService(PollRepository pollRepository){
        this.pollRepository=pollRepository;
    }
    public Poll createPoll(Poll poll){

        return pollRepository.save(poll);
    }
    public List<Poll>getAllPoll(){
        return pollRepository.findAll();
    }
    public Optional<Poll> getPoll(Long id){
        return pollRepository.findById(id);
    }
    public void vote(Long pollId, int optionIndex){
    Poll poll =pollRepository.findById(pollId).orElseThrow(()->new RuntimeException("Poll not found"));

    List<Optionvote>options= poll.getOptions();
    if(optionIndex >=options.size() || optionIndex<0){
        throw new IllegalArgumentException("Invalid option index");
    }
    Optionvote selected= options.get(optionIndex);
    selected.setCounter(selected.getCounter()+1);
    pollRepository.save(poll);
    }
    public void deletePoll(Long pollId) {
        if (!pollRepository.existsById(pollId)) {
            throw new RuntimeException("Poll with id " + pollId + " not found");
        }
        try {
            pollRepository.deleteById(pollId);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete Poll with id " + pollId, e);
        }
    }
}

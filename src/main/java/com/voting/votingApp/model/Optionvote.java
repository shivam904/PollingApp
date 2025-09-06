package com.voting.votingApp.model;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Embeddable
public class Optionvote {
    private String optionName;
    private Long counter=0l;
}

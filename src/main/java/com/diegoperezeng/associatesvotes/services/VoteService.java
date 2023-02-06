package com.diegoperezeng.associatesvotes.services;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.diegoperezeng.associatesvotes.entities.Vote;
import com.diegoperezeng.associatesvotes.repositories.VoteRepository;
import com.diegoperezeng.associatesvotes.utils.ServiceUtils;

@Service
public class VoteService {

	// Method created to reuse the database server messages handler and make code
	// more clean
	ServiceUtils serviceUtils = new ServiceUtils();

	private final VoteRepository voteRepository;

	public VoteService(VoteRepository VoteRepository) {
		this.voteRepository = VoteRepository;
	}

	public List<Vote> getAllVotes() {
		return serviceUtils.handleRepositoryCall(() -> voteRepository.findAll());
	}

	public Vote findVoteById(Long id) {
		return serviceUtils.handleRepositoryCall(() -> voteRepository.findById(id).orElse(null));
	}	
	
	// The method saveVote() is used to create a new vote for an associate in a particularly session.
	// Item: Receber votos dos associados em pautas (os votos são apenas 'Sim'/'Não'. Cada associado é identificado por um id único e pode votar apenas uma vez por pauta)
	//
	@Transactional
	public void saveVote(Long sessionId, Long associateId, Boolean voteChoice) throws ConstraintViolationException {
		Vote vote = new Vote();
		vote.setSessionId(sessionId);
		vote.setAssociateId(associateId);
		vote.setVoteChoice(voteChoice);
		vote.setCreatedAt(new Timestamp(System.currentTimeMillis()));

		serviceUtils.handleRepositoryCall(() -> voteRepository.save(vote));

	}
}

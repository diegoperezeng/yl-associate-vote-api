package com.diegoperezeng.associatesvotes.services;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.diegoperezeng.associatesvotes.entities.Vote;
import com.diegoperezeng.associatesvotes.entities.Vote.VoteOption;
import com.diegoperezeng.associatesvotes.repositories.VoteRepository;
import com.diegoperezeng.associatesvotes.utils.ServiceUtils;

@Service
public class VoteService {

	// Method created to reuse the database server messages handler and make code
	// more clean
	ServiceUtils serviceUtils = new ServiceUtils();

	private final VoteRepository VoteRepository;

	public VoteService(VoteRepository VoteRepository) {
		this.VoteRepository = VoteRepository;
	}

	public List<Vote> getAllVotes() {
		return serviceUtils.handleRepositoryCall(() -> VoteRepository.findAll());
	}

	public Vote findVoteById(Long id) {
		return serviceUtils.handleRepositoryCall(() -> VoteRepository.findById(id).orElse(null));
	}

	@Transactional
	public void saveVote(Long sectionId, Long associateId, VoteOption voteChoice) throws ConstraintViolationException {
		Vote vote = new Vote();
		vote.setSectionId(sectionId);
		vote.setAssociateId(associateId);
		vote.setVoteChoice(voteChoice);

		serviceUtils.handleRepositoryCall(() -> VoteRepository.save(vote));

	}
}

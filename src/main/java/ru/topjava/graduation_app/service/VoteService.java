package ru.topjava.graduation_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.topjava.graduation_app.entity.Vote;
import ru.topjava.graduation_app.exception.EntityNotFoundException;
import ru.topjava.graduation_app.exception.VotingTimeIsUpException;
import ru.topjava.graduation_app.repository.CrudRestaurantRepository;
import ru.topjava.graduation_app.repository.CrudUserRepository;
import ru.topjava.graduation_app.repository.CrudVoteRepository;
import ru.topjava.graduation_app.util.VoteUtil;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@Transactional(readOnly = true)
public class VoteService {
    private CrudVoteRepository voteRepository;
    private CrudUserRepository userRepository;
    private CrudRestaurantRepository restaurantRepository;

    @Autowired
    public VoteService(CrudVoteRepository voteRepository, CrudUserRepository userRepository, CrudRestaurantRepository restaurantRepository) {
        this.voteRepository = voteRepository;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
    }


    public Vote get(int userId, LocalDate localDate) {
        return voteRepository.findByUserIdAndLocalDate(userId, localDate).orElseThrow(()-> new EntityNotFoundException("Entity not found"));
    }

    public List<Vote> getAllByRestaurantIdAndDate(int id, LocalDate date) {
        return voteRepository.getAllByRestaurantIdAndLocalDate(id, date);
    }

    public Long getAmountVotesForToday(int restaurantId) {
        return voteRepository.getAmountVotesByRestaurantIdAndLocalDate(restaurantId, LocalDate.now());
    }

    public Iterable<Vote> getAll() {
        return voteRepository.findAll();
    }

    public Map<Integer, Long> getAllForToday() {
        List<Vote> allVotes = voteRepository.getAllForToday(LocalDate.now());
        return allVotes.stream().collect(Collectors.groupingBy(v->v.getRestaurant().id(), Collectors.counting()));
    }

    @Transactional
    public void update(int restaurantId, int userId) {
        if (!VoteUtil.checkTimeVote()) {
            throw new VotingTimeIsUpException("You can't change your vote after 11:00 AM");
        }
        Vote oldVote = voteRepository.findByUserIdAndLocalDate(userId, LocalDate.now()).orElseThrow(()-> new EntityNotFoundException("Vote by user_id = " + userId + " and local_date  = " + LocalDate.now() + " was not found"));
        oldVote.setRestaurant(restaurantRepository.findById(restaurantId).orElseThrow(()->new EntityNotFoundException("Restaurant with id = " + restaurantId + " was not found")));
        voteRepository.save(oldVote);
    }

    @Transactional
    public Vote save(int restaurantId, int userId) {
        Vote vote = new Vote();
        vote.setUser(userRepository.getReferenceById(userId));
        vote.setRestaurant(restaurantRepository.findById(restaurantId).orElseThrow(()->new EntityNotFoundException("Restaurant with id = " + restaurantId + " was not found")));

        return voteRepository.save(vote);
    }

    @Transactional
    public boolean delete(int id) {
        return voteRepository.delete(id) != 0;
    }
}

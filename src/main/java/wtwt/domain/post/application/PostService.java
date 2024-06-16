package wtwt.domain.post.application;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wtwt.domain.category.infrastructure.CategoryRepository;
import wtwt.domain.category.model.Category;
import wtwt.domain.post.application.dto.request.CreatePostReq;
import wtwt.domain.post.infrastructure.PostRepository;
import wtwt.domain.post.model.Post;
import wtwt.domain.trip.infrastructure.TripRepository;
import wtwt.domain.trip.model.Trip;
import wtwt.domain.user.infrastructure.UserRepository;
import wtwt.domain.user.model.User;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final TripRepository tripRepository;

    @Transactional
    public Long create(CreatePostReq request) {
        Trip trip = createTripByPost(request);
        Post post = createPostWithTrip(request, tripRepository.save(trip));
        return postRepository.save(post)
            .getId();
    }

    public Long save(Long id, CreatePostReq createPostReq) {
        return null;
    }

    public Long createAndSave(CreatePostReq createPostReq) {
        return null;
    }

    public Long publish(Long id, CreatePostReq createPostReq) {
        return null;
    }

    public Long createAndPublish(CreatePostReq createPostReq) {
        return null;
    }

    private Post createPostWithTrip(CreatePostReq request, Trip trip) {
        return Post.builder()
            .category(getCategoryOrElseThrow(request.categoryId(), "해당 ID의 카테고리가 존재하지 않습니다."))
            .writer(getUserOrElseThrow(request.loginUserId(), "해당 ID의 유저가 존재하지 않습니다."))
            .title(request.title())
            .content(request.content())
            .isLightning(request.isLightning())
            .imageUrls(request.urls())
            .tagNames(request.tags())
            .trip(trip)
            .build();
    }

    private Category getCategoryOrElseThrow(Long categoryId, String message) {
        return categoryRepository.findById(categoryId)
            .orElseThrow(() -> new EntityNotFoundException(message));
    }

    private Trip createTripByPost(CreatePostReq request) {
        List<User> members = toMembers(request.members());

        return Trip.builder()
            .startDate(request.startDate())
            .endDate(request.endDate())
            .preferCapacity(request.groupSize())
            .users(members)
            .preferGender(request.preferGender())
            .preferMinAge(request.preferMinAge())
            .preferMaxAge(request.preferMaxAge())
            .build();
    }

    private List<User> toMembers(List<Long> members) {
        return members.stream()
            .map(userId -> getUserOrElseThrow(userId, "해당 ID의 멤버가 존재하지 않습니다."))
            .toList();
    }

    private User getUserOrElseThrow(Long userId, String message) {
        return userRepository.findById(userId)
            .orElseThrow(() -> new EntityNotFoundException(message));
    }
}

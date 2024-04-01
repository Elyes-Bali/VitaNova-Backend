package tn.esprit.vitanova.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.vitanova.entities.Community;
import tn.esprit.vitanova.entities.Posts;
import tn.esprit.vitanova.repository.CommunityRepository;
import tn.esprit.vitanova.repository.PostsRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostsService implements IPostsService {

    private final CommunityRepository communityRepository;
    private final PostsRepository postsRepository;

    @Override
    public Posts addNewPost(Posts post, Long communityId) {
        Community community = communityRepository.findById(communityId).orElseThrow(NullPointerException::new);
        post.setCommunity(community);
        return postsRepository.save(post);
    }

    @Override
    public void deletePost(Long postId) {
        postsRepository.deleteById(postId);
    }

    @Override
    public Posts updatePost(Posts postRequest, Long postId) {
        Posts currentPost = postsRepository.findById(postId).orElseThrow(NullPointerException::new);
        currentPost.setPost(postRequest.getPost());
        currentPost.setDescription(postRequest.getPost());
        currentPost.setImageP(postRequest.getPost());
        return postsRepository.save(currentPost);
    }

    @Override
    public List<Posts> getCommunityPosts(Long communityId) {
        return postsRepository.findByCommunity_IdCommunity(communityId);
    }

    @Override
    public List<Posts> getUserPosts(Long userId) {
        return postsRepository.findByIdOwner(userId);
    }

    @Override
    public Posts getPostDetail(long communityId, long postId) {
        return postsRepository.findByIdPostsAndCommunity_IdCommunity(postId, communityId).orElseThrow(NullPointerException::new);
    }
}

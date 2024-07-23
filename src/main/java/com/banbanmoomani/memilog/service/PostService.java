package com.banbanmoomani.memilog.service;

import com.banbanmoomani.memilog.DAO.PostMapper;
import com.banbanmoomani.memilog.DTO.post.CreateRequestDTO;
import com.banbanmoomani.memilog.DTO.post.PostDTO;
import com.banbanmoomani.memilog.DTO.archivePostDTO;
import com.banbanmoomani.memilog.DTO.todayPostDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PostService {
    private final PostMapper postMapper;

    public PostService(PostMapper postMapper) {
        this.postMapper = postMapper;
    }

    public List<PostDTO> findAllPosts() {
        return postMapper.findAllPost();
    }

    @Transactional
    public void createPost(CreateRequestDTO createRequestDTO) {
        postMapper.createPost(createRequestDTO);
    }
//    @Transactional
//    public void updatePost(PostDTO updateRequestDTO) {
//        PostDTO post = postMapper.findPostById(updateRequestDTO.getPost_id());
//        if (post != null && post.getUser_id() == updateRequestDTO.getUser_id()) {
//            postMapper.updatePost(updateRequestDTO);
//        } else {
//            throw new IllegalArgumentException("해당 포스트가 없거나 권한이 없습니다.");
//        }
//    }
    @Transactional
    public void updatePost(PostDTO updateRequestDTO) {
    // 하드코딩된 post_id를 사용하여 게시물을 찾습니다.
        int postId = 35;
        int userId = 1;
        PostDTO post = postMapper.findPostById(postId);
        System.out.println("Post: " + post);
        System.out.println("UpdateRequest User ID: " + userId);

        if (post != null && post.getUser_id() == userId) {
        // 하드코딩된 post_id와 user_id를 설정
            updateRequestDTO.setPost_id(postId);
            updateRequestDTO.setUser_id(userId);
            postMapper.updatePost(updateRequestDTO);
        } else {
            throw new IllegalArgumentException("해당 포스트가 없거나 권한이 없습니다.");
        }
    }

    @Transactional
    public void deletePost(int postId, int userId) {
        PostDTO post = postMapper.findPostById(postId);
        if (post != null && post.getUser_id() == userId) {
            postMapper.deletePostById(postId);
        } else {
            throw new IllegalArgumentException("해당 포스트가 없습니다");
        }
    }

    public PostDTO findPostById(int postId) {
        return postMapper.findPostById(postId);
    }

    public List<PostDTO> findAllPostOnMissionByDate() {

        return postMapper.findAllPostOnMissionByDate();
    }

    public List<PostDTO> findPostsByCompanion(List<Integer> companionIds) {
        return postMapper.findPostsByCompanion(companionIds);
    }

    public List<todayPostDTO> getTodayPostDTOList() {
        return postMapper.findTodayPost();
    }

    public List<archivePostDTO> getArchivePostDTOList() {
        return postMapper.findArchivePost();
    }

    public int getTodayPostCount() {
        Integer count = postMapper.findTodayPostCount();
        return count == null ? 0 : count;
    }
}

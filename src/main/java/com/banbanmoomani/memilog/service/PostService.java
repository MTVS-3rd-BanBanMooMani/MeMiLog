package com.banbanmoomani.memilog.service;

import com.banbanmoomani.memilog.DAO.PostMapper;
import com.banbanmoomani.memilog.DTO.post.CreateRequestDTO;
import com.banbanmoomani.memilog.DTO.post.PostDTO;
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
    @Transactional
    public void updatePost(CreateRequestDTO updateRequestDTO) {
        PostDTO post = postMapper.findPostById(updateRequestDTO.getPostId());
        if (post != null && post.getUser_id() == updateRequestDTO.getUser_id()) {
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

}

package com.banbanmoomani.memilog.service;

import com.banbanmoomani.memilog.DAO.PostMapper;
import com.banbanmoomani.memilog.DTO.PostDTO;
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
    public void createPost(PostDTO post) {
        if (post.getTitle() == null || post.getTitle().isEmpty()) {
            post.setTitle(post.getMissionContent());
        }
        postMapper.createPost(post);
    }


    public List<PostDTO> findAllPostOnMissionByDate() {

        return postMapper.findAllPostOnMissionByDate();
    }
}

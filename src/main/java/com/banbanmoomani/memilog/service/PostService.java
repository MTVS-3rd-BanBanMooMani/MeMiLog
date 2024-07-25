package com.banbanmoomani.memilog.service;

import com.banbanmoomani.memilog.DAO.FileMapper;
import com.banbanmoomani.memilog.DAO.LikeMapper;
import com.banbanmoomani.memilog.DAO.PostMapper;
import com.banbanmoomani.memilog.DTO.*;
import com.banbanmoomani.memilog.DTO.mydiary.PostRequestDTO;
import com.banbanmoomani.memilog.DTO.post.CreateRequestDTO;
import com.banbanmoomani.memilog.DTO.post.PostDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PostService {
    private final PostMapper postMapper;

    private final LikeMapper likeMapper;

    private final FileMapper fileMapper;

    public PostService(PostMapper postMapper, LikeMapper likeMapper, FileMapper fileMapper) {
        this.postMapper = postMapper;
        this.likeMapper = likeMapper;
        this.fileMapper = fileMapper;
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
        PostDTO post = postMapper.findPostById(updateRequestDTO.getPost_id());
        if (post != null && post.getUser_id() == updateRequestDTO.getUser_id()) {
            System.out.println("updateRequestDTO = " + updateRequestDTO);
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

    public List<PostRequestDTO> findAllPostOnMissionByDate(String date) {

        return postMapper.findAllPostOnMissionByDate(date);
    }

    public List<PostRequestDTO> findPostsByCompanion(List<Integer> companionIds) {
        return postMapper.findPostsByCompanion(companionIds);
    }


    public PostRequestDTO showPostDetail(Long postId) {
        return postMapper.showPostDetail(postId);
    }

    @Transactional
    public void increaseLikeCount(int post_id, int user_id) {
        postMapper.increaseLikeCount(post_id);
        LikeDTO likeDTO = new LikeDTO(user_id, post_id);
        likeMapper.insertLikeInfo(likeDTO);
    }

    @Transactional
    public void decreaseLikeCount(int post_id, int user_id) {
        postMapper.decreaseLikeCount(post_id);
        LikeDTO likeDTO = new LikeDTO(user_id, post_id);
        likeMapper.deleteLikeInfo(likeDTO);
    }
    @Transactional
    public void updateHidden() {
        postMapper.updateHidden();
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

    public List<UpdateFileDTO> updatefiles(int postId) {
        return postMapper.updateFile(postId);
    }

    public String findMainFile(int postId) {
        return postMapper.findMainFile(postId);
    }

    public void updateImageOrder(ImageOrderDTO imageOrderDTO) {
        postMapper.updateImageOrder(imageOrderDTO);
    }
    public void deleteImageOrder(ImageOrderDTO imageOrderDTO) {
        postMapper.deleteImageOrder(imageOrderDTO);
    }
    public void addImage(ImageOrderDTO imageOrderDTO) {
        postMapper.addImage(imageOrderDTO);
    }

    public List<Integer> getFileIdsByPostId(int postId, Integer userId) {
        System.out.println("getFileIdsByPostId = " + postId);
        List<Integer> integerList = fileMapper.selectFileIdsByPostId(postId);
        for (Integer fileId : integerList) {
            System.out.println("fileId = " + fileId);
        }
        return integerList;
    }

    public void deleteFileById(int fileId, Integer userId) {
        fileMapper.deleteFileById(fileId);
    }

    public void saveFile(UpdateFileDTO updateFileDTO) {
        fileMapper.getFile(updateFileDTO);
    }

    public void updatePictureOrderBySrcUrl(String srcUrl, Integer order, Integer userId, Integer postId) {
        fileMapper.updatePictureOrder(srcUrl, order, userId, postId);
    }
}

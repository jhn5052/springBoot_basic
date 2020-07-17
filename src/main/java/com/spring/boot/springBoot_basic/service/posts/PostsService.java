package com.spring.boot.springBoot_basic.service.posts;

import com.spring.boot.springBoot_basic.domain.posts.Posts;
import com.spring.boot.springBoot_basic.domain.posts.PostsRepository;
import com.spring.boot.springBoot_basic.web.dto.PostsListResponseDto;
import com.spring.boot.springBoot_basic.web.dto.PostsResponseDto;
import com.spring.boot.springBoot_basic.web.dto.PostsSaveRequestDto;
import com.spring.boot.springBoot_basic.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) throws IllegalAccessException {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new
                IllegalAccessException("해당 게시글이 없습니다. id = " + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }
    @Transactional
    public void delete(Long id){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        postsRepository.delete(posts);
    }

    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));
        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAll().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }


}

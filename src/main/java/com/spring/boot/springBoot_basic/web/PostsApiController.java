package com.spring.boot.springBoot_basic.web;


import com.spring.boot.springBoot_basic.service.posts.PostsService;
import com.spring.boot.springBoot_basic.web.dto.PostsResponseDto;
import com.spring.boot.springBoot_basic.web.dto.PostsSaveRequestDto;
import com.spring.boot.springBoot_basic.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {
    private final PostsService postsService;
    //등록
    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto){
        return postsService.save(requestDto);
    }

    //수정 , 조회
    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) throws IllegalAccessException {
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id){
        return postsService.findById(id);
    }

}

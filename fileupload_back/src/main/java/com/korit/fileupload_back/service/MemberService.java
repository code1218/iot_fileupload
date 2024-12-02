package com.korit.fileupload_back.service;

import com.korit.fileupload_back.dto.ReqFileUploadDto;
import com.korit.fileupload_back.dto.RespAddMemberDto;
import com.korit.fileupload_back.entity.Member;
import com.korit.fileupload_back.exception.MemberInsertException;
import com.korit.fileupload_back.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MemberService {

    @Autowired
    private FileUploadService fileUploadService;
    @Autowired
    private MemberRepository memberRepository;

    public RespAddMemberDto addMember(ReqFileUploadDto dto) {
        String profileImgPath = fileUploadService.uploadFile(dto.getImg());

        Member memberEntity = Member.builder()
                .name(dto.getTitle())
                .profileImgPath(profileImgPath)
                .build();

        Member insertedMember = memberRepository.save(memberEntity)
                .orElseThrow(() -> {
                    Map<String, Object> errorMap = Map.of(
                            "httpStatus", HttpStatus.INTERNAL_SERVER_ERROR,
                            "message", "DB에 멤버 추가 중 오류!"
                    );
                    return new MemberInsertException("멤버 추가 오류!", errorMap);
                });

        return RespAddMemberDto.builder()
                .isSuccess(true)
                .memberId(insertedMember.getId())
                .build();
    }
}

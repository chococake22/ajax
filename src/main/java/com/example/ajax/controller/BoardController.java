package com.example.ajax.controller;


import com.example.ajax.domain.AttachedFile;
import com.example.ajax.domain.Board;
import com.example.ajax.service.BoardServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final BoardServiceImpl boardService;

    @Value("${file.upload.path}")
    private String fileDir;

    public String getFullPath(String filename) {
        return fileDir + filename;
    }

    @GetMapping("/")
    public String main(Model model, Board dto) {

        List<Board> boards = boardService.SELECT_BOARD_LIST(dto);

        model.addAttribute("boards", boards);

        return "index";
    }

    @GetMapping("/reg")
    public String saveBoard() {
        return "/board/reg";
    }


    @PostMapping("/test/ajax")
    @ResponseBody
    public Map<String,Object> testAjax(Board board,
                                       @RequestParam(value = "files", required = false) List<MultipartFile> files) throws IOException {

        Map<String, Object> result = new HashMap<String, Object>();

        try {
            Board newBoard = Board.builder()
                    .name(board.getName())
                    .gender(board.getGender())
                    .age(board.getAge())
                    .phone(board.getPhone())
                    .build();

            boardService.INSERT_BOARD(newBoard);

            // 만약 첨부파일이 존재한다면
            if (files != null){ // files 자체가 null로 받아진다. 그렇기 때문에 여기에서 size니 empty 이런걸로 접근하면 안됨... 그냥 이거가 null인가 아닌가로만 판단해야함
                for (MultipartFile file : files) {

                    AttachedFile attachedFile = AttachedFile.builder()
                            .filename(file.getOriginalFilename())
                            .filePath(getFullPath(file.getOriginalFilename()))
                            .blindYn(board.getBlindYn())
                            .regId("user1")
                            .modId("user3")
                            .build();

                    // 파일 전송
                    file.transferTo(new File(getFullPath(file.getOriginalFilename())));

                    // AttachedFile 저장
                    boardService.INSERT_BOARD_ATTACHED_FILE(attachedFile);

                    System.out.println("-------------------------------");
                    log.info("파일 이름 : {} : ", file.getName());
                    log.info("파일 이름 : {} : ", file.getOriginalFilename());
                    log.info("파일 이름 : {} : ", file.getSize());
                    System.out.println("-------------------------------");
                }
            }
            result.put("code", "0000");
        } catch (Exception e) {
            throw new RuntimeException();
        }

        return result;
    }


    @ResponseBody
    @PostMapping("/board/blind")
    public Map<String, Object> blindBoardList(Model model, @RequestBody Map<String, Object> map2) {

        Map<String, Object> result = new HashMap<>();

        try {
            // 헤더에서 data[]를 가져와서 일일히 값을 사용할 수 있음
//            String[] arrays = request.getParameterValues("data[]");
//
//            for (String str : arrays) {
//                System.out.println(str);
//            }

            // 배열 자체가 JSON이 되어버림
            System.out.println(map2.get("data"));

            result.put("0000", "성공이여");
        } catch (Exception e) {
            throw new RuntimeException();
        }

        return result;

    }

}

package com.example.ajax.controller;


import com.example.ajax.domain.AttachedFile;
import com.example.ajax.domain.Board;
import com.example.ajax.service.BoardServiceImpl;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardServiceImpl boardService;

    @Value("${file.upload.path}")
    private String fileDir;

    public String getFullPath(String filename) {
        return fileDir + filename;
    }

    @GetMapping("/")
    public String main() {
        return "index";
    }

    @PostMapping("/test/ajax")
    @ResponseBody
    public Map<String,Object> testAjax(Board board,
                                       @RequestParam(value = "files", required = false) List<MultipartFile> files) throws IOException {

        Map<String, Object> result = new HashMap<String, Object>();


        try {
            System.out.println(board.getName());
            System.out.println(board.getGender());
            System.out.println(board.getAge());
            System.out.println(board.getPhone());

            Board newBoard = new Board();
            newBoard.setName(board.getName());
            newBoard.setGender(board.getGender());
            newBoard.setAge(board.getAge());
            newBoard.setPhone(board.getPhone());

            boardService.INSERT_BOARD(newBoard);

            // 만약 첨부파일이 존재한다면
            if (files != null){ // files 자체가 null로 받아진다. 그렇기 때문에 여기에서 size니 empty 이런걸로 접근하면 안됨... 그냥 이거가 null인가 아닌가로만 판단해야함
                for (MultipartFile file : files) {

                    AttachedFile attachedFile = new AttachedFile();

                    attachedFile.setFilename(file.getOriginalFilename());
                    attachedFile.setFilePath(getFullPath(file.getOriginalFilename()));
                    attachedFile.setBlindYn(board.getBlindYn());
                    attachedFile.setRegId("user1");
                    attachedFile.setModId("user1");

                    file.transferTo(new File(getFullPath(file.getOriginalFilename())));

                    boardService.INSERT_BOARD_ATTACHED_FILE(attachedFile);

                    System.out.println("-------------------------------");
                    System.out.println("파일 ???? : " + file.getName());
                    System.out.println("파일 이름 : " + file.getOriginalFilename());
                    System.out.println("파일 크기 : " + file.getSize());
                    System.out.println("-------------------------------");
                }
            }
            result.put("code", "0000");
        } catch (Exception e) {
            throw new RuntimeException();
        }

        return result;
    }
}

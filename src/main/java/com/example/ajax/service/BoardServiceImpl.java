package com.example.ajax.service;

import com.example.ajax.domain.AttachedFile;
import com.example.ajax.domain.Board;
import com.example.ajax.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    public final BoardMapper boardMapper;

    @Override
    public JSONObject SELECT_BOARD(Long boardId) {
        return boardMapper.SELECT_BOARD(boardId);
    }

    @Override
    public int COUNT_BOARD(Board dto) {
        return boardMapper.COUNT_BOARD(dto);
    }

    @Override
    public  List<Board> SELECT_BOARD_LIST(Board dto) {
        return boardMapper.SELECT_BOARD_LIST(dto);
    }


    @Override
    public void INSERT_BOARD(Board dto) {
       boardMapper.INSERT_BOARD(dto);
    }

    @Override
    public void INSERT_BOARD_ATTACHED_FILE(AttachedFile file) {
        boardMapper.INSERT_BOARD_ATTACHED_FILE(file);
    }
}

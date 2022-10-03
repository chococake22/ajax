package com.example.ajax.service;

import com.example.ajax.domain.AttachedFile;
import com.example.ajax.domain.Board;
import com.example.ajax.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    public final BoardMapper boardMapper;

    @Override
    public JSONObject SELECT_BOARD(Long boardId) {
        return boardMapper.SELECT_BOARD(boardId);
    }

    @Override
    public void INSERT_BOARD(Board board) {
       boardMapper.INSERT_BOARD(board);
    }

    @Override
    public void INSERT_BOARD_ATTACHED_FILE(AttachedFile file) {
        boardMapper.INSERT_BOARD_ATTACHED_FILE(file);
    }
}

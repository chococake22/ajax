package com.example.ajax.service;


import com.example.ajax.domain.AttachedFile;
import com.example.ajax.domain.Board;
import org.json.simple.JSONObject;

public interface BoardService {

    public JSONObject SELECT_BOARD(Long boardId);

    public void INSERT_BOARD(Board board);

    public void INSERT_BOARD_ATTACHED_FILE(AttachedFile file);
}

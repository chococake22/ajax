package com.example.ajax.service;


import com.example.ajax.domain.AttachedFile;
import com.example.ajax.domain.Board;
import org.json.simple.JSONObject;

import java.util.List;

public interface BoardService {

    public JSONObject SELECT_BOARD(Long boardId);

    public int COUNT_BOARD(Board dto);

    public  List<Board> SELECT_BOARD_LIST(Board dto);

    public void INSERT_BOARD(Board board);

    public void INSERT_BOARD_ATTACHED_FILE(AttachedFile file);
}

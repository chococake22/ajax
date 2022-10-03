package com.example.ajax.mapper;

import com.example.ajax.domain.AttachedFile;
import com.example.ajax.domain.Board;
import org.apache.ibatis.annotations.Mapper;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface BoardMapper {

    public JSONObject SELECT_BOARD(Long boardId);

    public int COUNT_BOARD(Board dto);

    public  List<Board> SELECT_BOARD_LIST(Board dto);

    public void INSERT_BOARD(Board board);

    public void INSERT_BOARD_ATTACHED_FILE(AttachedFile file);

}

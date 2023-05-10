package org.example.models;

import org.example.commons.Privacy;

import java.util.ArrayList;
import java.util.List;

public class Board {

    String boardId;

    String name;

    Privacy privacy;

    String url;

    public String getBoardId() {
        return boardId;
    }

    public void setBoardId(String boardId) {
        this.boardId = boardId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Privacy getPrivacy() {
        return privacy;
    }

    public void setPrivacy(Privacy privacy) {
        this.privacy = privacy;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Users> getMembers() {
        return members;
    }

    public void setMembers(List<Users> members) {
        this.members = members;
    }

    public List<BoardList> getBoardList() {
        return boardList;
    }

    public void setBoardList(List<BoardList> boardList) {
        this.boardList = boardList;
    }

    List<Users> members = new ArrayList<>();

    List<BoardList> boardList = new ArrayList<>();



}

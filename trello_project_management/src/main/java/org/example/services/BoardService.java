package org.example.services;

import org.example.commons.Privacy;
import org.example.repos.BoardRepo;

public class BoardService {

    BoardRepo boardRepo;


    public BoardService(){

        boardRepo = BoardRepo.getInstance();

    }


    public boolean createBoard(String name, Privacy privacy){

        boolean status = boardRepo.addBoard(name,privacy);

        return status;

    }

    public boolean addNewUser(String name,String boardId){


        boolean status = boardRepo.addNewUser(boardId,name);

        return status;
    }

    public void displayBoards(){

        boardRepo.displayBoards();

    }


}

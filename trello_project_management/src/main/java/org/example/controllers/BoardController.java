package org.example.controllers;

import org.example.commons.CommonStrings;
import org.example.commons.Privacy;
import org.example.commons.SuccessMessage;
import org.example.repos.BoardRepo;
import org.example.services.BoardService;

import java.util.Scanner;

public class BoardController {

   BoardService boardService;

    public void createBoard(){

        Scanner sc = new Scanner(System.in);

        boardService = new BoardService();

        /// required name and privacy;

        System.out.println("Enter Name for your Board : ");

        String name = sc.next();

        System.out.println("Enter Privacy for your Board : public/private : ");

        String privacy = sc.next().toLowerCase();

        boolean status=false;

        if(privacy.equals(CommonStrings.PRIVATE)){

            status=boardService.createBoard(name, Privacy.PRIVATE);

        }

        else if(privacy.equals(CommonStrings.PUBLIC)){

            status=boardService.createBoard(name,Privacy.PUBLIC);

        }

        if(status) System.out.println(SuccessMessage.BOARD_CREATED_SUCCESSFULLY);

    }

    public void addNewUser(){

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter username : ");
        String name = sc.nextLine();
        System.out.println("Board id to assign member : ");
        String boardId = sc.next();

        boardService = new BoardService();

        boolean status = boardService.addNewUser(name,boardId);

        if(status) System.out.println(SuccessMessage.USER_ASSIGNED_SUCCESSFULLY);
    }

    public void displayBoards(){

        BoardService bs = new BoardService();

        bs.displayBoards();

    }

}

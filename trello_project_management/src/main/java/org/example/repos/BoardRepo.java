package org.example.repos;

import org.example.commons.ErrorMessage;
import org.example.commons.Privacy;
import org.example.commons.Urls;
import org.example.exceptions.InvalidBoardIdException;
import org.example.exceptions.UserAlreadyPresentException;
import org.example.models.Board;
import org.example.models.Users;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class BoardRepo {

    HashMap<String, Board> boards = new HashMap<>();
    Random r = new Random();
    public static BoardRepo br = null;
    private BoardRepo(){


    }

    public static BoardRepo getInstance(){

        if(br==null) br = new BoardRepo();

        return br;
    }


    public boolean addBoard(String name, Privacy privacy){

        Board b = new Board();

        String boardId = String.valueOf(r.nextInt(1000))+name;
        b.setBoardId(boardId);
        b.setName(name);
        b.setPrivacy(privacy);
        String url = "";
        if(privacy==Privacy.PRIVATE)
            url = generateUrl(name,"secure",boardId);
        else if(privacy==Privacy.PUBLIC)
            url = generateUrl(name,"open",boardId);

        b.setUrl(url);

        boards.put(boardId,b);

        return true;
    }

    public void displayBoards(){

        for(Map.Entry<String,Board> entry:boards.entrySet()){

            Board b = entry.getValue();

            System.out.println("BoardName : "+b.getName());
            System.out.println("BoardUrl : "+b.getUrl());
            System.out.println("BoardStatus : "+b.getPrivacy());

            if(b.getMembers()!=null) {

                b.getMembers().iterator().forEachRemaining(user->{

                    System.out.println("UserID : "+user.getUserId());
                    System.out.println("UserName : "+user.getUserName());
                    System.out.println("----- user seperation -----");
                });
            }

            System.out.println("________________board seperation_______________________");

        }


    }

    public boolean addNewUser(String boardId,String name){

        try {
            if (!boards.containsKey(boardId)) throw new InvalidBoardIdException(ErrorMessage.INVALID_BOARD_ID);

            Board board = boards.get(boardId);

            List<Users> members = board.getMembers();

            Users user = new Users();
            user.setUserId(r.nextInt(1000));
            user.setUserName(name);

            members.add(user);

            board.setMembers(members);

            boards.put(boardId,board);

            return true;

          //  if(checkUserPresent(members,userId)) throw new UserAlreadyPresentException(ErrorMessage.USER_ALREADY_PRESENT);

        }catch(InvalidBoardIdException ib){

            System.out.println(ib.getMessage());
        }

        return false;
    }

//    public boolean addNewUser(){
//
//    }

    private boolean checkUserPresent(List<Users> members,int userId){

        for(int itr = 0; itr<members.size(); itr++){

            Users user = members.get(itr);

            if(user.getUserId()==userId) return true;
        }

        return false;

    }

    private String generateUrl(String name,String privacy,String id){

        return Urls.DOMIAN_URL +name+"/"+privacy+"/"+id;

    }


}

package data.promotiondata.test;

import data.userdata.UserData;
import dataService.userdataService.UserDataService;
import po.UserPO;
import util.UserSearchCondition;

public class Userdata {

    public static void main(String args[]){

        try{
            UserDataService userdata = new UserData();


//            UserPO userPO = new UserPO();
//            userPO.setComment("comment userPO");
//            userPO.setDate("fdsa");
//
//            userdata.insert(userPO);

            System.out.println(userdata.search(new UserSearchCondition()).get(0).getComment());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
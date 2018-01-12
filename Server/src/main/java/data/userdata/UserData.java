package data.userdata;

import annotations.RMIRemote;
import dataService.userdataService.UserDataService;
import javafx.scene.image.Image;
import mapper.UserDataPOMapper;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import po.UserPO;
import util.ImageConvertor;
import util.ResultMessage;
import util.UserSearchCondition;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
@RMIRemote
public class UserData extends UnicastRemoteObject implements UserDataService {

    public UserData() throws RemoteException {
    }

    @Override
    public UserPO getNew() throws RemoteException {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            UserDataPOMapper mapper = session.getMapper(UserDataPOMapper.class);
            int id = mapper.getId();
            UserPO userPO = new UserPO();
            userPO.setUserId(id);
            session.commit();
            return userPO;
        }
    }


    @Override
    public ResultMessage delete(int id) throws RemoteException {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            UserDataPOMapper mapper = session.getMapper(UserDataPOMapper.class);
            mapper.delete(id);
            session.commit();
            return ResultMessage.SUCCESS;
        }
    }

    @Override
    public ResultMessage update(UserPO userPO) throws RemoteException {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            UserDataPOMapper mapper = session.getMapper(UserDataPOMapper.class);
            mapper.update(userPO);
            System.out.println(userPO);
            session.commit();
            return ResultMessage.SUCCESS;
        }
    }

    @Override
    public UserPO showDetail(int id) throws RemoteException {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            UserDataPOMapper mapper = session.getMapper(UserDataPOMapper.class);
            UserPO userPO = mapper.showDetail(id);
            session.commit();
            File file = null;
            String files[] = null;
            try{
                file = new File(getUserImageURL());
                files = file.list();
                System.out.println(files[0]);
            }catch (Exception e){
                e.printStackTrace();
            }
            for(String f:files) {
                    try {
                        if (userPO.getUserId() == Integer.parseInt(f.split("\\.")[0])) {
                            BufferedImage image = ImageIO.read((new File(file.getPath() + "\\" + f)));
                            userPO.setImage(ImageConvertor.getByte(image));
                        }else {
                            BufferedImage image = ImageIO.read((new File("/default/timg.jpg")));
                            userPO.setImage(ImageConvertor.getByte(image));
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }

            }
            return userPO;
        }
    }

    @Override
    public ArrayList<UserPO> search(UserSearchCondition userSearchCondition) throws RemoteException {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            UserDataPOMapper mapper = session.getMapper(UserDataPOMapper.class);
            ArrayList<UserPO> userPOs = mapper.search(userSearchCondition);
            File file = null;
            String files[] = null;
            try{
                file = new File(getUserImageURL());
                files = file.list();
                System.out.println(files[0]);
            }catch (Exception e){
                e.printStackTrace();
            }
            for(UserPO userPO:userPOs){
                for(String f:files) {
                    try {
                        if (userPO.getUserId() == Integer.parseInt(f.split("\\.")[0])) {
                            BufferedImage image = ImageIO.read((new File(file.getPath() + "\\" + f)));
                            userPO.setImage(ImageConvertor.getByte(image));
                        }else {
                            BufferedImage image = ImageIO.read((new File("/default/timg.jpg")));
                            userPO.setImage(ImageConvertor.getByte(image));
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("sabi");
            System.out.println(userPOs.size());
            session.commit();
            return userPOs;
        }
    }

    @Override
    public UserPO checkPassword(String username, String password) throws RemoteException {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            UserDataPOMapper mapper = session.getMapper(UserDataPOMapper.class);
            UserPO userPO = mapper.getPassword(username);
            if(userPO == null)
                return null;
            if(userPO.getPassword() == password)
                return userPO;

            session.commit();
        }
        return null;
    }

    private static String getUserImageURL(){
        return "E:\\ERPnju\\Server\\src\\main\\resources\\image";
    }

}

package Helpers;

import Models.GameState;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import gui.controller.BoardGameController;
import gui.controller.GameScoresController;
import org.tinylog.Logger;

import java.io.*;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DataManager {
    /**
     * Function checks if program is running as jar or from IDE.
     * According to the options, it calls specific functions.
     */
    public static void save(GameState gamestate){
        String protocol = gamestate.getClass().getResource("").getProtocol();
        if(Objects.equals(protocol, "jar")){
            JarSave(gamestate);
        } else if(Objects.equals(protocol, "file")) {
            ideSave(gamestate);
        }
    }
    /**
     * Function gets {@code data.json} file by using Class Loader Mechanism when program is running from IDE.
     * Then it checks if {@code data.json} has data.
     * If yes, then by using {@code ObjectMapper} it adds all data from the file to a {@code List<Player> playerList}.
     * Then it adds current user to the list.
     * Finally, {@code ObjectWriter} writes the new data to {@code data.json}
     */
    private static void ideSave(GameState game){
        InputStream data = BoardGameController.class.getResourceAsStream("/data.json");
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        try {
            List<GameState> gameList = new ArrayList<GameState>();
            if(data.available()!=0){
                gameList = objectMapper.readValue(data, new TypeReference<List<GameState>>() {
                });
            }
            gameList.add(game);
            OutputStream out = new FileOutputStream(BoardGameController.class.getResource("/data.json").getFile());
            ObjectWriter writer = objectMapper.writer(new DefaultPrettyPrinter());
            writer.writeValue(out,gameList);
            Logger.info("Game data has been saved");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Function gets {@code data.json} file by using Class Loader Mechanism program is running as JAR.
     * Then it checks if {@code data.json} has data.
     * If yes, then by using {@code ObjectMapper} it adds all data from the file to a {@code List<Player> playerList}.
     * Then it adds current user to the list.
     * Finally, {@code ObjectWriter} writes the new data to {@code data.json}
     */
    private static void JarSave(GameState game){
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        try {
            String path = game.getClass().getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            path = path.substring(0, path.lastIndexOf("/") + 1);
            path = URLDecoder.decode(path, "UTF-8");
            FileInputStream data = new FileInputStream(path+"classes/data.json");
            Logger.info(data);
            BufferedReader in = new BufferedReader(new InputStreamReader(data));
            List<GameState> gameList = new ArrayList<GameState>();
            if(data.available()!=0){
                gameList = objectMapper.readValue(in, new TypeReference<List<GameState>>() {
                });
            }
            gameList.add(game);
            OutputStream out = new FileOutputStream(path+"classes/data.json");
            ObjectWriter writer = objectMapper.writer(new DefaultPrettyPrinter());
            writer.writeValue(out,gameList);
            Logger.info("Game data has been saved");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Function to return FileInputStream data when program is running as JAR
     */
    public static FileInputStream JarRead(GameScoresController controller){
        try {
            String path = controller.getClass().getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            path = path.substring(0, path.lastIndexOf("/") + 1);
            path = URLDecoder.decode(path, StandardCharsets.UTF_8);
            FileInputStream file = new FileInputStream(path+"/classes/data.json");
            return file;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Function to return InputStream data when program is running from IDE
     */
    public static InputStream IdeRead(GameScoresController controller){
        return controller.getClass().getResourceAsStream("/data.json");
    }

}

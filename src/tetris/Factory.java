package tetris;

/**
 * Created by lysogordima on 22.04.16.
 */
public class Factory {
        public Game getMenu(String menutype){
            if (menutype == null)
                return null;
            if (menutype.equalsIgnoreCase("MainMenu"))
                return new Proxy();
            if (menutype.equalsIgnoreCase("Game"))
                return new Tetris();
            return null;
        }
}
